package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Supplier;

@Repository
public interface SupplierRepository extends BaseRepository<Supplier, Integer> {

	List<Supplier> findAllByOrderByNameDesc();

}
