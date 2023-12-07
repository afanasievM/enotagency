package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
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


    @PostMapping("/binotel/calls/incoming")
    fun resolveIncomingBinotel(@RequestBody request: CallRequest): BinotelSuccessResponse {
        when{
            request.callCompleted != null -> log.info("Call completed")
        }
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel/getCall/acs")
    fun getWebCalls(@RequestBody request: GetCallRequest): BinotelSuccessResponse {
        channelService.sendRequestNumber(request)
        return BinotelSuccessResponse()
    }

}
