package ua.com.enotagency.repository

import com.google.api.services.sheets.v4.Sheets
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

interface HorseRepository : DescriptionRepository {
}

@Repository
class HorseRepositoryImpl(
    sheets: Sheets,
    @Value("\${horse.spreadSheet.id}") spreadSheetId: String
) : HorseRepository, DescriptionRepositoryImpl(sheets, spreadSheetId) {
    override val listName = LIST_NAME

    companion object {
        const val LIST_NAME = "Horses"
    }
}