import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun main(args: Array<String>) {

    val producer = KafkaProducer<String, String>(kafkaProperties, StringSerializer(), StringSerializer())

    while (true) {
        println("Type a message:")
        val scanner = Scanner(System.`in`)
        val line = scanner.nextLine()

        ProducerRecord(topic, UUID.randomUUID().toString(), line).let {
            producer.send(it) { metadata, exception ->
                when {
                    metadata != null -> println(" !Sent message into topic '${metadata.topic()}' with key='${it.key()}'")
                    exception != null -> println(" !Exception in producer. $exception")
                    else -> throw IllegalStateException("metadata and exception are null!!!")
                }
            }
        }

    }

}