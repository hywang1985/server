package clinics.jpa.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import clinics.entity.Patient;
import clinics.entity.Supplier;
import clinics.jpa.repository.ItemsRepository;

@Service("itemsRepositoryService")
public class ItemsRepositoryService extends AbstractRepositoryService<ItemsRepository, Patient, Integer> {
	
	public static final String PERCENT = "%";

	@Autowired
	private ItemsRepository repository;

	@Override
	public ItemsRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<Patient> findAll() {
		return super.findAll();
	}

	@Override
	public Patient findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public Patient save(Patient entity) {
		return super.save(entity);
	}

	public Double findWeightSumByType(String type) {
		return repository().findAllWeightByItemType(false, type);
	}
	
	public List<Map<String, String>> findAllItemNames() {
		return repository().findAllItemNames();
	}
	
	public PageImpl<Patient> findAllByNameLike(String name, Pageable pageRequest) {
		return repository().findAllByNameLike(PERCENT + name + PERCENT, pageRequest);
	}
	
	public Double findWeightSumByType(String type, Supplier supplier) {
		return repository().sumByWeightByItemTypeSupplier(false, type, supplier);
	}

	public List<Map<String, Integer>> findItemsCount(Long itemNotifyCount) {
		return repository().findItemsCount(false, itemNotifyCount);
	}

	public List<Patient> getLentItems() {
		return repository().findAllByLendToNotNullAndSoldOrderByLendToDesc(false);
	}
	
	public List<Patient> getSoldItems() {
		return repository().findAllByLendToNotNullAndSoldOrderByLendToDesc(true);
	}

	public List<Patient> getSoldItems(Long start, Long end) {
		return repository().findAllByLendToNotNullAndLendDateBetweenAndSoldOrderByLendToDesc(start, end, true);
	}

	public List<Patient> getLentItems(Long start, Long end) {
		return repository().findAllByLendToNotNullAndLendDateBetweenAndSoldOrderByLendToDesc(start, end, false);
	}

	public PageImpl<Patient> findAllByValidated(boolean booleanValue, Pageable pageRequest) {
		return repository().findAllByValidated(booleanValue, pageRequest);
	}

	public void saveAllAsValid(Boolean flag) {
		repository().saveAllAsValid(flag);
	}
}
