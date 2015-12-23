package clinics.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Customer;
import clinics.jpa.services.CustomerRepositoryService;
import clinics.model.CustomerModel;
import clinics.transformer.CustomerTransformer;

@Service
public class CustomerService extends AbstractServiceImpl<Integer, CustomerModel, Customer, CustomerRepositoryService, CustomerTransformer> {

	@Autowired
	private CustomerRepositoryService categoryRepositoryService;

	@Autowired
	private CustomerTransformer categoryTransformer;

	@Override
	protected CustomerRepositoryService repoService() {
		return categoryRepositoryService;
	}

	@Override
	protected CustomerTransformer transformer() {
		return categoryTransformer;
	}
}
