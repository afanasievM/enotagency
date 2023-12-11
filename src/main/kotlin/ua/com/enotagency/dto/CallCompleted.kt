package ua.com.enotagency.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import ua.com.enotagency.dto.enum.BinotelRequestType

@JsonIgnoreProperties(ignoreUnknown = true)
data class CallCompleted(
    override val requestType: BinotelRequestType,
    val attemptsCounter: String?,
    val callDetails: CallDetails?,
    val language: String?
) : BinotelCallRequest(requestType)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CallDetails(
    val companyID: String?,
    val generalCallID: String?,
    val callID: String?,
    val startTime: Long?,
    val callType: String?,
    val internalNumber: String?,
    val internalAdditionalData: String?,
    val externalNumber: String?,
    val waitsec: Int?,
    val billsec: Int?,
    val disposition: String?,
    val recordingStatus: String?,
    val isNewCall: String?,
    val whoHungUp: String?,
    val customerData: String?,
    val employeeData: EmployeeData?,
    val pbxNumberData: PbxNumberData?,
    val historyData: List<HistoryData> = emptyList(),
    val linkToCallRecordOverlayInMyBusiness: String?,
    val linkToCallRecordInMyBusiness: String?,
)

data class EmployeeData(
    val name: String?,
    val email: String?
)

data class PbxNumberData(
    val number: Long?,
    val name: String?
)

data class HistoryData(
    val waitsec: Int?,
    val billsec: Int?,
    val disposition: String?,
    val internalNumber: String?,
    val internalAdditionalData: String?,
    val employeeData: EmployeeData?
)
