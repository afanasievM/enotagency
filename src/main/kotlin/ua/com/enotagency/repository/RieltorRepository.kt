package ua.com.enotagency.repository

import com.google.api.services.sheets.v4.Sheets
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

interface RieltorRepository : DescriptionRepository {
}

@Repository
class RieltorRepositoryImpl(
    sheets: Sheets,
    @Value("\${rieltor.spreadSheet.id}") spreadSheetId: String
) : RieltorRepository, DescriptionRepositoryImpl(sheets, spreadSheetId) {
    override val listName = LIST_NAME

    companion object {
        const val LIST_NAME = "Rieltor"
    }
}
