import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import java.util.Properties
import java.util.regex.Pattern




fun main(args: Array<String>) {

    val consumer = KafkaConsumer<String, String>(kafkaProperties, StringDeserializer(), StringDeserializer())
            .apply { this.subscribe(Pattern.compile(topic)) }

    while (true) {
        consumer.poll(100)
                .forEach { record -> println("key='${record.key()}' | value='${record.value()}'") }
    }

}