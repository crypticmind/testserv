package ar.com.crypticmind.testserv.client.backend

import ar.com.crypticmind.testserv.client.config.ServiceConnectorConfiguration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class Testserv(
        private val serviceConnectorConfiguration: ServiceConnectorConfiguration,
        @Qualifier("serviceConnector") private val connector: RestTemplate) {

    private val endpoint = serviceConnectorConfiguration.endpoint

    fun getGreeting(name: String?): String {
        val uri = StringBuilder().append(endpoint).append("/test/hello")
        if (name != null) uri.append("?name=$name")
        return connector.getForObject(uri.toString(), String::class.java) ?: "(empty response from server)"
    }
}
