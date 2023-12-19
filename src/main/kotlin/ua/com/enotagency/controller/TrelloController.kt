package ua.com.enotagency.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ua.com.enotagency.executor.Executor
import ua.com.enotagency.service.HorseService
import ua.com.enotagency.service.RieltorService

@RestController
class TrelloController(
    private val horseService: HorseService,
    private val rieltorService: RieltorService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("trello/card/horse", consumes = arrayOf(MediaType.ALL_VALUE))
    @ResponseStatus(HttpStatus.OK)
    fun saveHorse(@RequestBody requestBody: Map<String,String>) {
        log.info("Recieved:\n$requestBody")
        Executor.execute { horseService.saveDescription(requestBody["cardId"]!!) }
    }

    @PostMapping("trello/card/rieltor", consumes = arrayOf(MediaType.ALL_VALUE))
    @ResponseStatus(HttpStatus.OK)
    fun saveRieltor(@RequestBody requestBody: Map<String,String>) {
        log.info("Recieved:\n$requestBody")
        Executor.execute { rieltorService.saveDescription(requestBody["cardId"]!!) }
    }
}
