import java.util.*

const val topic = "message"

val kafkaProperties = Properties().apply {
    this["bootstrap.servers"] = "localhost:9092"
    this["group.id"] = "CountryCounter"
}