package clinics.jpa.services;

import clinics.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface RepositoryService<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E entity);

    List<E> findAll();

    void delete(ID id);

    E findOne(ID id);

}
