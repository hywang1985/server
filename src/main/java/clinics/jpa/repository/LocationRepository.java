package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Location;

@Repository
public interface LocationRepository extends BaseRepository<Location, Integer> {

}
