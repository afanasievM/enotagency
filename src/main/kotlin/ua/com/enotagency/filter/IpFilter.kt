package ua.com.enotagency.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class IpFilter(@Value("\${allowed.ip}") private val allowedIPs: Set<String>) : Filter {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun init(filterConfig: FilterConfig?) {}

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        log.info(request.remoteHost)
        chain.doFilter(request, response)
    }
}
