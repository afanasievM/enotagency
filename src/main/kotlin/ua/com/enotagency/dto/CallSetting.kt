package ua.com.enotagency.dto

import ua.com.enotagency.dto.enum.BinotelRequestType

data class CallSetting(
    val pbxNumber: String?,
    val externalNumber: String?,
    val companyID: String?,
    val callType: String?,
    val language: String?,
    val didNumber: String?,
    val srcNumber: String?,
    override val requestType: BinotelRequestType
) : BinotelCallRequest(requestType)
