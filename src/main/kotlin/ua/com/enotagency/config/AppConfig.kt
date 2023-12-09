package ua.com.enotagency.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ua.com.enotagency.builder.BinotelCallRequestBuilder
import ua.com.enotagency.builder.BinotelRequestBuilder

@Configuration
class AppConfig {
    @Bean
    fun binotelCallRequestBuilder(builders: List<BinotelRequestBuilder>): BinotelCallRequestBuilder {
        BinotelCallRequestBuilder.init(builders)
        return BinotelCallRequestBuilder
    }
}
