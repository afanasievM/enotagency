package ua.com.enotagency.controller

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.web.servlet.MockMvc
import ua.com.enotagency.telegram.bot.TelegramBot

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
class BinotelControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    val telegramBot: TelegramBot = mock(TelegramBot::class.java)

    @Test
    fun test(){

    }
}
