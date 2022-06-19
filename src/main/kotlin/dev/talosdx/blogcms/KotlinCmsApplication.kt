package dev.talosdx.blogcms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class KotlinCmsApplication

fun main(args: Array<String>) {
    runApplication<KotlinCmsApplication>(*args)
}
