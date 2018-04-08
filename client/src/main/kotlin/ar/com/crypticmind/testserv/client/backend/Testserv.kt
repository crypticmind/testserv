package ar.com.crypticmind.testserv.client.backend

import ar.com.crypticmind.testserv.client.config.ServiceConnector
import org.springframework.stereotype.Service

@Service
class Testserv(private val serviceConnector: ServiceConnector) {

    private val connector = serviceConnector.serviceConnector()
    private val endpoint = serviceConnector.endpoint

    fun getGreeting(name: String?): String {
        val uri = StringBuilder().append(endpoint).append("/test/hello")
        if (name != null) uri.append("?name=$name")
        return connector.getForObject(uri.toString(), String::class.java) ?: "(empty response from server)"
    }
}
