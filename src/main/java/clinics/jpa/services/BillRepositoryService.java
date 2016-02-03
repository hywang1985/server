package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Bill;
import clinics.entity.Patient;
import clinics.entity.Visit;
import clinics.jpa.repository.BillRepository;

@Service
public class BillRepositoryService extends AbstractRepositoryService<BillRepository, Bill, Integer> {

	@Autowired
	private BillRepository repository;

	@Override
	public BillRepository repository() {
		return repository;
	}

	public List<Bill> getPatientVisitBills(Patient patient, Visit visit) {
		return repository().findAllByPatientAndVisit(patient, visit);
	}

	public Bill findById(Integer id) {
		return repository().findById(id);
	}
}
