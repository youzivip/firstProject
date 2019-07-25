package firstTest;

import java.util.Properties;

public class ProducerSample {
    ProducerSample ps = new ProducerSample();

    Properties props = new Properties();
        props.put("zk.connect", "127.0.0.1:2181");
        props.put("serializer.class", "kafka.serializer.StringEncoder");

    ProducerConfig config = new ProducerConfig(props);
    Producer<String, String> producer = new Producer<String, String>(config);
    ProducerData<String, String> data = new ProducerData<String, String>("test-topic", "test-message2");
        producer.send(data);
        producer.close();
}
