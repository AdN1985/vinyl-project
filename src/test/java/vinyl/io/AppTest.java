package vinyl.io;

import org.junit.Test;

import vinyl.io.controller.HomeController;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author 00558704
 * 
 */
public class AppTest {

    /**
     * 
     */
    @Test
    public void testApp() {
        HomeController hc = new HomeController();
        String result = hc.home();
       
        //Hamcrest
        assertThat(result, is("Vinyl Project"));       
    }
}
