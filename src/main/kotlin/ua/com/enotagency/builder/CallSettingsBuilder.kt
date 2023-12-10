package ua.com.enotagency.builder

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import ua.com.enotagency.dto.CallSetting
import ua.com.enotagency.dto.enum.BinotelRequestType

@Component
class CallSettingsBuilder : BinotelRequestBuilder {
    override val requestType = BinotelRequestType.API_CALL_SETTINGS
    override fun build(request: Map<String, String>): CallSetting {
        val json = jacksonObjectMapper().writeValueAsString(request)
        return jacksonObjectMapper().readValue(json, CallSetting::class.java)
    }
}
