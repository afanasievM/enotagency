package ua.com.enotagency.mapper

import com.julienvey.trello.domain.Card
import ua.com.enotagency.entity.CardPerson

object CardPersonMapper {
    fun map(card: Card): CardPerson {
        val description = card.desc.split("\n").associate {
            it.substringBefore(DELIMITER) to it.substringAfter(DELIMITER).trim()
        }
        return CardPerson(
            name = description[NAME_PATTERN],
            phoneNumber = card.name.substringBefore(" ").trim(),
            roomNumber = description[ROOM_NUMBER_PATTERN],
            region = description[REGION_PATTERN],
            budget = description[BUDGET_PATTERN],
            floor = description[FLOOR_PATTERN],
            condition = description[CONDITION_PATTERN],
            notesFirst = description[NOTES_FIRST_PATTERN],
            notesSecond = description[NOTES_SECOND_PATTERN]
        )
    }

    private const val DELIMITER = ":"
    private const val NAME_PATTERN = "Имя:"
    private const val ROOM_NUMBER_PATTERN = "Количество комнат:"
    private const val REGION_PATTERN = "Район:"
    private const val BUDGET_PATTERN = "Бюджет:"
    private const val FLOOR_PATTERN = "Этаж:"
    private const val CONDITION_PATTERN = "Состояние:"
    private const val NOTES_FIRST_PATTERN = "Примечание 1:"
    private const val NOTES_SECOND_PATTERN = "Примечание 2:"
}
