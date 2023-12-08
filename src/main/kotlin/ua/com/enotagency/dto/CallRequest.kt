package ua.com.enotagency.dto

data class CallRequest(
    val callSetting: CallSetting?,
    val receivedTheCall: ReceivedTheCall?,
    val answeredTheCall: AnsweredTheCall?,
    val hangupTheCall: HangupTheCall?,
    val callCompleted: CallCompleted?
)
