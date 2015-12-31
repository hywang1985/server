package clinics.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import clinics.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

}