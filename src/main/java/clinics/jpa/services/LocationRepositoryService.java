package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Location;
import clinics.jpa.repository.LocationRepository;

@Service("locationRepositoryService")
public class LocationRepositoryService extends AbstractRepositoryService<LocationRepository, Location, Integer> {

	@Autowired
	private LocationRepository repository;

	@Override
	public LocationRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<Location> findAll() {
		return super.findAll();
	}

	@Override
	public Location findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public Location save(Location entity) {
		return super.save(entity);
	}
}
