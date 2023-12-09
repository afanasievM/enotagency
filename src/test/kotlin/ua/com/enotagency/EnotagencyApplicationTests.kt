package ua.com.enotagency

import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import org.junit.jupiter.api.Test

class EnotagencyApplicationTests {

    @Test
    fun contextLoads() {
        val string =
            "requestType=apiCallCompleted&attemptsCounter=5&callDetails%5BcompanyID%5D=62770&callDetails%5BgeneralCallID%5D=4683310794&callDetails%5BcallID%5D=4683310794&callDetails%5BstartTime%5D=1702107076&callDetails%5BcallType%5D=0&callDetails%5BinternalNumber%5D=901&callDetails%5BinternalAdditionalData%5D=&callDetails%5BexternalNumber%5D=0990272198&callDetails%5Bwaitsec%5D=13&callDetails%5Bbillsec%5D=17&callDetails%5Bdisposition%5D=ANSWER&callDetails%5BrecordingStatus%5D=uploaded&callDetails%5BisNewCall%5D=0&callDetails%5BwhoHungUp%5D=&callDetails%5BcustomerData%5D=&callDetails%5BemployeeData%5D%5Bname%5D=%D0%AF%D1%80%D0%BE%D1%81%D0%BB%D0%B0%D0%B2+2&callDetails%5BemployeeData%5D%5Bemail%5D=stuiunger2792%40gmail.com&callDetails%5BpbxNumberData%5D%5Bnumber%5D=0502792021&callDetails%5BhistoryData%5D%5B0%5D%5Bwaitsec%5D=13&callDetails%5BhistoryData%5D%5B0%5D%5Bbillsec%5D=17&callDetails%5BhistoryData%5D%5B0%5D%5Bdisposition%5D=ANSWER&callDetails%5BhistoryData%5D%5B0%5D%5BinternalNumber%5D=901&callDetails%5BhistoryData%5D%5B0%5D%5BinternalAdditionalData%5D=&callDetails%5BhistoryData%5D%5B0%5D%5BemployeeData%5D%5Bname%5D=%D0%AF%D1%80%D0%BE%D1%81%D0%BB%D0%B0%D0%B2+2&callDetails%5BhistoryData%5D%5B0%5D%5BemployeeData%5D%5Bemail%5D=stuiunger2792%40gmail.com&callDetails%5BlinkToCallRecordOverlayInMyBusiness%5D=https%3A%2F%2Fmy.binotel.ua%2F%3Fmodule%3Dhistory%26subject%3D0990272198%26sacte%3Dovl-link-pb-4683310794&callDetails%5BlinkToCallRecordInMyBusiness%5D=https%3A%2F%2Fmy.binotel.ua%2F%3Fmodule%3Dcdrs%26action%3DgenerateFile%26fileName%3D4683310794.mp3%26callDate%3D2023-09-12_09%3A31%26customerNumber%3D0990272198%26callType%3Dincoming&language=ru"

        val decoded = URLDecoder.decode(string, StandardCharsets.UTF_8)
        val objectMapper = ObjectMapper()
        println(decoded)
//		jacksonObjectMapper()
//			.readValue(url,CallCompleted::class.java)
        val jsonMap = convertToJsonObject(decoded, objectMapper)

        // Print the JSON object
        println(jsonMap)
    }

    fun convertToJsonObject(input: String, objectMapper: ObjectMapper): String {
        val map = input.split("&")
            .map { it.split("=") }
            .associate { it[0] to it[1] }
            .mapKeys { it.key.replace("%5B", "[").replace("%5D", "]") }
            .mapValues { it.value.replace("+", " ") }

        return objectMapper.writeValueAsString(map)
    }

}
