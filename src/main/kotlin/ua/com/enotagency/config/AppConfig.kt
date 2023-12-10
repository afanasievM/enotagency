package ua.com.enotagency.config

import com.julienvey.trello.Trello
import com.julienvey.trello.impl.TrelloImpl
import com.julienvey.trello.impl.http.ApacheHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ua.com.enotagency.builder.BinotelCallRequestBuilder
import ua.com.enotagency.builder.BinotelRequestBuilder

@Configuration
class AppConfig {
    @Bean
    fun binotelCallRequestBuilder(builders: List<BinotelRequestBuilder>): BinotelCallRequestBuilder {
        BinotelCallRequestBuilder.init(builders)
        return BinotelCallRequestBuilder
    }

    @Value("\${trello.trelloKey}")
    private val trelloKey: String? = null

    @Value("\${trello.trelloAccessToken}")
    private val trelloAccessToken: String? = null

    @Bean
    fun trelloClient(): Trello {
        return TrelloImpl(trelloKey, trelloAccessToken, ApacheHttpClient())
    }
}
