package com.shivang.search.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.AsyncRestTemplate
import spock.lang.Specification

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS


@ContextConfiguration(classes = AsyncRestTemplateConfig)
@DirtiesContext(classMode = AFTER_CLASS)
class AsyncRestTemplateConfigSpec extends Specification {

    @Autowired
    AsyncRestTemplate asyncRestTemplate

    def "Get AsyncRestTemplate configured correctly"() {
        given: "That the autoconfiguration is enabled for the application (which it is by default for Spring Boot App)"
        when: "The required AsyncRestTemplate bean is autowired"
        then: "Verify that the autowired bean is not null"
            asyncRestTemplate != null
    }
}