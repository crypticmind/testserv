package ar.com.crypticmind.testserv.model

import ar.com.crypticmind.testserv.service.misc.json.JsonConfiguration
import com.fasterxml.jackson.databind.JsonMappingException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.*

@RunWith(SpringRunner::class)
@Import(JsonConfiguration::class)
@JsonTest
class SerializationTest {

    data class TestEntity(val i: Int, val b: Boolean, val s: String)

    @Autowired
    private lateinit var json: JacksonTester<TestEntity>

    @Test
    fun backAndForth() {

        assertThat(json.parse(""" { "i": 123, "b": true, "s": "str" } """))
                .isEqualTo(TestEntity(123, true, "str"))

        assertThat(json.write(TestEntity(123, true, "str")))
                .isEqualToJson(""" { "i": 123, "b": true, "s": "str" } """)

    }

    @Test
    fun failOnNullPrimitives() {

        assertFailsWith(JsonMappingException::class) {
            json.parse(""" {           "b": true, "s": "str" } """)
        }

        assertFailsWith(JsonMappingException::class) {
            json.parse(""" { "i": 123,            "s": "str" } """)
        }

    }
}