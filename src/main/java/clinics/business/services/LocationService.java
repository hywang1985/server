package clinics.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Location;
import clinics.jpa.services.LocationRepositoryService;
import clinics.model.LocationModel;
import clinics.transformer.LocationTransformer;

@Service
public class LocationService extends AbstractServiceImpl<Integer, LocationModel, Location, LocationRepositoryService, LocationTransformer> {

	@Autowired
	private LocationRepositoryService locationRepositoryService;

	@Autowired
	private LocationTransformer locationTransformer;

	@Override
	protected LocationRepositoryService repoService() {
		return locationRepositoryService;
	}

	@Override
	protected LocationTransformer transformer() {
		return locationTransformer;
	}
}
