package ua.com.enotagency.dto.enum

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class BinotelRequestType(private val requestType: String) {
    API_CALL_SETTINGS("apiCallSettings"),
    RECEIVED_THE_CALL("receivedTheCall"),
    ANSWERED_THE_CALL("answeredTheCall"),
    HANGUP_THE_CALL("hangupTheCall"),
    API_CALL_COMPLETED("apiCallCompleted");

    override fun toString(): String {
        return when (this) {
            API_CALL_SETTINGS -> "apiCallSettings"
            RECEIVED_THE_CALL -> "receivedTheCall"
            ANSWERED_THE_CALL -> "answeredTheCall"
            HANGUP_THE_CALL -> "hangupTheCall"
            API_CALL_COMPLETED -> "apiCallCompleted"
        }
    }

    @JsonValue
    fun getRequestType(): String {
        return requestType
    }

    @JsonCreator
    fun fromString(requestType: String): BinotelRequestType {
        return entries.firstOrNull { it.requestType == requestType }
            ?: throw IllegalArgumentException("Unknown status: $requestType")
    }
}
