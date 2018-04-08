package ar.com.crypticmind.testserv.client.backend

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.*

@RunWith(SpringRunner::class)
@SpringBootTest
class TestservIntegrationTest {

    @Autowired
    private lateinit var testserv: Testserv

    @Test
    fun getGreeting() {
        assertEquals("Hello John", testserv.getGreeting("John"))
    }
}
