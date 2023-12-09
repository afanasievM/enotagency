package ua.com.enotagency.dto

import ua.com.enotagency.dto.enum.BinotelRequestType

abstract class BinotelCallRequest(open val requestType: BinotelRequestType)
