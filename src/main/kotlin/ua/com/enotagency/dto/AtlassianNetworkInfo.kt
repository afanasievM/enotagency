package ua.com.enotagency.dto

data class AtlassianNetworkInfo(
    val creationDate: String,
    val syncToken: String,
    val items: List<NetworkItem>
)

data class NetworkItem(
    val network: String,
    val maskLen: Int,
    val cidr: String,
    val mask: String,
    val region: List<String>,
    val product: List<String>,
    val direction: List<String>,
    val perimeter: String
)
