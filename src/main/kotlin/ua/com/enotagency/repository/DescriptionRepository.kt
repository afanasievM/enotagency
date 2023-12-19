package ua.com.enotagency.repository

import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import ua.com.enotagency.entity.CardPerson

interface DescriptionRepository {
    fun save(cardPerson: CardPerson)
    fun findAll(): List<List<Any>>
}

abstract class DescriptionRepositoryImpl(
    private val sheets: Sheets,
    private val spreadSheetId: String
) : DescriptionRepository {

    abstract val listName: String

    override fun findAll() = sheets.spreadsheets().values().get(spreadSheetId, listName).execute().getValues().orEmpty()

    override fun save(cardPerson: CardPerson) {
        val valueRange = createValueRange(cardPerson)
        sheets.spreadsheets()
            .values()
            .append(spreadSheetId, listName, valueRange)
            .setValueInputOption("RAW")
            .execute()
    }

    private fun createValueRange(cardPerson: CardPerson): ValueRange = ValueRange().apply {
        range = listName
        majorDimension = "ROWS"
        setValues(
            listOf(
                listOf(
                    cardPerson.phoneNumber,
                    cardPerson.name,
                    cardPerson.roomNumber,
                    cardPerson.region,
                    cardPerson.budget,
                    cardPerson.floor,
                    cardPerson.condition,
                    cardPerson.notesFirst,
                    cardPerson.notesSecond,
                )
            )
        )
    }
}
