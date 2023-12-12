package ua.com.enotagency.filter


import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
@Order(2)
class InRequestResponseLoggerFilter : Filter {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        super.init(filterConfig)
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val wreq = ContentCachingRequestWrapper(request as HttpServletRequest)
        val wres = ContentCachingResponseWrapper(response as HttpServletResponse)
        log.info("{}: {}?{}", wreq.method, wreq.requestURI, if (wreq.queryString != null) wreq.queryString else "")
        wreq.headerNames.asIterator().forEach { "Header: $it : ${wreq.getHeader(it)}" }
        chain.doFilter(wreq, wres)
        while (wreq.inputStream.read() >= 0) {
        }
        log.info("Response: {}", String(wres.contentAsByteArray))
        wres.copyBodyToResponse()
    }
}
