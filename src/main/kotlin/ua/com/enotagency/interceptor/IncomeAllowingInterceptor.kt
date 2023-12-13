package ua.com.enotagency.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor

class IncomeAllowingInterceptor(
    private val allowedIPs: Set<String>,
    private val allowedDomains: Set<String>
) : HandlerInterceptor {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val clientDomain = request.remoteHost
        val clientIp = request.remoteAddr
        log.info("IpAddress: $clientDomain")
        log.info("Domain: $clientDomain")
        if (!isAllowedDomain(clientDomain) || !allowedIPs.contains(clientIp)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
            return false
        }
        return true
    }

    private fun isAllowedDomain(clientDomain: String): Boolean {
        return allowedDomains.stream().anyMatch { clientDomain.endsWith(it) }
    }
}
