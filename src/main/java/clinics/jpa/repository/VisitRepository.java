package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Visit;

@Repository
public interface VisitRepository extends BaseRepository<Visit, Integer> {

	public List<Visit> findByPatientId(Integer patientId);

	public Integer countByRoomIdAndDischargeDateIsNull(Integer id);

}
