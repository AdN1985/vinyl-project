package vinyl.io.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 00558704
 * 
 */
@RestController
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * @return String
     */
    @RequestMapping("/")
    public String home() {
    	log.info("home vinyl project init");
        return "Vinyl Project";
    }
}
