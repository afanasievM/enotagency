package ua.com.enotagency.service

import org.slf4j.LoggerFactory
import ua.com.enotagency.mapper.CardPersonMapper
import ua.com.enotagency.repository.DescriptionRepository

interface DescriptionService {
    fun saveDescription(cardId: String)
}

abstract class DescriptionServiceImpl(
    private val descriptionRepository: DescriptionRepository,
    private val trelloService: TrelloService
) : DescriptionService {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun saveDescription(cardId: String) {
        val card = trelloService.getCardById(cardId)
        trelloService.removeCardById(cardId)
        val cardPerson = CardPersonMapper.map(card)
        val descriptions = descriptionRepository.findAll().map { it[0] as String }
        if (descriptions.contains(cardPerson.phoneNumber)) {
            log.info("containing number ${cardPerson.phoneNumber}")
            return
        }
        descriptionRepository.save(cardPerson)
        log.info("saving description $cardPerson")
    }
}
