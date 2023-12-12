package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.executor.Executor
import ua.com.enotagency.service.TrelloService

@RestController
class TrelloController(
    private val trelloService: TrelloService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("trello/card/horse", consumes = arrayOf(MediaType.ALL_VALUE))
    @ResponseStatus(HttpStatus.OK)
    fun resolveIncomingBinotel(@RequestParam cardId: String) {
        log.info("Recieved:\n$cardId")
        Executor.execute { trelloService.getCardById(cardId) }
    }
}
