package ar.com.crypticmind.testserv.client.config

import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.client.RestTemplate
import java.security.KeyStore
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Configuration
@Validated
@ConfigurationProperties(prefix = "testserv.service")
class ServiceConnectorConfiguration {

    @NotBlank
    lateinit var endpoint: String

    @NotNull
    lateinit var keyStore: Resource

    @NotBlank
    lateinit var keyStorePassword: String

    @NotBlank
    lateinit var keyPassword: String

    @NotNull
    lateinit var trustStore: Resource

    @NotBlank
    lateinit var trustStorePassword: String

    @Bean(name = [ "serviceConnector" ])
    fun serviceConnector(builder: RestTemplateBuilder): RestTemplate {
        val sslContext =
                SSLContextBuilder()
                        .loadKeyMaterial(keyStore.url, keyStorePassword.toCharArray(), keyPassword.toCharArray())
                        .loadTrustMaterial(trustStore.url, trustStorePassword.toCharArray())
                        .build()
        val socketFactory = SSLConnectionSocketFactory(sslContext)
        val httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build()
        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        return builder.requestFactory { factory }.build()
    }
}
