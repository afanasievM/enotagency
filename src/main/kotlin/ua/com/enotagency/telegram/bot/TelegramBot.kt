package ua.com.enotagency.telegram.bot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.entities.ChatId
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TelegramBot(@Value("\${telegram.bot.token}") private val token: String) {
    private val bot = bot {
        token = this@TelegramBot.token
    }

    init {
        bot.startPolling()
    }

    fun sendMessage(channelId: Long, message: String) {
        bot.sendMessage(ChatId.fromId(channelId), message)
    }
}
