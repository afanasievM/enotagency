package ua.com.enotagency.dto

data class CallPush(
    val receivedTheCall: ReceivedTheCall?,
    val answeredTheCall: AnsweredTheCall?,
    val hangupTheCall: HangupTheCall?
)

data class ReceivedTheCall(
    val externalNumber: String,
    val internalNumber: String,
    val generalCallID: String,
    val callType: String,
    val companyID: String,
    val requestType: String,
    val method: String,
    val dstNumber: String,
    val extNumber: String
)

data class AnsweredTheCall(
    val externalNumber: String,
    val internalNumber: String,
    val generalCallID: String,
    val callType: String,
    val companyID: String,
    val requestType: String
)

data class HangupTheCall(
    val generalCallID: String,
    val billSec: Int,
    val disposition: String,
    val requestType: String,
    val method: String
)