package clinics.jpa.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import clinics.entity.BaseEntity;
import clinics.jpa.repository.BaseRepository;

@Transactional
public abstract class AbstractRepositoryService<R extends BaseRepository<E, ID>, E extends BaseEntity<ID>, ID extends Serializable> implements RepositoryService<E, ID> {

	public void delete(ID id) {
		repository().delete(id);
	}
	
	public void delete(E entity) {
		repository().delete(entity);
	}

	public E save(E entity) {
		return repository().save(entity);
	}
	
	public E saveAndFlush(E entity) {
		return repository().saveAndFlush(entity);
	}

	public E findOne(ID id) {
		return repository().findOne(id);
	}

	public List<E> findAll() {
		return repository().findAll();
	};
	
	public Page<E> findAll(PageRequest pageRequest) {
		return repository().findAll(pageRequest);
	}

	public List<E> findAll(Specification<E> specification) {
		return repository().findAll(specification);
	}

	public abstract R repository();
}