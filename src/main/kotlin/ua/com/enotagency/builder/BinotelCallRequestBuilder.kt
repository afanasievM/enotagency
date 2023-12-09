package ua.com.enotagency.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ua.com.enotagency.dto.BinotelCallRequest

@Component
object BinotelCallRequestBuilder {
    @Autowired
    lateinit var builders: List<BinotelRequestBuilder>

    fun build(request: Map<String, String>): BinotelCallRequest? {
        return choose(request)?.let { build(request) }

    }

    private fun choose(request: Map<String, String>): BinotelRequestBuilder? {
        val requestType = request["requestType"]
        return builders.filter { it.requestType.toString() == requestType }.first
    }
}
