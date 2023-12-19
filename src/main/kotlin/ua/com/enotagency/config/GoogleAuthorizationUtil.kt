package ua.com.enotagency.config

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.IOException
import java.security.GeneralSecurityException
import java.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleAuthorizationUtil(
    private val buildProperties: BuildProperties,
    @Value("\${credentials.file.path}") private val credentialsFilePath: String
) {
    @Bean
    @Throws(IOException::class, GeneralSecurityException::class)
    fun credential(): GoogleCredentials {
        val credentialsStream = FileInputStream(credentialsFilePath)
        return ServiceAccountCredentials.fromStream(credentialsStream).createScoped(listOf(SheetsScopes.SPREADSHEETS))
    }

    @Bean
    @Throws(IOException::class, GeneralSecurityException::class)
    fun getSheetsService(credential: GoogleCredentials): Sheets {
        return Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            HttpCredentialsAdapter(credential)
        )
            .setApplicationName(buildProperties.name)
            .build()
    }
}