package clinics.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import clinics.entity.Staff;

@Repository
public interface StaffsRepository extends BaseRepository<Staff, Integer> {

	@Transactional
	public Page<Staff> findAllByFirstNameLikeOrLastNameLike(String fname, String lname, Pageable pageRequest);

	public Staff findById(Integer id);

}