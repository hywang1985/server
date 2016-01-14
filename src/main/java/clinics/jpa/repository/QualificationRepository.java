package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Qualification;

@Repository
public interface QualificationRepository extends BaseRepository<Qualification, Integer> {

	public Qualification findByName(String name);

	public List<Qualification> findAllByNameLike(String name);
}
