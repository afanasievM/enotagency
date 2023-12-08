package ua.com.enotagency.telegram.service

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.stereotype.Service
import ua.com.enotagency.dto.GetCallRequest
import ua.com.enotagency.telegram.bot.TelegramBot

@Service
class ChannelService(
    private val bot: TelegramBot,
    @Value("\${telegram.bot.channelId}")
    private val channelId: Long,
    private val buildProperties: BuildProperties
) {

    fun sendRequestNumber(request: GetCallRequest) {
        request.externalNumber?.let { sendMessage(it) }
    }

    fun sendMessage(message: String) {
        bot.sendMessage(channelId, message)
    }

    @PostConstruct
    private fun sendVersion() {
        bot.sendMessage(channelId, "Використовується версія ${buildProperties.version}")
    }
}