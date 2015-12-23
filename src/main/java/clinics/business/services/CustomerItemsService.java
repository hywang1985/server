package clinics.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.CustomerItems;
import clinics.jpa.services.CustomerItemsRepositoryService;
import clinics.model.CustomerItemsModel;
import clinics.transformer.CustomerItemsTransformer;

@Service
public class CustomerItemsService extends AbstractServiceImpl<Integer, CustomerItemsModel, CustomerItems, CustomerItemsRepositoryService, CustomerItemsTransformer> {

	@Autowired
	private CustomerItemsRepositoryService categoryRepositoryService;

	@Autowired
	private CustomerItemsTransformer categoryTransformer;

	@Override
	protected CustomerItemsRepositoryService repoService() {
		return categoryRepositoryService;
	}

	@Override
	protected CustomerItemsTransformer transformer() {
		return categoryTransformer;
	}
}
