package kz.man.mirrorapi.service;

import kz.man.mirrorapi.domain.Home;
import org.springframework.http.ResponseEntity;

public interface HomeService {

    ResponseEntity<Home> save(Home home);

    ResponseEntity update(Home home, Long id);

    ResponseEntity delete(Long id);
}
