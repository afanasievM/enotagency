package ua.com.enotagency.telegram.bot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.entities.ChatId
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TelegramBot(
    @Value("\${telegram.bot.token}")
    private val token: String,
    @Value("\${telegram.bot.channelId}")
    private val channelId: Long,
    private val buildProperties: BuildProperties
) {
    private val bot = bot {
        token = this@TelegramBot.token
    }

    init {
        bot.startPolling()
    }

    fun sendMessage(message: String) {
        bot.sendMessage(ChatId.fromId(channelId), message)
    }

    @EventListener(ContextRefreshedEvent::class)
    private fun sendVersion(){
        sendMessage("Використовується версія ${buildProperties.version}")
    }
}
