package ua.com.enotagency.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.net.util.SubnetUtils
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import ua.com.enotagency.service.AtlassianIPService

class IncomeAllowingInterceptor(
    private val allowedIPs: Set<String>,
    private val atlassianIPService: AtlassianIPService
) : HandlerInterceptor {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val clientIp = request.remoteAddr
        val range = atlassianIPService.getAtlassianIPRanges()
        if (!allowedIPs.contains(clientIp) || !range.checkRange(clientIp)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
            return false
        }
        return true
    }
}

private fun List<String>.checkRange(ip: String): Boolean {
    return this.any {
        println(it)
        val subnet = SubnetUtils(it)
        return subnet.info.isInRange(ip)
    }
}
