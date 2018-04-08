package ar.com.crypticmind.testserv.client.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import org.springframework.web.client.RestTemplate
import javax.validation.constraints.NotBlank

@Configuration
@Validated
@ConfigurationProperties(prefix = "testserv.service")
class ServiceConnector {

    @NotBlank
    lateinit var endpoint: String
    
    fun serviceConnector() =
            RestTemplate()

}
