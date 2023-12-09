package ua.com.enotagency.builder

import ua.com.enotagency.dto.BinotelCallRequest
import ua.com.enotagency.dto.enum.BinotelRequestType

interface BinotelRequestBuilder {

    val requestType: BinotelRequestType

    fun build(request: Map<String, String>): BinotelCallRequest
}
