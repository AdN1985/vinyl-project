package vinyl.io.repository;

import java.util.List;

/**
 * @author 00558704
 * 
 */
public interface VinylsRepositoryCustom {

    /**
     * @return List<Vinyl>
     */
	public List <Object[]> findAllToExport();
}
