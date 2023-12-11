package ua.com.enotagency.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import ua.com.enotagency.dto.enum.BinotelRequestType

@JsonIgnoreProperties(ignoreUnknown = true)
data class CallSetting(
    val pbxNumber: String?,
    val externalNumber: String?,
    val companyID: String?,
    val callType: String?,
    val language: String?,
    val didNumber: String?,
    val srcNumber: String?,
    val trunkNumber: String?,
    override val requestType: BinotelRequestType
) : BinotelCallRequest(requestType)
