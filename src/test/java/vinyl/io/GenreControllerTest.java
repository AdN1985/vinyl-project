package vinyl.io;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import vinyl.io.controller.GenresController;
import vinyl.io.model.Genre;
import vinyl.io.repository.GenresRepository;


/**
 * @author 00558704
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreControllerTest {

    @Mock
    GenresRepository repository;
    
    @Mock
    GenresRepository genresRepository;
    
    Genre genre1; Genre genre2;  Genre genre3;  Genre genre4;  Genre genre5;
    
    
    @InjectMocks
    private GenresController gc;

    /**
     * 
     */
    @Before
    public void setUp() {

        this.genre1 = new Genre(1, "BLUES");
        this.genre2 = new Genre(2, "ROCK");       
        this.genre3 = new Genre(3, "HEAVY");
        this.genre4 = new Genre(4, "FOLK");
        this.genre5 = new Genre(5, "JAZZ");           
    }
        
    /**
     * Test to check get all genres
     */
    @Test
    public void testGetAllVinyls() {
    	
    	//given genre1, genre2, genre3, genre4, genre5
    	
    	//when  
    	List<Genre> genres = Arrays.asList(this.genre1, this.genre2, this.genre3, this.genre4, this.genre5);
    	when(repository.findAll()).thenReturn(genres);
    	List<Genre> listVinyls = gc.list();

    	verify(repository).findAll();
    	 
    	//then
        assertThat(listVinyls, is(genres));       
    }    
}
