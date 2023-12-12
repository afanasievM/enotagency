package ua.com.enotagency.config

import com.julienvey.trello.Trello
import com.julienvey.trello.impl.TrelloImpl
import com.julienvey.trello.impl.http.ApacheHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import ua.com.enotagency.builder.BinotelCallRequestBuilder
import ua.com.enotagency.builder.BinotelRequestBuilder


@Configuration
class AppConfig {

    @Value("\${trello.trelloKey}")
    private val trelloKey: String? = null

    @Value("\${trello.trelloAccessToken}")
    private val trelloAccessToken: String? = null

    @Bean
    fun binotelCallRequestBuilder(builders: List<BinotelRequestBuilder>): BinotelCallRequestBuilder {
        BinotelCallRequestBuilder.init(builders)
        return BinotelCallRequestBuilder
    }

    @Bean
    fun trelloClient(): Trello {
        return TrelloImpl(trelloKey, trelloAccessToken, ApacheHttpClient())
    }

    @Bean
    fun corsFilter(
        @Value("\${allowed.ip}") allowedIPs: Set<String>,
        @Value("\${allowed.domains}") allowedDomains: Set<String>
    ): FilterRegistrationBean<CorsFilter> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        allowedIPs.forEach { config.addAllowedOrigin("http://$it") }
        allowedDomains.forEach { config.addAllowedOriginPattern("http://$it") }
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        val bean = FilterRegistrationBean(CorsFilter(source))
        bean.order = 0
        return bean
    }
}
