package cz.my.company.eshop.controller.client.configuration

import com.fasterxml.jackson.module.kotlin.KotlinModule
import cz.my.company.eshop.controller.client.logger.CustomFeignRequestLogging
import feign.Feign
import feign.Logger
import feign.Retryer
import feign.httpclient.ApacheHttpClient
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Component
@Import(FeignClientsConfiguration::class)
class FeignClientBuilder {

  val feignBuilder: Feign.Builder
    get() = Feign.builder()
        .client(ApacheHttpClient())
        .contract(SpringMvcContract())
        .decoder(JacksonDecoder(listOf(KotlinModule())))
        .encoder(JacksonEncoder())
        .logLevel(Logger.Level.BASIC)
        .logger(CustomFeignRequestLogging())
        .retryer(Retryer.NEVER_RETRY)
}