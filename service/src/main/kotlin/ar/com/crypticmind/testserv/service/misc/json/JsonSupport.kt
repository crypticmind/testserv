package ar.com.crypticmind.testserv.service.misc.json

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.type.*
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class JsonConfiguration(private val objectMapper: ObjectMapper) {

    @Bean
    fun kotlinModule() = KotlinModule()

    @PostConstruct
    fun configureObjectMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
    }

}
