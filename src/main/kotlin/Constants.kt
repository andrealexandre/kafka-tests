import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import java.util.*

const val topic = "message"

val kafkaProperties = Properties().apply {
    this["bootstrap.servers"] = "localhost:9092"
    this["group.id"] = "CountryCounter"
    this[StreamsConfig.APPLICATION_ID_CONFIG] = "stream-pipes"
    this[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
    this[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
}