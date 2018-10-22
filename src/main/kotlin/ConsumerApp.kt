import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import java.util.Properties
import java.util.regex.Pattern




fun main(args: Array<String>) {

    KafkaConsumer<String, String>(kafkaProperties, StringDeserializer(), StringDeserializer()).use {
        it.subscribe(Pattern.compile(topic))

        while (true) {
            it.poll(100)
                    .forEach { record -> println("key='${record.key()}' | value='${record.value()}'") }
        }
    }

}