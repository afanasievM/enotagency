package ua.com.enotagency.builder

import org.springframework.stereotype.Component
import ua.com.enotagency.dto.CallSetting
import ua.com.enotagency.dto.enum.BinotelRequestType

@Component
class CallSettingsBuilder : BinotelRequestBuilder {
    override val requestType = BinotelRequestType.API_CALL_SETTINGS
    override fun build(request: Map<String, String>): CallSetting {
        return CallSetting(
            pbxNumber = request["pbxNumber"],
            externalNumber = request["externalNumber"],
            companyID = request["companyID"],
            callType = request["callType"],
            language = request["language"],
            didNumber = request["didNumber"],
            srcNumber = request["srcNumber"],
            requestType = requestType
        )
    }
}
