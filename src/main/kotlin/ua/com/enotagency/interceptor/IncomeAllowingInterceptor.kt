package ua.com.enotagency.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import ua.com.enotagency.service.AllowedIPService

class IncomeAllowingInterceptor(private val atlassianIPService: AllowedIPService) : HandlerInterceptor {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val clientIp = request.remoteAddr
        if (!atlassianIPService.getAllowedIps().contains(clientIp)) {
            log.info("Access denied for ip: $clientIp")
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
            return false
        }
        return true
    }
}
