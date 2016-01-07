package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.BloodGroup;

@Repository
public interface BloodGroupRepository extends BaseRepository<BloodGroup, Integer> {

	public BloodGroup findByName(String name);

}
