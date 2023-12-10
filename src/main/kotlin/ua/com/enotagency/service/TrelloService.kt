package ua.com.enotagency.service

import com.julienvey.trello.Trello
import com.julienvey.trello.domain.Card
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ua.com.enotagency.dto.CallCompleted

@Service
class TrelloService(private val trelloClient: Trello) {
    @Value("\${trello.boardId}")
    lateinit var boardId: String
    private lateinit var callListId: String


    @PostConstruct
    private fun getCallListId() {
        callListId = trelloClient.getBoard(boardId)
            .fetchLists()
            .filter {
                it.name.contains(LIST_CALLS_NAME)
            }
            .map { it.id }
            .first
    }

    fun createCallCard(requestObj: CallCompleted) {
        if (!isInCall(requestObj)) {
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
            newCard.pos=2
            trelloClient.createCard(callListId, newCard)
        }
    }

    // TODO MAKE CALLTYPE ENUMS
    private fun isInCall(requestObj: CallCompleted) = requestObj.callDetails?.callType?.equals("0")!!

    private fun getBoard() = trelloClient.getBoard(boardId)

    companion object {
        private const val LIST_CALLS_NAME = "Звонки"
    }
}
