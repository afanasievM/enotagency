package ua.com.enotagency.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ua.com.enotagency.interceptor.IncomeAllowingInterceptor


@Configuration
class WebConfig : WebMvcConfigurer {
    @Value("\${allowed.ip}")
    private lateinit var allowedIPs: Set<String>

    @Value("\${allowed.domains}")
    private lateinit var allowedDomains: Set<String>
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(incomeAllowingInterceptor(allowedIPs, allowedDomains)).addPathPatterns("/**")
    }

    @Bean
    fun incomeAllowingInterceptor(allowedIPs: Set<String>, allowedDomains: Set<String>): IncomeAllowingInterceptor {
        return IncomeAllowingInterceptor(allowedIPs, allowedDomains)
    }
}
