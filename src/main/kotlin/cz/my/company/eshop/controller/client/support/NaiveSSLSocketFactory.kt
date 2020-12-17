package cz.my.company.eshop.controller.client.support

import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

class NaiveSSLSocketFactory(vararg naivelyTrustedHostnames: String?) : SSLSocketFactory() {
  private val sslSocketFactory = getDefault() as SSLSocketFactory
  private val alwaysAllowSslContext: SSLContext = SSLContext.getInstance("TLS")
  private val naivelyTrustedHostnames: Set<String> = mutableSetOf(*naivelyTrustedHostnames) as Set<String>
  override fun getDefaultCipherSuites(): Array<String> {
    return sslSocketFactory.defaultCipherSuites
  }

  override fun getSupportedCipherSuites(): Array<String> {
    return sslSocketFactory.supportedCipherSuites
  }

  @Throws(IOException::class)
  override fun createSocket(socket: Socket, host: String, port: Int, autoClose: Boolean): Socket {
    return if (naivelyTrustedHostnames.contains(host)) alwaysAllowSslContext.socketFactory.createSocket(socket, host, port, autoClose) else sslSocketFactory.createSocket(socket, host, port, autoClose)
  }

  @Throws(IOException::class)
  override fun createSocket(host: String, port: Int): Socket {
    return if (naivelyTrustedHostnames.contains(host)) alwaysAllowSslContext.socketFactory.createSocket(host, port) else sslSocketFactory.createSocket(host, port)
  }

  @Throws(IOException::class)
  override fun createSocket(host: String, port: Int, localAddress: InetAddress, localPort: Int): Socket {
    return if (naivelyTrustedHostnames.contains(host)) alwaysAllowSslContext.socketFactory.createSocket(host, port, localAddress, localPort) else sslSocketFactory.createSocket(host, port, localAddress, localPort)
  }

  @Throws(IOException::class)
  override fun createSocket(host: InetAddress, port: Int): Socket {
    return if (naivelyTrustedHostnames.contains(host.hostName)) alwaysAllowSslContext.socketFactory.createSocket(host, port) else sslSocketFactory.createSocket(host, port)
  }

  @Throws(IOException::class)
  override fun createSocket(host: InetAddress, port: Int, localHost: InetAddress, localPort: Int): Socket {
    return if (naivelyTrustedHostnames.contains(host.hostName)) alwaysAllowSslContext.socketFactory.createSocket(host, port, localHost, localPort) else sslSocketFactory.createSocket(host, port, localHost, localPort)
  }

  init {
    val tm: TrustManager = InsecureTrustManager()
    alwaysAllowSslContext.init(null, arrayOf(tm), null)
  }
}