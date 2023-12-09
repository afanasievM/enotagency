package ua.com.enotagency.builder

import ua.com.enotagency.dto.BinotelCallRequest

object BinotelCallRequestBuilder {

    private var builders: List<BinotelRequestBuilder> = mutableListOf()

    fun init(builders:List<BinotelRequestBuilder>){
        this.builders = builders
    }

    fun build(request: Map<String, String>): BinotelCallRequest? {
        return choose(request)?.let { build(request) }
    }

    private fun choose(request: Map<String, String>): BinotelRequestBuilder? {
        val requestType = request["requestType"]
        return builders.firstOrNull { it.requestType.toString() == requestType }
    }
}
