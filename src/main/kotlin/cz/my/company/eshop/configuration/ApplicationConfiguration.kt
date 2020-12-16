package cz.my.company.eshop.configuration


import cz.my.company.eshop.handler.LoggingHandler


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Application Configuration
 */
@Configuration
class ApplicationConfiguration() {

    /**
     * Register LoggingHandler bean
     * @return registered LoggingHandler beam
     */
    @Bean
    fun createLoggingFilter(): LoggingHandler {
        return LoggingHandler()
    }

}

