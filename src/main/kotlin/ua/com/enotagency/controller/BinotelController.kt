package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.dto.BinotelSuccessResponse
import ua.com.enotagency.dto.CallRequest
import ua.com.enotagency.dto.GetCallRequest
import ua.com.enotagency.telegram.service.ChannelService

@RestController
class BinotelController(private val channelService: ChannelService) {
    private val log = LoggerFactory.getLogger(this.javaClass)


    @PostMapping("/binotel/calls/incoming")
    fun resolveIncomingBinotel(@ModelAttribute request: CallRequest): BinotelSuccessResponse {
        when{
            request.callCompleted != null -> log.info("Call completed")
        }
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel/getCall/acs")
    fun getWebCalls(@ModelAttribute request: GetCallRequest): BinotelSuccessResponse {
        channelService.sendRequestNumber(request)
        return BinotelSuccessResponse()
    }

    @GetMapping("/binotel")
    fun get1WebCalls(@RequestParam request: String): BinotelSuccessResponse {
        println("get1")
        println(request)
        return BinotelSuccessResponse()
    }

    @GetMapping("/")
    fun get2WebCalls(@RequestParam request: String): BinotelSuccessResponse {
        println("get2")
        println(request)
        return BinotelSuccessResponse()
    }

    @PostMapping("/binotel")
    fun post1WebCalls(@RequestParam request: String): BinotelSuccessResponse {
        println("post1")
        println(request)
        return BinotelSuccessResponse()
    }

    @PostMapping("/")
    fun post2WebCalls(@RequestParam request: String): BinotelSuccessResponse {
        println("post2")
        println(request)
        return BinotelSuccessResponse()
    }

}
