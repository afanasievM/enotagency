package ua.com.enotagency.service

import java.util.SortedSet
import org.apache.commons.net.util.SubnetUtils
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.client.RestTemplate
import ua.com.enotagency.dto.AtlassianNetworkInfo

open class AllowedIPService(
    private val restTemplate: RestTemplate,
    private val allowedIPs: Set<String>
) {

    @Cacheable(cacheNames = [ATLAS_CACHE_NAME], cacheManager = "cacheManager", sync = true)
    open fun getAllowedIps(): SortedSet<String> {
        val networkInfo = restTemplate.getForObject(ATLAS_IP_URL, AtlassianNetworkInfo::class.java)
        return networkInfo!!.items
            .filter { it.product.contains(TRELLO_FILTER) }
            .map { it.cidr }
            .filter { !it.contains(":") }
            .flatMap {
                SubnetUtils(it).info.allAddresses.asIterable()
            }
            .flatMap { allowedIPs }
            .toSortedSet()
    }

    companion object {
        const val ATLAS_CACHE_NAME = "atlassianIPCache"
        const val ATLAS_IP_URL = "https://ip-ranges.atlassian.com"
        const val TRELLO_FILTER = "trello"
    }
}
