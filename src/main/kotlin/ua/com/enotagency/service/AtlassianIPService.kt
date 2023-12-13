package ua.com.enotagency.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.web.client.RestTemplate
import ua.com.enotagency.dto.AtlassianNetworkInfo

open class AtlassianIPService(private val restTemplate: RestTemplate) {

    @Cacheable(cacheNames = [ATLAS_CACHE_NAME], cacheManager = "cacheManager", sync = true)
    open fun getAtlassianIPRanges(): List<String> {
        val networkInfo = restTemplate.getForObject(ATLAS_IP_URL, AtlassianNetworkInfo::class.java)
        return networkInfo!!.items
            .filter { it.product.contains(TRELLO_FILTER) }
            .map { it.cidr }
            .filter { !it.contains(":") }
    }

    companion object {
        const val ATLAS_CACHE_NAME = "atlassianIPCache"
        const val ATLAS_IP_URL = "https://ip-ranges.atlassian.com"
        const val TRELLO_FILTER = "trello"
    }
}
