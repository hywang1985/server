package clinics.business.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import clinics.entity.Patient;
import clinics.jpa.services.PatientRepositoryService;
import clinics.model.ConfigurationModel;
import clinics.model.PatientModel;
import clinics.transformer.PatientTransformer;

@Service
public class PatientService extends AbstractServiceImpl<Integer, PatientModel, Patient, PatientRepositoryService, PatientTransformer> {

	private static final String PAGE_SIZE = "pagesize";

	private static final String ITEM_NOTIFY_COUNT = "item_notify_count";

	@Autowired
	private PatientRepositoryService itemsRepositoryService;

	@Autowired
	private PatientTransformer patientTransformer;

	@Autowired
	private ConfigurationService configurationService;

	@Override
	protected PatientRepositoryService repoService() {
		return itemsRepositoryService;
	}

	@Override
	public PatientTransformer transformer() {
		return patientTransformer;
	}

	public Page<Patient> getAll(Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = Integer.parseInt(System.getProperty(PAGE_SIZE));
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAll(new PageRequest(page, size));
	}
	
	public Page<Patient> search(String name, Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = Integer.parseInt(System.getProperty(PAGE_SIZE));
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAllByNameLike(name.toUpperCase(), new PageRequest(page, size));
	}

	public Integer getPageSize() {
		ConfigurationModel pageSize = configurationService.getByName(PAGE_SIZE);
		Integer size = 10;
		if (pageSize != null) {
			size = pageSize.getValue();
		}
		return size;
	}

	public void setPageSize(Integer size) {
		ConfigurationModel pageSize = configurationService.getByName(PAGE_SIZE);
		if (pageSize == null) {
			pageSize = new ConfigurationModel();
			pageSize.setName(PAGE_SIZE);
		}
		if (size == null || size <= 0) {
			size = 10;
		}
		pageSize.setValue(size);
		configurationService.save(pageSize);
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
}
