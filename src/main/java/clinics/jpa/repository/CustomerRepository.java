package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Customer;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer> {

}
