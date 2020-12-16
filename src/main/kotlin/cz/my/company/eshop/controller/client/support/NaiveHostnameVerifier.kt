package cz.my.company.eshop.controller.client.support

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSession

class NaiveHostnameVerifier(vararg naivelyTrustedHostnames: String?) : HostnameVerifier {
  private val naivelyTrustedHostnames: Set<String> = mutableSetOf(*naivelyTrustedHostnames) as Set<String>
  private val hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
  override fun verify(hostname: String, session: SSLSession): Boolean {
    return naivelyTrustedHostnames.contains(hostname) ||
        hostnameVerifier.verify(hostname, session)
  }

}