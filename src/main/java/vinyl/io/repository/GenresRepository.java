package vinyl.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinyl.io.model.Genre;

/**
 * @author 00558704
 * 
 */
public interface GenresRepository extends JpaRepository<Genre, Integer> {

}
