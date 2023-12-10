package ua.com.enotagency.builder

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import ua.com.enotagency.dto.BinotelCallRequest
import ua.com.enotagency.dto.ReceivedTheCall
import ua.com.enotagency.dto.enum.BinotelRequestType

@Component
class ReceivedTheCallBuilder : BinotelRequestBuilder {
    override val requestType = BinotelRequestType.RECEIVED_THE_CALL

    override fun build(request: Map<String, String>): BinotelCallRequest {
        val json = jacksonObjectMapper().writeValueAsString(request)
        return jacksonObjectMapper().readValue(json, ReceivedTheCall::class.java)
    }
}
