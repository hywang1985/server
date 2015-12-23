package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.CustomerItems;

@Repository
public interface CustomerItemsRepository extends BaseRepository<CustomerItems, Integer> {

}
