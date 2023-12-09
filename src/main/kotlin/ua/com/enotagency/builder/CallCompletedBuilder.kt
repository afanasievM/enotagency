//package ua.com.enotagency.builder
//
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.google.gson.Gson
//import org.springframework.stereotype.Component
//import ua.com.enotagency.dto.CallCompleted
//import ua.com.enotagency.dto.enum.BinotelRequestType
//
//@Component
//class CallCompletedBuilder : BinotelRequestBuilder {
//    override val requestType = BinotelRequestType.API_CALL_COMPLETED
//
//    private val gson = Gson()
//
//    override fun build(request: Map<String, String>): CallCompleted {
//        val json = jacksonObjectMapper().writeValueAsString(request)
//        println(json)
//        return CallCompleted(
//            language = request["language"],
//            requestType = requestType,
//            callDetails = null,
//            attemptsCounter = request["attemptsCounter"]
//        )
//    }
//}
