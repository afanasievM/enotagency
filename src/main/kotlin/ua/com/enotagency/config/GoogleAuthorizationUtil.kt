package ua.com.enotagency.config

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File
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
    fun credential(): Credential {
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(),
            FileReader(credentialsFilePath)
        )

        val scopes = listOf(SheetsScopes.SPREADSHEETS)

        val googleAuthorizationCodeFlow: GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            clientSecrets,
            scopes
        )
            .setDataStoreFactory(FileDataStoreFactory(File(System.getProperty("user.dir") + "/GoogleAPIKey")))
            .setAccessType("offline")
            .build()

        return AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, LocalServerReceiver()).authorize("user")
    }

    @Bean
    @Throws(IOException::class, GeneralSecurityException::class)
    fun getSheetsService(credential: Credential): Sheets {
        return Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential
        )
            .setApplicationName(buildProperties.name)
            .build()
    }
}