package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.StockType;

@Repository
public interface StockTypeRepository extends BaseRepository<StockType, Integer> {

	public List<StockType> findAllByMethod(String method);

	public List<StockType> findAllByMethodAndRemove(String method, boolean remove);

}
