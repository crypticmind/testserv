package ar.com.crypticmind.testserv.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class TestservClient

fun main(args: Array<String>) {
    runApplication<TestservClient>(*args)
}
