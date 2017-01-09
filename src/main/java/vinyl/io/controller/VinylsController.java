package vinyl.io.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vinyl.io.model.Genre;
import vinyl.io.model.Vinyl;
import vinyl.io.repository.GenresRepository;
import vinyl.io.repository.VinylsRepository;

/**
 * @author 00558704
 * 
 */
@RestController
@RequestMapping("/")
public class VinylsController {

    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private VinylsRepository repository;
    
    @Autowired
    private GenresRepository genreRepository;
    
    /**
     * @return List<Vinyl>
     */
    @GetMapping(value = "vinyls")
    public List<Vinyl> list() {
    	    	
        log.info("list method init");

        List<Vinyl> vinyls = this.repository.findAll();

        log.info("list method end");
                
        return vinyls;

    }
        	    
    /**
     * @param id
     * @return Vinyl
     */
    @GetMapping(value = "vinyls/{id}")
    public Vinyl get(@PathVariable final Integer id) {
    	
        log.info("get method init");
        Vinyl vinyl = this.repository.findOne(id);
        log.info("get method end");
        
        return vinyl;
    }

    /**
     * @param vinyl
     * @return Vinyl
     */
    @PostMapping(value = "vinyls")
    public Vinyl create(@RequestBody final Vinyl vinyl) {
        	
        log.info("save method init");

        vinyl.setImageFront(vinyl.getImageSrcFront().getBytes());
        vinyl.setImageBack(vinyl.getImageSrcBack().getBytes());

        log.info("save method end");
        
        return this.repository.saveAndFlush(vinyl);
 
    }

    /**
     * @param id
     * @param vinyl
     * @return Vinyl
     */
    @PutMapping(value = "vinyls/{id}")
    public Vinyl update(@PathVariable final Integer id, @RequestBody final Vinyl vinyl) {    	  
    	
        log.info("update method init");

        Vinyl existingVinyl = this.repository.findOne(id);

        vinyl.setImageFront(vinyl.getImageSrcFront().getBytes());
        vinyl.setImageBack(vinyl.getImageSrcBack().getBytes());

        BeanUtils.copyProperties(vinyl, existingVinyl);

        log.info("update method end");
        
        return repository.saveAndFlush(vinyl);
    }

    /**
     * @param id
     * @return Vinyl
     */
    @DeleteMapping(value = "vinyls/{id}")
    public Vinyl delete(@PathVariable final Integer id) {

        log.info("delete method init");

        Vinyl existingVinyl = this.repository.findOne(id);

        log.info("delete method end");

        this.repository.delete(existingVinyl);

        return existingVinyl;
    }
    

    /**
	 * @param file
	 * @param response
	 */
	@PostMapping(value = "vinyls/upload")     
    @Consumes(MediaType.MULTIPART_FORM_DATA)   
    public void uploadVinyls( @RequestParam("file") MultipartFile file, HttpServletResponse response){

        log.info("upload data method init");        
        List<Vinyl> listVinyls = new ArrayList<Vinyl>();
        
        try{
	        if (!file.isEmpty()) {
	        	
	        	//Get Genres
	        	Map<String, Genre> mapGenres = getGenresMap();
	        	
	        	InputStream inputStream;
				try {
					inputStream = file.getInputStream();
				
	        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	        				
	        	String line = bufferedReader.readLine();
	        	while ((line = bufferedReader.readLine()) != null)
	        	{	        			        	
	                String[] vFields = line.split(";");
					                
					Vinyl v = new Vinyl(null, vFields[0], vFields[1], vFields[2], mapGenres.get(vFields[3]), mapGenres.get(vFields[4]));
					
					listVinyls.add(v);
	        	}                     
				} catch (IOException e) {			
					e.printStackTrace();
				}
				
				this.repository.save(listVinyls);			
				response.getWriter().write(listVinyls.size() + " vinyls uploaded.");
				log.info(listVinyls.size() + "vinyls uploaded");
	        }
        }catch(DataIntegrityViolationException | IOException e){        	        	
        	try {
        		e.printStackTrace();
				response.getWriter().write("Someone vinyl exist.");				
			} catch (IOException e1) {
				e1.printStackTrace();
			}        	
        }
                
        log.info("upload data method end");	
              
    }
    
    /**
     * @return
     */
    public Map<String, Genre> getGenresMap() {
    	log.info("get genres map method init");
    	
    	Map<String, Genre> mapGenres = new HashMap<String, Genre>();
    	List<Genre> genres = this.genreRepository.findAll();
    	
    	for(Genre genre : genres){    		
    		mapGenres.put(genre.getName(), genre);    		
    	}
    	
    	log.info("get genres map method end");
		return mapGenres;		   	
    }
    
}
