package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.dto.BinotelSuccessResponse
import ua.com.enotagency.telegram.service.ChannelService

@RestController
class BinotelController(private val channelService: ChannelService) {
    private val log = LoggerFactory.getLogger(this.javaClass)


//    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    @PostMapping("/**", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun resolveIncomingBinotel(@RequestParam request: Map<String, String>): BinotelSuccessResponse {
        request.forEach{k,v-> println("$k >>>>> $v")}
//        when{
//            request.callCompleted != null -> log.info("Call completed")
//        }
        return BinotelSuccessResponse()
    }

//    @PostMapping("/binotel/getсall/acs", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    @GetMapping("/**", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun getWebCalls(@RequestParam request: Map<String, String>): BinotelSuccessResponse {
        request.forEach{k,v-> println("$k >>>>> $v")}
//        channelService.sendRequestNumber(request)
        return BinotelSuccessResponse()
    }
}
