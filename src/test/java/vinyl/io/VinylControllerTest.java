package vinyl.io;

import static org.hamcrest.CoreMatchers.equalTo;
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

import vinyl.io.controller.VinylsController;
import vinyl.io.model.Genre;
import vinyl.io.model.Vinyl;
import vinyl.io.repository.VinylsRepository;


/**
 * @author 00558704
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VinylControllerTest {

    @Mock
    VinylsRepository repository;
        
    Vinyl vinyl1;
    Vinyl vinyl2;
    Vinyl vinyl3;
    
    @InjectMocks
    private VinylsController vc;

    /**
     * 
     */
    @Before
    public void setUp() {

        this.vinyl1 = new Vinyl(1, "Vinyl_1", "Artist_1", "1985", null, null);
        this.vinyl2 = new Vinyl(2, "Vinyl_2", "Artist_2", "1985", null, null);
        this.vinyl3 = new Vinyl(3, "Vinyl_3", "Artist_3", "1985", null, null);        
    }

    /**
     * Test to check get one vinyl
     */
  
    @Test    
    public void testGetVinyl() {
    	
    	//given vinyl1    	
    	
    	//when     	
    	when(repository.findOne(1)).thenReturn(this.vinyl1);
    	    	    	
    	Vinyl vinyl = vc.get(1);
    	
    	verify(repository).findOne(1);
    	 
    	//then
    	assertThat(vinyl.getId(), is(1));
        
    }
    
    /**
     * Test to check delete a vinyl
     */
    @Test
    public void testDeleteVinyl() {
    	
    	//given vinyl2
    	    	
        when(repository.findOne(2)).thenReturn(this.vinyl2);

        vc.delete(2);

        verify(repository).delete(vinyl2); 
        
    }
    
    /**
     * Test to check get all vinyls
     */
    @Test
    public void testGetAllVinyls() {
    	
    	//given vinyl1, vinyl2 and vinyl3    	
    	
    	//when  
    	List<Vinyl> vinyls = Arrays.asList(this.vinyl1, this.vinyl2, this.vinyl3);
    	when(repository.findAll()).thenReturn(vinyls);
    	List<Vinyl> listVinyls = vc.list();

    	verify(repository).findAll();
    	 
    	//then
        assertThat(listVinyls, is(vinyls));       
    }

    
    /**
     * Test to check add vinyl
     */
    @Test    
    public void testAddVinyl() {    	    
    	
    	//given vinyl1
    	
    	//when     	
    	when(repository.save(this.vinyl1)).thenReturn(this.vinyl1);
    	
    	vc.create(this.vinyl1);
    	    	
    	verify(repository).saveAndFlush(this.vinyl1); 
    	
    	when(repository.findOne(this.vinyl1.getId())).thenReturn(this.vinyl1);
    	
    	Vinyl vinyl1New = vc.get(this.vinyl1.getId());
    	 
    	//then
        assertThat(vinyl1New, is(this.vinyl1));
        
    }
    
    /**
     * Test to check update a vinyl
     */
    @Test
    public void testUpdateVinyl() {
    	
    	//given vinyl1Updated
    	Vinyl vinyl1Updated = new Vinyl(1, "vinyl1Updated", "vinyl1Updated", "2016", new Genre(1, "ROCK"), null);  
    	
    	when(repository.saveAndFlush(vinyl1Updated)).thenReturn(vinyl1Updated);    	   
    	    	    	   
    	when(repository.findOne(1)).thenReturn(vinyl1Updated);
    	this.vinyl1 = vc.get(vinyl1Updated.getId());
    	    
    	vc.update(vinyl1Updated.getId(), vinyl1Updated);    	    
    	verify(repository).saveAndFlush(vinyl1Updated); 
    	    	 
    	//then
        assertThat(this.vinyl1.getTitle(), is(equalTo(vinyl1Updated.getTitle())));
        assertThat(this.vinyl1.getArtist(), is(equalTo(vinyl1Updated.getArtist())));
        assertThat(this.vinyl1.getEdited(), is(equalTo(vinyl1Updated.getEdited())));
        assertThat(this.vinyl1.getGenre1(), is(equalTo(vinyl1Updated.getGenre1())));
        
    }
    
}
