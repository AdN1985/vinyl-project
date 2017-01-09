package vinyl.io.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vinyl.io.model.Genre;
import vinyl.io.repository.GenresRepository;

/**
 * @author 00558704
 * 
 */
@RestController
@RequestMapping("/")
public class GenresController {

    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private GenresRepository repository;

    /**
     * @return List<Genre>
     */
    @GetMapping(value = "genres")
    public List<Genre> list() {

        GenresController.log.info("list method init");

        List<Genre> genres = repository.findAll();

        GenresController.log.info("list method end");

        return genres;
    }
    
    
    
}
