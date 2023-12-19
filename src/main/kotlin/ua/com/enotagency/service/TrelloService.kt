package ua.com.enotagency.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.julienvey.trello.Trello
import com.julienvey.trello.domain.Card
import jakarta.annotation.PostConstruct
import java.util.TreeSet
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ua.com.enotagency.dto.CallCompleted
import ua.com.enotagency.repository.HorseRepository

@Service
class TrelloService(
    private val trelloClient: Trello,
    private val horseRepository: HorseRepository
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${trello.boardId}")
    lateinit var boardId: String
    private lateinit var callListId: String

    @Value("\${filtering.numbers}")
    lateinit var filteringNumbers: TreeSet<String>


    @PostConstruct
    private fun getCallListId() {
        callListId = trelloClient.getBoard(boardId)
            .fetchLists()
            .filter {
                it.name.contains(LIST_CALLS_NAME)
            }
            .map { it.id }
            .first()
    }

    fun createCallCard(requestObj: CallCompleted) {
        if (!isInCall(requestObj) || !isLeadCallNumber(requestObj)) {
            return
        }
        val board = getBoard()
        val callsCards = board.fetchCards().filter { it.idList == callListId }
        val externalNumber = requestObj.callDetails?.externalNumber
        callsCards.filter { card ->
            if (externalNumber != null) {
                card.name.contains(externalNumber)
            } else {
                false
            }
        }.ifEmpty {
            val newCard = Card()
            newCard.name = externalNumber
            newCard.idBoard = boardId
            trelloClient.createCard(callListId, newCard)
        }
    }

    fun getCardById(cardId: String) {
        log.info("cardId:$cardId")
        val card = trelloClient.getBoardCard(boardId, cardId)
        log.info(jacksonObjectMapper().writeValueAsString(card))
        horseRepository.findAll()
    }

    // TODO MAKE CALLTYPE ENUMS
    private fun isInCall(requestObj: CallCompleted) = requestObj.callDetails?.callType?.equals("0")!!

    private fun isLeadCallNumber(requestObj: CallCompleted) = filteringNumbers
        .contains(requestObj.callDetails?.pbxNumberData?.number)

    private fun getBoard() = trelloClient.getBoard(boardId)

    companion object {
        private const val LIST_CALLS_NAME = "Звонки"
    }
}
