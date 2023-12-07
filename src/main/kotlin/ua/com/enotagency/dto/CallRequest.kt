package ua.com.enotagency.dto

data class CallRequest(
    val callPush: CallPush?,
    val callSetting: CallSetting?,
    val callCompleted: CallCompleted?
)