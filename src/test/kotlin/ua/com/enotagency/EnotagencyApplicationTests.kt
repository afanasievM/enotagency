package ua.com.enotagency

import org.junit.jupiter.api.Test

class EnotagencyApplicationTests {

    @Test
    fun contextLoads() {
        val string = "callDetails[historyData][0][employeeData][name]"

        val parsed = string.substringAfter("][").substringBefore("]")

        // Print the JSON object
        println(parsed)
    }
}
