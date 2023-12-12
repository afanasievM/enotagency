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
        val clientIp = request.remoteAddr
        log.info(request.remoteHost)
        log.info(request.serverName)
        log.info(request.localName)
        log.info(request.localAddr)
        log.info(request.contentType)
        log.info(request.remotePort.toString())
        log.info(request.localPort.toString())
        log.info(request.serverPort.toString())
        if (!allowedIPs.contains(clientIp)) {
            log.warn("Restricted $clientIp")
            return
        }
        chain.doFilter(request, response)
    }
}
