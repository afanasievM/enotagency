package ua.com.enotagency.dto

data class ReceivedTheCall(
    val pbxNumber: String?,
    val externalNumber: String?,
    val internalNumber: Int?,
    val generalCallID: Long?,
    val callType: Int?,
    val companyID: Int?,
    val requestType: String?,
    val method: String?,
    val didNumber: String?,
    val did: String?,
    val srcNumber: String?
)

data class AnsweredTheCall(
    val pbxNumber: String?,
    val externalNumber: String?,
    val internalNumber: Int?,
    val generalCallID: Long?,
    val callType: Int?,
    val companyID: Int?,
    val requestType: String?
)

data class HangupTheCall(
    val generalCallID: Long?,
    val billsec: Int?,
    val disposition: String?,
    val requestType: String?,
    val method: String?
)
