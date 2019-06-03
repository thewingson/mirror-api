package kz.man.mirrorapi.rest;

import kz.man.mirrorapi.domain.Home;
import kz.man.mirrorapi.repository.HomeRepository;
import kz.man.mirrorapi.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
@CrossOrigin
public class RestHome {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private HomeService homeService;
    @Autowired
    private Subscriber subscriber;

    @GetMapping(value = "/all")
    public List<Home> getList() {
        return homeRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Home getOne(@PathVariable Long id) {
        return homeRepository.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Home home) {
        return homeService.save(home);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody Home home, @PathVariable Long id) {
        return homeService.update(home, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return homeService.delete(id);
    }


}
