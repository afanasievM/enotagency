package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.dto.BinotelSuccessResponse
import ua.com.enotagency.dto.CallCompleted
import ua.com.enotagency.dto.CallSetting
import ua.com.enotagency.dto.GetCallRequest
import ua.com.enotagency.dto.ReceivedTheCall
import ua.com.enotagency.telegram.service.ChannelService

@RestController
class BinotelController(private val channelService: ChannelService) {
    private val log = LoggerFactory.getLogger(this.javaClass)


//    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
//    fun resolveIncomingBinotel(@ModelAttribute request: Any): BinotelSuccessResponse {
//        when (request) {
//            (request is CallSetting) -> log.info("Call setting recieved:\n${request as CallSetting}")
//            (request is ReceivedTheCall) -> log.info("Call setting recieved:\n${request as ReceivedTheCall}")
//            (request is CallCompleted) -> log.info("Call setting recieved:\n${request as CallCompleted}")
//            (request is AnsweredTheCall) -> log.info("Call setting recieved:\n${request as AnsweredTheCall}")
//            (request is HangupTheCall) -> log.info("Call setting recieved:\n${request as HangupTheCall}")
//            else -> log.info("Unknown type \n${request.toString()}")
//        }
//        return BinotelSuccessResponse()
//    }

    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun resolveReceivedCall(@RequestBody request: ReceivedTheCall): BinotelSuccessResponse {
        log.info("ReceivedTheCall recieved:\n${request}")
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun resolveCallCompleted(@RequestBody request: CallCompleted): BinotelSuccessResponse {
        log.info("CallCompleted recieved:\n${request}")
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun resolveCallSettings(@RequestBody request: CallSetting): BinotelSuccessResponse {
        log.info("CallSetting recieved:\n${request}")
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel/getCall/acs", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun getWebCalls(@ModelAttribute request: GetCallRequest): BinotelSuccessResponse {
        log.info("Recieved $request")
        channelService.sendRequestNumber(request)
        return BinotelSuccessResponse()
    }
}
