package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.DiagnosticType;

@Repository
public interface DiagnosticTypeRepository extends BaseRepository<DiagnosticType, Integer> {

	public List<DiagnosticType> findByName(String name);

	public List<DiagnosticType> findAll();

}