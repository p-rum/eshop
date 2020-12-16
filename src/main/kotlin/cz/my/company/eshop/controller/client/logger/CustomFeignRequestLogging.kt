package cz.my.company.eshop.controller.client.logger

import cz.my.company.eshop.logger.Log
import feign.Logger
import feign.Request
import feign.Response
import feign.Util
import feign.form.util.CharsetUtil
import java.io.IOException

class CustomFeignRequestLogging : Logger() {
  companion object : Log()

  override fun logRequest(configKey: String, logLevel: Level, request: Request) {
    log(configKey, "Request: %s/ %s", request.httpMethod().name, request.url())
    log(configKey, "Headers: %s", request.headers())
    if (request.body() != null) {
      val bodyText = if (request.charset() != null) String(request.body(), request.charset()) else null
      log(configKey, "") // CRLF
      log(configKey, "Body: %s", bodyText ?: "Body: Binary data")
    }
  }

  @Throws(IOException::class)
  override fun logAndRebufferResponse(configKey: String, logLevel: Level, response: Response, elapsedTime: Long): Response {
    val status = response.status()
    val bodyLength = 0
    val request = response.request()
    log(configKey, "Response: %s %s %s", request.httpMethod().name, request.url(), status, elapsedTime)
    log(configKey, "Headers: %s", request.headers())
    when {
      response.body() != null && !(status == 204 || status == 205) -> {
        val bodyData = Util.toByteArray(response.body().asInputStream())
        log(configKey, "Body: %s", Util.decodeOrDefault(bodyData, CharsetUtil.UTF_8, "Binary data"))
        return response.toBuilder().body(bodyData).build()
      }
      else -> {
        log(configKey, "Body: %s-byte", bodyLength)
      }
    }
    return response
  }

  override fun log(configKey: String, format: String, vararg args: Any) {
    log.info(format(configKey, format, *args))
  }

  private fun format(configKey: String?, format: String, vararg args: Any?): String {
    return String.format(methodTag(configKey) + format, *args)
  }
}