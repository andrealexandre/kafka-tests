import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Materialized
import java.util.concurrent.CountDownLatch

fun main(args: Array<String>) {

    val builder = StreamsBuilder()

    builder.stream<String, String>(topic, Consumed.with(Serdes.String(), Serdes.String()))
            .flatMapValues { _, value -> value.split(Regex("\\W+")) }
            .groupBy { _, value -> value }
            .count(Materialized.`as`("message-word-count-table"))
            .toStream()
            .peek { key, value -> println("message-count key='$key' value='$value'") }

    val topology = builder.build()

    val streams = KafkaStreams(topology, kafkaProperties)

    val latch = CountDownLatch(1)

    try {
        streams.cleanUp()
        streams.start()
        latch.await()
    }
    finally {
        latch.countDown()
        streams.close()
        streams.cleanUp()
    }
}