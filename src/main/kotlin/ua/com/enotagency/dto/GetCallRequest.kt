package ua.com.enotagency.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetCallRequest(
    val externalNumber: String?,
    val GetCallID: String?,
    val domain: String?,
    val companyID: String?,
    val requestType: String?,
    val additionalData: String?,
    val language: String?
)
