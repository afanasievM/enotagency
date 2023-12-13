package ua.com.enotagency.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ua.com.enotagency.interceptor.IncomeAllowingInterceptor
import ua.com.enotagency.service.AtlassianIPService


@Configuration
class WebConfig : WebMvcConfigurer {
    @Value("\${allowed.ip}")
    private lateinit var allowedIPs: Set<String>

    @Value("\${allowed.domains}")
    private lateinit var allowedDomains: Set<String>


    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(IncomeAllowingInterceptor(allowedIPs, atlassianIpService()))
            .addPathPatterns("/**")
    }

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun atlassianIpService() = AtlassianIPService(restTemplate())
}
