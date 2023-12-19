package ua.com.enotagency.repository

import com.google.api.services.sheets.v4.Sheets
import ua.com.enotagency.entity.CardPerson

interface DescriptionRepository {
    fun save(cardPerson: CardPerson)
    fun findAll()
}

abstract class DescriptionRepositoryImpl(
    private val sheets: Sheets,
    private val spreadSheetId: String
) : DescriptionRepository {

    override fun findAll() {
        sheets.spreadsheets().values().get(spreadSheetId, "Horses").execute().getValues().forEach { println(it) }
    }

    override fun save(cardPerson: CardPerson) {
        sheets.spreadsheets().values().batchGet(spreadSheetId)
    }
}
