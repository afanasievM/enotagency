package ua.com.enotagency.dto

import ua.com.enotagency.dto.enum.BinotelRequestType

data class ReceivedTheCall(
    val pbxNumber: String?,
    val externalNumber: String?,
    val internalNumber: String?,
    val generalCallID: String?,
    val callType: String?,
    val companyID: String?,
    override val requestType: BinotelRequestType,
    val method: String?,
    val didNumber: String?,
    val did: String?,
    val srcNumber: String?
) : BinotelCallRequest(requestType)

data class AnsweredTheCall(
    val pbxNumber: String?,
    val externalNumber: String?,
    val internalNumber: String?,
    val generalCallID: String?,
    val callType: String?,
    val companyID: String?,
    override val requestType: BinotelRequestType
) : BinotelCallRequest(requestType)

data class HangupTheCall(
    val generalCallID: String?,
    val billsec: String?,
    val disposition: String?,
    override val requestType: BinotelRequestType,
    val method: String?
) : BinotelCallRequest(requestType)
