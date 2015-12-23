package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Configuration;

@Repository
public interface ConfigurationRepository extends BaseRepository<Configuration, Integer> {

	public Configuration findByName(String name);

}
