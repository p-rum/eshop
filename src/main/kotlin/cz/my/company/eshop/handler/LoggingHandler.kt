package cz.my.company.eshop.handler

import cz.my.company.eshop.logger.Log
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingResponseWrapper
import java.io.*
import java.nio.charset.Charset
import java.util.function.Consumer
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.servlet.FilterChain
import javax.servlet.ReadListener
import javax.servlet.ServletException
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse


/**
 * Logging handler Filter
 */
class LoggingHandler : OncePerRequestFilter() {
  companion object : Log()

  private val EXCLUDED_PATHS = mutableSetOf("/_bonjour.json")
  private val VISIBLE_TYPES: List<MediaType> = listOf(
      MediaType.valueOf("text/*"),
      //  MediaType.APPLICATION,
      MediaType.APPLICATION_JSON,
      // MediaType.APPLICATION_XML,
      MediaType.valueOf("application/*+json"),
      // MediaType.valueOf("application/*+xml"),
      MediaType.MULTIPART_FORM_DATA
  )

  private val REQUEST_BODY = "Request body: "
  private val RESPONSE_BODY = "Response body: "
  private val DELIMITER = " | "

  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    val path = request.requestURI.substring(request.contextPath.length)
        .replace("[/]+$".toRegex(), "")
    if (EXCLUDED_PATHS.contains(path)) {
      filterChain.doFilter(request, response)
      return
    }
    if (isAsyncDispatch(request)) {
      filterChain.doFilter(request, response)
    } else {
      doFilterWrapped(MultiReadHttpServletRequest(request), wrapResponse(response), filterChain)
    }
  }

  @Throws(ServletException::class, IOException::class)
  private fun doFilterWrapped(request: MultiReadHttpServletRequest, response: ContentCachingResponseWrapper, filterChain: FilterChain) {
    try {
      beforeRequest(request)
      filterChain.doFilter(request, response)
    } finally {
      afterRequest(response)
    }
  }

  private fun beforeRequest(request: MultiReadHttpServletRequest) {
    if (log.isInfoEnabled) {
      logRequest(request, request.remoteAddr)
    }
  }

  @Throws(IOException::class)
  private fun afterRequest(response: ContentCachingResponseWrapper) {
    if (log.isInfoEnabled) {
      logResponse(response)
    }
  }

  private fun logRequest(request: MultiReadHttpServletRequest, prefix: String) {
    val logData = StringBuilder()
    val queryString = request.queryString
    if (queryString == null) {
      logData.append("[").append(prefix).append("]").append(" ", request.method, " ", request.requestURI)
    } else {
      logData.append("[").append(prefix).append("]").append(" ", request.method, " ", request.requestURI, "?", queryString)
    }

    val requestHeaders: MutableMap<String, String> = mutableMapOf()
    listOf(request.headerNames)
        .forEach { h ->
          requestHeaders[h.nextElement()] = request.getHeader(h.nextElement())
        }
    logData.append(DELIMITER).append("Request headers: ", requestHeaders.toString())
    val content = request.content
    if (content.isNotEmpty()) {
      logData.append(DELIMITER, getContent(content, request.contentType, request.characterEncoding, REQUEST_BODY))
    }
    log.info(logData.toString())
  }

  @Throws(IOException::class)
  private fun logResponse(response: ContentCachingResponseWrapper) {
    val logData = StringBuilder()
    val status = response.status
    logData.append(status, " ", HttpStatus.valueOf(status).reasonPhrase)
    val content = response.contentAsByteArray
    if (content.isNotEmpty()) {
      logData.append(DELIMITER, getContent(content, response.contentType, response.characterEncoding, RESPONSE_BODY))
    }
    response.copyBodyToResponse()
    logData.append(DELIMITER).append("Response headers: ")
    response.headerNames.forEach(Consumer { headerName: String? -> response.getHeaders(headerName).forEach(Consumer { headerValue: String? -> logData.append(headerName).append(" : ").append(headerValue) }) })
    log.info(logData.toString())
  }

  private fun getContent(content: ByteArray, contentType: String, contentEncoding: String, bodyType: String): String {
    val logData = StringBuilder()
    val mediaType: MediaType = MediaType.valueOf(contentType)
    val visible = VISIBLE_TYPES.stream().anyMatch { visibleType: MediaType -> visibleType.includes(mediaType) }
    return when {
      visible -> {
        try {
          val contentString = String(content, Charset.forName(contentEncoding))
          logData.append(bodyType)
          Stream.of(contentString.split("\r\n|\r|\n")).forEach { st -> logData.append(st) }
          logData.toString()
        } catch (e: UnsupportedEncodingException) {

          logData.append("[", content.size, "] bytes content]", content.size).toString()
        }
      }


      else -> logData.append("[", content.size, "] bytes content]", content.size).toString()
    }
  }

  private fun wrapResponse(response: HttpServletResponse): ContentCachingResponseWrapper {
    return response as? ContentCachingResponseWrapper ?: ContentCachingResponseWrapper(response)
  }

  private class MultiReadHttpServletRequest internal constructor(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    val content: ByteArray
    private val servletInputStream: ServletInputStream = request.inputStream

    override fun getInputStream(): ServletInputStream {
      val byteArrayInputStream = ByteArrayInputStream(content)
      return object : ServletInputStream() {
        override fun isFinished(): Boolean {
          return servletInputStream.isFinished
        }

        override fun isReady(): Boolean {
          return servletInputStream.isReady
        }

        override fun setReadListener(listener: ReadListener) {
          servletInputStream.setReadListener(listener)
        }

        override fun read(): Int {
          return byteArrayInputStream.read()
        }
      }
    }

    override fun getReader(): BufferedReader {
      return BufferedReader(InputStreamReader(this.inputStream))
    }

    init {
      val servletInputStream = request.inputStream
      content = BufferedReader(InputStreamReader(servletInputStream)).lines()
          .collect(Collectors.joining()).toByteArray()
    }
  }
}