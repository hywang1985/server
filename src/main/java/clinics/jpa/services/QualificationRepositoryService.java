package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Qualification;
import clinics.jpa.repository.QualificationRepository;
import clinics.utils.Constants;

@Service
public class QualificationRepositoryService extends AbstractRepositoryService<QualificationRepository, Qualification, Integer> {

	@Autowired
	private QualificationRepository repository;

	@Override
	public QualificationRepository repository() {
		return repository;
	}

	public List<Qualification> findByName(String name) {
		return repository().findAllByNameLike(Constants.PERCENT + name.toUpperCase() + Constants.PERCENT);
	}
}
