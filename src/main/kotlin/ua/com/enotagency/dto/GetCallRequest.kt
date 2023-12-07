package ua.com.enotagency.dto

data class GetCallRequest(
    val pbxNumber: String,
    val externalNumber: String,
    val companyID: String,
    val callType: String,
    val requestType: String,
    val didNumber: String,
    val srcNumber: String
)
