package kz.man.mirrorapi.rest;


import kz.man.mirrorapi.domain.Post;
import kz.man.mirrorapi.repository.PostRepository;
import kz.man.mirrorapi.service.PostService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
@CrossOrigin
public class RestPost {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private Subscriber subscriber;

    @GetMapping(value = "/all")
    public List<Post> getList() {
        return postRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Post getOne(@PathVariable Long id) {
        return postRepository.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Post post) {
        return postService.save(post);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody Post post, @PathVariable Long id) {
        return postService.update(post, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return postService.delete(id);
    }

    @PostMapping(value = "/send")
    public String send(@RequestBody String message) {
        try {
            subscriber.sendMessage(message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return "Success!";
    }

}
