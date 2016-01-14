package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Speciality;

@Repository
public interface SpecialityRepository extends BaseRepository<Speciality, Integer> {

	public Speciality findByName(String name);

	public List<Speciality> findAllByNameLike(String name);
}
