package clinics.jpa.services;

import java.io.Serializable;
import java.util.List;

import clinics.entity.BaseEntity;

public interface RepositoryService<E extends BaseEntity<ID>, ID extends Serializable> {

	E save(E entity);

	List<E> findAll();

	void delete(ID id);

	E findOne(ID id);

}