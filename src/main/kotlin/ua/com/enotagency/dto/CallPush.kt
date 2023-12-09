package ua.com.enotagency.dto

import ua.com.enotagency.dto.enum.BinotelRequestType

data class ReceivedTheCall(
    val pbxNumber: String?,
    val externalNumber: String?,
    val internalNumber: Int?,
    val generalCallID: Long?,
    val callType: Int?,
    val companyID: Int?,
    override val requestType: BinotelRequestType,
    val method: String?,
    val didNumber: String?,
    val did: String?,
    val srcNumber: String?
) : BinotelCallRequest(requestType)

data class AnsweredTheCall(
    val pbxNumber: String?,
    val externalNumber: String?,
    val internalNumber: Int?,
    val generalCallID: Long?,
    val callType: Int?,
    val companyID: Int?,
    override val requestType: BinotelRequestType
) : BinotelCallRequest(requestType)

data class HangupTheCall(
    val generalCallID: Long?,
    val billsec: Int?,
    val disposition: String?,
    override val requestType: BinotelRequestType,
    val method: String?
) : BinotelCallRequest(requestType)
