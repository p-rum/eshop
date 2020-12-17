package cz.my.company.eshop.controller.client.decoder

import cz.my.company.eshop.exception.impl.BadGatewayException
import cz.my.company.eshop.logger.Log
import feign.Response
import feign.Util
import feign.codec.ErrorDecoder
import org.apache.commons.lang3.StringUtils

class CustomFeignErrorDecoder : ErrorDecoder {

  companion object : Log()

  override fun decode(methodKey: String, response: Response): Exception {

    var responseBody = if (StringUtils.isNotBlank(response.reason())) response.reason() else "Unknown error."
    try {
      when {
        response.body() != null -> responseBody = String(Util.toByteArray(response.body().asInputStream()))
      }
    } catch (e: Exception) {
      log.debug("Error while decoding response: {} ", e)
    }
    return BadGatewayException(message = responseBody)
  }
}
