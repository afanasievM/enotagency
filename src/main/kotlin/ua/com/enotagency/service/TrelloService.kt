package ua.com.enotagency.service

import com.julienvey.trello.Trello
import com.julienvey.trello.domain.Card
import jakarta.annotation.PostConstruct
import java.time.Instant
import java.util.Date
import java.util.TreeSet
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ua.com.enotagency.dto.CallCompleted
import ua.com.enotagency.repository.RieltorRepository

@Service
class TrelloService(
    private val trelloClient: Trello,
    private val rieltorRepository: RieltorRepository
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
            newCard.desc = CARD_DESCRIPTION_TEMPLATE
            newCard.due = Date.from(Instant.now().plusSeconds(CARD_DUE_TIME_SECONDS))
            trelloClient.createCard(callListId, newCard)
        }
    }

    fun getCardById(cardId: String) = trelloClient.getBoardCard(boardId, cardId)!!

    fun removeCardById(cardId: String) = trelloClient.deleteCard(cardId)

    // TODO MAKE CALLTYPE ENUMS
    private fun isInCall(requestObj: CallCompleted) = requestObj.callDetails?.callType?.equals("0")!!

    private fun isLeadCallNumber(requestObj: CallCompleted) = filteringNumbers
        .contains(requestObj.callDetails?.pbxNumberData?.number)

    private fun getBoard() = trelloClient.getBoard(boardId)

    companion object {
        private const val LIST_CALLS_NAME = "Звонки"
        private const val CARD_DESCRIPTION_TEMPLATE = "Имя:\n" +
                "Количество комнат:\n" +
                "Район:\n" +
                "Бюджет:\n" +
                "Этаж:\n" +
                "Состояние:\n" +
                "Примечание 1:\n" +
                "Примечание 2:"
        private const val CARD_DUE_TIME_SECONDS = 60 * 60 * 24L
    }
}
