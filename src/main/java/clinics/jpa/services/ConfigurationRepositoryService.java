package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Configuration;
import clinics.jpa.repository.ConfigurationRepository;

@Service("configurationRepositoryService")
public class ConfigurationRepositoryService extends AbstractRepositoryService<ConfigurationRepository, Configuration, Integer> {

	@Autowired
	private ConfigurationRepository repository;

	@Override
	public ConfigurationRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<Configuration> findAll() {
		return super.findAll();
	}

	@Override
	public Configuration findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public Configuration save(Configuration entity) {
		return super.save(entity);
	}

	public Configuration findByName(String name) {
		return repository().findByName(name);
	}
}
