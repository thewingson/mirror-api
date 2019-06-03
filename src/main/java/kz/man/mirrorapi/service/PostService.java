package kz.man.mirrorapi.service;

import kz.man.mirrorapi.domain.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ResponseEntity<Post> save(Post post);

    ResponseEntity update(Post post, Long id);

    ResponseEntity delete(Long id);
}
