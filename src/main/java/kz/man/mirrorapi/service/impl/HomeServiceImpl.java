package kz.man.mirrorapi.service.impl;

import kz.man.mirrorapi.domain.Home;
import kz.man.mirrorapi.repository.HomeRepository;
import kz.man.mirrorapi.rest.RestHome;
import kz.man.mirrorapi.service.HomeService;
import kz.man.mirrorapi.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository homeRepository;


    public static final Logger logger = LoggerFactory.getLogger(RestHome.class);

    @Override
    public ResponseEntity<Home> save(Home home) {
        return new ResponseEntity<Home>(homeRepository.saveAndFlush(home), HttpStatus.CREATED);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity update(Home home, Long id) {

        if (homeRepository.getOne(id) != null) {

            Home homeToUpdate = homeRepository.getOne(id);
            homeToUpdate.setName(home.getName());
            homeToUpdate.setAddress(home.getAddress());
            return new ResponseEntity<Home>(homeRepository.saveAndFlush(homeToUpdate), HttpStatus.ACCEPTED);
        } else {
            logger.error("Update error " + home.getName());
            return new ResponseEntity(
                    new CustomErrorType("Error while updating home"),
                    HttpStatus.CONFLICT);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity delete(Long id) {

        if (homeRepository.getOne(id) != null) {
            homeRepository.deleteById(id);
            return new ResponseEntity<Home>(HttpStatus.ACCEPTED);
        } else {
            logger.error("Delete error " + homeRepository.getOne(id).getName());
            return new ResponseEntity(
                    new CustomErrorType("Error while deleting home"),
                    HttpStatus.CONFLICT);
        }
    }
}
