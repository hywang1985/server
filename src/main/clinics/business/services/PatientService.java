package clinics.business.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import clinics.entity.Patient;
import clinics.jpa.services.PatientRepositoryService;
import clinics.model.PatientModel;
import clinics.transformer.PatientTransformer;
import clinics.utils.Constants;

@Service
public class PatientService extends AbstractServiceImpl<Integer, PatientModel, Patient, PatientRepositoryService, PatientTransformer> {

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

	public Page<Patient> search(String name, Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = configurationService.getIntPropertyFromCache(Constants.PAGE_SIZE);
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAllByNameLike(name.toUpperCase(), new PageRequest(page, size));
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
}
