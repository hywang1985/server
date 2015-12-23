package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Supplier;
import clinics.jpa.services.SupplierRepositoryService;
import clinics.model.SupplierModel;
import clinics.transformer.SupplierTransformer;

@Service
public class SupplierService extends AbstractServiceImpl<Integer, SupplierModel, Supplier, SupplierRepositoryService, SupplierTransformer> {

    @Autowired
    private SupplierRepositoryService supplierRepositoryService;

    @Autowired
    private SupplierTransformer supplierTransformer;

    @Override
    protected SupplierRepositoryService repoService() {
        return supplierRepositoryService;
    }

    @Override
    protected SupplierTransformer transformer() {
        return supplierTransformer;
    }
    
    @Override
    public List<SupplierModel> getAll() {
		return transformer().transformTo(repoService().findAllByOrderByNameDesc());
	}
}
