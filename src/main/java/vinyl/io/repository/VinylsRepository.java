package vinyl.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinyl.io.model.Vinyl;

/**
 * @author 00558704
 * 
 */
public interface VinylsRepository extends JpaRepository<Vinyl, Integer>, VinylsRepositoryCustom {

}
