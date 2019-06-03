package kz.man.mirrorapi.service.impl;

import kz.man.mirrorapi.domain.Post;
import kz.man.mirrorapi.repository.PostRepository;
import kz.man.mirrorapi.rest.RestPost;
import kz.man.mirrorapi.service.PostService;
import kz.man.mirrorapi.util.CustomErrorType;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    public static final Logger logger = LoggerFactory.getLogger(RestPost.class);

    @Override
    public ResponseEntity<Post> save(Post post) {
        return new ResponseEntity<Post>(postRepository.saveAndFlush(post), HttpStatus.CREATED);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity update(Post post, Long id) {

        if (postRepository.getOne(id) != null) {

            Post postToUpdate = postRepository.getOne(id);
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setText(post.getText());
            return new ResponseEntity<Post>(postRepository.saveAndFlush(postToUpdate), HttpStatus.ACCEPTED);
        } else {
            logger.error("Update error " + post.getTitle());
            return new ResponseEntity(
                    new CustomErrorType("Error while updating post"),
                    HttpStatus.CONFLICT);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity delete(Long id) {

        if (postRepository.getOne(id) != null) {
            postRepository.deleteById(id);
            return new ResponseEntity<Post>(HttpStatus.ACCEPTED);
        } else {
            logger.error("Delete error " + postRepository.getOne(id).getTitle());
            return new ResponseEntity(
                    new CustomErrorType("Error while deleting post"),
                    HttpStatus.CONFLICT);
        }
    }

//    @Override
//    public ResponseEntity send(String mess) {
//        String topic        = "MQTT Examples";
//        String content      = mess;
//        int qos             = 2;
//        String broker       = "tcp://iot.eclipse.org:1883";
//        String clientId     = "Mirror Api";
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        try {
//            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
//
//            System.out.println("Connecting to broker: "+broker);
//
//            sampleClient.connect(connOpts);
//
//            System.out.println("Connected");
//            System.out.println("Publishing message: "+content);
//
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            sampleClient.publish(topic, message);
//
//            System.out.println("Message published");
//
//            sampleClient.disconnect();
//
//            System.out.println("Disconnected");
//
//            System.exit(0);
//
//            return new ResponseEntity<Post>(HttpStatus.CREATED);
//        } catch(MqttException me) {
//            System.out.println("reason "+me.getReasonCode());
//            System.out.println("msg "+me.getMessage());
//            System.out.println("loc "+me.getLocalizedMessage());
//            System.out.println("cause "+me.getCause());
//            System.out.println("excep "+me);
//            me.printStackTrace();
//            return new ResponseEntity<Post>(HttpStatus.CONFLICT);
//        }
//
//    }
}
