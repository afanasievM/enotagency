package ua.com.enotagency.builder

import org.springframework.stereotype.Component
import ua.com.enotagency.dto.CallCompleted
import ua.com.enotagency.dto.CallDetails
import ua.com.enotagency.dto.EmployeeData
import ua.com.enotagency.dto.HistoryData
import ua.com.enotagency.dto.PbxNumberData
import ua.com.enotagency.dto.enum.BinotelRequestType

@Component
class CallCompletedBuilder : BinotelRequestBuilder {
    override val requestType = BinotelRequestType.API_CALL_COMPLETED

    override fun build(request: Map<String, String>): CallCompleted {
        return CallCompleted(
            language = request["language"],
            requestType = requestType,
            callDetails = buildCallDetails(request),
            attemptsCounter = request["attemptsCounter"],
        )
    }

    private fun buildCallDetails(request: Map<String, String>): CallDetails {
        return CallDetails(
            companyID = request["callDetails[companyID]"],
            generalCallID = request["callDetails[generalCallID]"],
            callID = request["callDetails[callID]"],
            startTime = request["callDetails[startTime]"]?.toLong(),
            callType = request["callDetails[callType]"],
            internalNumber = request["callDetails[internalNumber]"],
            internalAdditionalData = request["callDetails[internalAdditionalData]"],
            externalNumber = request["callDetails[externalNumber]"],
            waitsec = request["callDetails[waitsec]"]?.toInt(),
            billsec = request["callDetails[billsec]"]?.toInt(),
            disposition = request["callDetails[disposition]"],
            recordingStatus = request["callDetails[recordingStatus]"],
            isNewCall = request["callDetails[isNewCall]"],
            whoHungUp = request["callDetails[whoHungUp]"],
            customerData = request["callDetails[customerData]"],
            employeeData = EmployeeData(
                request["callDetails[employeeData][name]"], request["callDetails[employeeData][email]"]
            ),
            pbxNumberData = PbxNumberData(
                request["callDetails[pbxNumberData][number]"]?.toLong(),
                request["callDetails[pbxNumberData][name]=SIP Lifecell"]
            ),
            linkToCallRecordOverlayInMyBusiness = request["callDetails[linkToCallRecordOverlayInMyBusiness]"],
            linkToCallRecordInMyBusiness = request["callDetails[linkToCallRecordInMyBusiness]"],
            historyData = buildHistoryData(request)
        )
    }

    private fun buildHistoryData(request: Map<String, String>): List<HistoryData> {
        val list = mutableListOf<HistoryData>()
        val keys = request
            .filter { it.key.contains("historyData") }
            .keys
            .map { it.substringAfter("][").substringBefore("]").toInt() }
            .distinct()
        keys.forEach {
            list.add(
                HistoryData(
                    waitsec = request["callDetails[historyData][$it][waitsec]"]?.toInt(),
                    billsec = request["callDetails[historyData][$it][billsec]"]?.toInt(),
                    disposition = request["callDetails[historyData][$it][disposition]"],
                    internalNumber = request["callDetails[historyData][$it][internalNumber]"],
                    internalAdditionalData = request["callDetails[historyData][$it][internalAdditionalData]"],
                    employeeData = EmployeeData(
                        request["callDetails[historyData][$it][employeeData][name]"],
                        request["callDetails[historyData][$it][employeeData][email]"]
                    ),
                )
            )
        }
        return list
    }
}
