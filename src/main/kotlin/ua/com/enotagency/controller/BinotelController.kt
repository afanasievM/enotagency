package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.dto.BinotelSuccessResponse
import ua.com.enotagency.dto.CallRequest
import ua.com.enotagency.dto.GetCallRequest
import ua.com.enotagency.telegram.service.ChannelService

@RestController
class BinotelController(private val channelService: ChannelService) {
    private val log = LoggerFactory.getLogger(this.javaClass)


    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun resolveIncomingBinotel(@RequestBody request: CallRequest): BinotelSuccessResponse {
        log.info("Recieved")
        println(request)
//        request.forEach{log.info("key:${it.key} -> value:${it.value}")}
//        log.info("Recieved $request")
        when (request) {

//            request.callCompleted != null -> log.info("Call completed")
        }
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel/getCall/acs", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun getWebCalls(@ModelAttribute request: GetCallRequest): BinotelSuccessResponse {
        log.info("Recieved $request")
        channelService.sendRequestNumber(request)
        return BinotelSuccessResponse()
    }
}
