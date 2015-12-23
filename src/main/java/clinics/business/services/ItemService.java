package clinics.business.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import clinics.entity.Category;
import clinics.entity.Item;
import clinics.entity.Item_;
import clinics.entity.StockType;
import clinics.entity.Supplier;
import clinics.jpa.services.ItemsRepositoryService;
import clinics.jpa.services.SupplierRepositoryService;
import clinics.model.ConfigurationModel;
import clinics.model.ItemModel;
import clinics.model.ReportRequestModel;
import clinics.model.SupplierStatsModel;
import clinics.transformer.ItemTransformer;

@Service
public class ItemService extends AbstractServiceImpl<Integer, ItemModel, Item, ItemsRepositoryService, ItemTransformer> {

	private static final String SILVER = "Silver";

	private static final String GOLD = "Gold";

	private static final String ITEM_PAGE_SIZE = "item_pagesize";
	
	private static final String ITEM_NOTIFY_COUNT = "item_notify_count";

	@Autowired
	private ItemsRepositoryService itemsRepositoryService;
	
	@Autowired
	private SupplierRepositoryService supplierRepositoryService;

	@Autowired
	private ItemTransformer itemTransformer;

	@Autowired
	private ConfigurationService configurationService;

	@Override
	protected ItemsRepositoryService repoService() {
		return itemsRepositoryService;
	}

	@Override
	public ItemTransformer transformer() {
		return itemTransformer;
	}

	public PageImpl<Item> getAll(Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = Integer.parseInt(System.getProperty(ITEM_PAGE_SIZE));
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAll(new PageRequest(page, size));
	}

	public Integer getPageSize() {
		ConfigurationModel pageSize = configurationService.getByName(ITEM_PAGE_SIZE);
		Integer size = 10;
		if (pageSize != null) {
			size = pageSize.getValue();
		}
		return size;
	}

	public void setPageSize(Integer size) {
		ConfigurationModel pageSize = configurationService.getByName(ITEM_PAGE_SIZE);
		if (pageSize == null) {
			pageSize = new ConfigurationModel();
			pageSize.setName(ITEM_PAGE_SIZE);
		}
		if (size == null || size <= 0) {
			size = 10;
		}
		pageSize.setValue(size);
		configurationService.save(pageSize);
	}

	public ItemModel sell(ItemModel model) {
		model.setSold(true);
		model.setLendDate(new Date().getTime());
		return super.save(model);
	}

	public ItemModel getBack(ItemModel model) {
		model.setLendTo(0);
		return super.save(model);
	}

	public ItemModel lend(ItemModel model) {
		model.setSold(false);
		model.setLendDate(new Date().getTime());
		return super.save(model);
	}

	public List<ItemModel> generateReport(final ReportRequestModel model) {
		return transformer().transformTo(itemsRepositoryService.findAll(new Specification<Item>() {

			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (StringUtils.isNotBlank(model.getItemName())) {
					predicates.add(cb.like(root.get(Item_.name), "%" + model.getItemName().toUpperCase() + "%"));
				}

				if (StringUtils.isNotBlank(model.getItemType())) {
					predicates.add(cb.equal(root.get(Item_.itemType), model.getItemType()));
				}

				if (model.getCategory() > 0) {
					Category category = new Category();
					category.setId(model.getCategory());
					predicates.add(cb.equal(root.get(Item_.category), category));
				}

				if (model.getStockType() > 0) {
					StockType stockType = new StockType();
					stockType.setId(model.getStockType());
					predicates.add(cb.equal(root.get(Item_.stockType), stockType));
				}

				if (model.getSupplier() > 0) {
					Supplier supplier = new Supplier();
					supplier.setId(model.getSupplier());
					predicates.add(cb.equal(root.get(Item_.supplier), supplier));
				}

				if (model.getLent() > 0) {
					Supplier lent = new Supplier();
					lent.setId(model.getLent());
					predicates.add(cb.equal(root.get(Item_.lendTo), lent));
				}

				if (null != model.getPurchaseStartDate() && null != model.getPurchaseEndDate()) {
					predicates.add(cb.greaterThanOrEqualTo(root.get(Item_.createDate), getBeginTimeStampForDate(model.getPurchaseStartDate())));
					predicates.add(cb.lessThanOrEqualTo(root.get(Item_.createDate), getEndTimeStampForDate(model.getPurchaseEndDate())));
				}

				if (null != model.getSellStartDate() && null != model.getSellEndDate()) {
					predicates.add(cb.greaterThanOrEqualTo(root.get(Item_.lendDate), getBeginTimeStampForDate(model.getSellStartDate())));
					predicates.add(cb.lessThanOrEqualTo(root.get(Item_.lendDate), getEndTimeStampForDate(model.getSellEndDate())));
				}
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		}));
	}
	
	public Map<String, Double> generateBalanceSheet() {
		Map<String, Double> output = new HashMap<String, Double>();
		double d = 0.0;
		try {
			d = itemsRepositoryService.findWeightSumByType(GOLD);
		} catch (Exception e) {
			d = 0.0;
		}
		output.put("goldWeight", d);
		try {
			d = itemsRepositoryService.findWeightSumByType(SILVER);
		} catch (Exception e) {
			d = 0.0;
		}
		output.put("silverWeight", d);
		return output;
	}
	
	public List<Map<String, String>> findAllItemNames() {
		return repoService().findAllItemNames();
	}

	protected Long getBeginTimeStampForDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	protected Long getEndTimeStampForDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	public PageImpl<Item> search(String name, Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = Integer.parseInt(System.getProperty(ITEM_PAGE_SIZE));
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAllByNameLike(name.toUpperCase(), new PageRequest(page, size));
	}
	
	public List<SupplierStatsModel> supplierStats() {
		List<SupplierStatsModel> stats = new ArrayList<SupplierStatsModel>();
		
		List<Supplier> allSuppliers = supplierRepositoryService.findAll();
		for (Supplier quriedSupplier : allSuppliers) {
			SupplierStatsModel eachStat = new SupplierStatsModel();
			double d = 0.0;
			try {
				d = itemsRepositoryService.findWeightSumByType(GOLD, quriedSupplier);
			} catch (Exception e) {
				d = 0.0;
			}
			eachStat.setGold(d);
			try {
				d = itemsRepositoryService.findWeightSumByType(SILVER, quriedSupplier);
			} catch (Exception e) {
				d = 0.0;
			}
			eachStat.setSilver(d);
			eachStat.setSupplier(quriedSupplier.getName());
			stats.add(eachStat);
		}
		return stats;
	}
	
	public List<Map<String, Integer>> getItemsCount() {
		Long itemNotifyCount = 1L;
		try {
			itemNotifyCount = Long.parseLong(System.getProperty(ITEM_NOTIFY_COUNT));
		} catch (Exception e) { }
		return repoService().findItemsCount(itemNotifyCount);
	}

	public void setNotifySize(Integer size) {
		ConfigurationModel pageSize = configurationService.getByName(ITEM_NOTIFY_COUNT);
		if (pageSize == null) {
			pageSize = new ConfigurationModel();
			pageSize.setName(ITEM_NOTIFY_COUNT);
		}
		if (size == null || size <= 0) {
			size = 1;
		}
		pageSize.setValue(size);
		configurationService.save(pageSize);
	}
	
	public Integer getNotifySize() {
		ConfigurationModel pageSize = configurationService.getByName(ITEM_NOTIFY_COUNT);
		Integer size = 1;
		if (pageSize != null) {
			size = pageSize.getValue();
		}
		return size;
	}

	public List<ItemModel> getLentItems(ReportRequestModel model) {
		Long start = 0L, end = 0L;
		List<Item> lentItems = null;
		if (null != model.getSellStartDate() && null != model.getSellEndDate()) {
			start = getBeginTimeStampForDate(model.getSellStartDate());
			end = getEndTimeStampForDate(model.getSellEndDate());
			lentItems = repoService().getLentItems(start, end);
		} else {
			lentItems = repoService().getLentItems();
		}
		return transformer().transformTo(lentItems);
	}
	
	public List<ItemModel> getSoldItems(ReportRequestModel model) {
		Long start = 0L, end = 0L;
		List<Item> soldItems = null;
		if (null != model.getSellStartDate() && null != model.getSellEndDate()) {
			start = getBeginTimeStampForDate(model.getSellStartDate());
			end = getEndTimeStampForDate(model.getSellEndDate());
			soldItems = repoService().getSoldItems(start, end);
		} else {
			soldItems = repoService().getSoldItems();
		}
		return transformer().transformTo(soldItems);
	}

	public PageImpl<Item> getAll(boolean booleanValue, Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = Integer.parseInt(System.getProperty(ITEM_PAGE_SIZE));
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAllByValidated(booleanValue, new PageRequest(page, size));
	}

	public void saveAllAsValid(Boolean flag) {
		repoService().saveAllAsValid(flag);
	}
}