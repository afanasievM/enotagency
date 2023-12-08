package ua.com.enotagency.dto

data class GetCallRequest(
    val externalNumber: String?,
    val GetCallID: String?,
    val domain: String?,
    val companyID: String?,
    val requestType: String?,
    val additionalData: String?,
    val language: String?
)
