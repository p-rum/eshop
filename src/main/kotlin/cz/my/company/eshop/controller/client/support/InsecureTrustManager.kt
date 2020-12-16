package cz.my.company.eshop.controller.client.support

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * Dummy Trust manager accepting all certificates for SSL security connections.
 *
 *
 * Created by vmusil on 11-Oct-2019.
 */
class InsecureTrustManager : X509TrustManager {
  /**
   * {@inheritDoc}
   */
  override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
    // Everyone is trusted!
  }

  /**
   * {@inheritDoc}
   */
  override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
    // Everyone is trusted!
  }

  /**
   * {@inheritDoc}
   */
  override fun getAcceptedIssuers(): Array<X509Certificate?> {
    return arrayOfNulls(0)
  }
}