package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Staff;

@Repository
public interface StaffsRepository extends BaseRepository<Staff, Integer> {

	public Staff findById(Integer id);

}