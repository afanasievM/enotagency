package ua.com.enotagency.dto

data class CallSetting(
    val pbxNumber: String?,
    val externalNumber: String?,
    val companyID: String?,
    val callType: String?,
    val requestType: String?,
    val language: String?,
    val didNumber: String?,
    val srcNumber: String?
)
