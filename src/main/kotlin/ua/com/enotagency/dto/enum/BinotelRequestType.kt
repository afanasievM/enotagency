package ua.com.enotagency.dto.enum

enum class BinotelRequestType {
    API_CALL_SETTINGS,
    RECEIVED_THE_CALL,
    ANSWERED_THE_CALL,
    HANGUP_THE_CALL,
    API_CALL_COMPLETED;

    override fun toString(): String {
        return when (this) {
            API_CALL_SETTINGS -> "apiCallSettings"
            RECEIVED_THE_CALL -> "receivedTheCall"
            ANSWERED_THE_CALL -> "answeredTheCall"
            HANGUP_THE_CALL -> "hangupTheCall"
            API_CALL_COMPLETED -> "apiCallCompleted"
        }
    }
}
