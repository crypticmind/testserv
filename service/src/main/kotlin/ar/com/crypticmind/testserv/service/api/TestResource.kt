package ar.com.crypticmind.testserv.service.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service/test")
class TestResource {

    @GetMapping("/hello")
    fun hello(name: String?) =
            "Hello " + (name ?: " world")

}
