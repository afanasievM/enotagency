package ua.com.enotagency.telegram.bot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.channel
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TelegramBot(
    @Value("\${telegram.bot.token}")
    private val token: String,
    @Value("\${telegram.bot.channelId}")
    private val channelId: Long
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
}
