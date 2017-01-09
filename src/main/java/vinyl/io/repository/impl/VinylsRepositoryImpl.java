package vinyl.io.repository.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import vinyl.io.model.Vinyl;
import vinyl.io.repository.VinylsRepositoryCustom;

/**
 * @author 00558704
 * 
 */
public class VinylsRepositoryImpl implements VinylsRepositoryCustom{
	
	@Autowired
	private EntityManager entityManager;
	 
	public List <Object[]> findAllToExport() {
			 	     
		Query query = entityManager.createQuery("select v.id, v.title, v.artist, v.edited from Vinyl v");
		
		 return (List <Object[]>) query.getResultList();
	     	
	}
	
	

  
}
