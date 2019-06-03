package kz.man.mirrorapi.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mqtt.properties")
public class MQTTProperties {

    @Value("${mqtt.broker.url}")
    private String url;
    @Value("${mqtt.broker.port}")
    private String port;
    @Value("${mqtt.broker.username}")
    private String username;
    @Value("${mqtt.broker.password}")
    private String password;
    @Value("${mqtt.broker.qos}")
    private String qos;
    @Value("${mqtt.broker.pub.clientId}")
    private String clientPubId;
    @Value("${mqtt.broker.sub.clientId}")
    private String clientSubId;
    @Value("${mqtt.broker.sub.topic}")
    private String clientSubTopic;
    @Value("${mqtt.broker.use.credential}")
    private Boolean useCredential;

    public String getUrl() {
        return url;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQos() {
        return qos;
    }

    public String getClientPubId() {
        return clientPubId;
    }

    public String getClientSubId() {
        return clientSubId;
    }

    public String getClientSubTopic() {
        return clientSubTopic;
    }

    public Boolean getUseCredential() {
        return useCredential;
    }

    @Override
    public String toString() {
        return "MqttProperties{" + "url=" + url + ", port=" + port + ", username=" + username + ", password=" + password + ", qos=" + qos + ", clientPubId=" + clientPubId + ", clientSubId=" + clientSubId + ", clientSubTopic=" + clientSubTopic + ", useCredential=" + useCredential + '}';
    }

}
