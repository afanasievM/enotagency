package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.builder.BinotelCallRequestBuilder
import ua.com.enotagency.dto.BinotelSuccessResponse
import ua.com.enotagency.dto.CallCompleted
import ua.com.enotagency.dto.GetCallRequest
import ua.com.enotagency.service.TrelloService
import ua.com.enotagency.telegram.service.ChannelService

@RestController
class BinotelController(
    private val channelService: ChannelService,
    private val trelloService: TrelloService
    ) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/binotel/calls/incoming", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun resolveIncomingBinotel(@RequestParam request: Map<String, String>): BinotelSuccessResponse {
        log.info("Recieved:\n$request")
        val requestObj = BinotelCallRequestBuilder.build(request)
        when(requestObj){
            is CallCompleted -> trelloService.createCallCard(requestObj)
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
