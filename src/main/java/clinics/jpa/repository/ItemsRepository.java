package clinics.jpa.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import clinics.entity.Patient;
import clinics.entity.Supplier;

@Repository
public interface ItemsRepository extends BaseRepository<Patient, Integer> {
	
	@Query("select sum(weight) from Item item where item.lendTo is null and item.sold = :sold and item.itemType = :type")
	Double findAllWeightByItemType(@Param("sold") Boolean sold, @Param("type") String type);

	@Query("select distinct name, itemType from Item")
	List<Map<String, String>> findAllItemNames();

	public PageImpl<Patient> findAllByNameLike(String name, Pageable pageRequest);
	
	@Query("select sum(weight) from Item item where item.lendTo is null and item.sold = :sold and item.itemType = :type and item.supplier = :supplier")
	Double sumByWeightByItemTypeSupplier(@Param("sold") Boolean sold, @Param("type") String type, @Param("supplier") Supplier supplier);

	@Query("select item.name, count(item.name) from Item item where item.lendTo is null and item.sold = :sold group by item.name having count(item.name) <= :count order by count(item.name)")
	public List<Map<String, Integer>> findItemsCount(@Param("sold") Boolean sold, @Param("count") Long itemNotifyCount);

	public List<Patient> findAllByLendToNotNullAndSoldOrderByLendToDesc(Boolean sold);

	List<Patient> findAllByLendToNotNullAndLendDateBetweenAndSoldOrderByLendToDesc(Long start, Long end, boolean b);

	PageImpl<Patient> findAllByValidated(boolean booleanValue, Pageable pageRequest);

	@Query("update Item item set validated = :type where (item.lendTo is null or item.lendTo = 0) and item.sold = 0")
	@Modifying
	public void saveAllAsValid(@Param("type") Boolean flag);
}
