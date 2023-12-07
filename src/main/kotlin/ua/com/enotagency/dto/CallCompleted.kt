package ua.com.enotagency.dto

data class CallCompleted(
    val requestType: String,
    val attemptsCounter: Int,
    val callDetails: CallDetails
)

data class CallDetails(
    val companyID: String,
    val generalCallID: String,
    val callID: String,
    val startTime: Long,
    val callType: String,
    val internalNumber: String,
    val internalAdditionalData: String,
    val externalNumber: String,
    val waitSec: Int,
    val billSec: Int,
    val disposition: String,
    val recordingStatus: String,
    val isNewCall: String,
    val whoHungUp: String,
    val customerData: CustomerData?,
    val employeeData: EmployeeData?,
    val pbxNumberData: PbxNumberData?,
    val historyData: List<HistoryData>,
    val customerDataFromOutside: CustomerDataFromOutside?
)

data class CustomerData(
    val id: String,
    val name: String
)

data class EmployeeData(
    val name: String,
    val email: String
)

data class PbxNumberData(
    val number: String
)

data class HistoryData(
    val waitSec: Int,
    val billSec: Int,
    val disposition: String,
    val internalNumber: String,
    val internalAdditionalData: String,
    val employeeData: EmployeeData
)

data class CustomerDataFromOutside(
    val id: String,
    val externalNumber: String,
    val name: String,
    val linkToCrmUrl: String,
    val linkToCallRecordOverlayInMyBusiness: String,
    val linkToCallRecordInMyBusiness: String
)