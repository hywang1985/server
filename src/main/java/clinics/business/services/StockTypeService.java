package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.StockType;
import clinics.jpa.services.StockTypeRepositoryService;
import clinics.model.StockTypeModel;
import clinics.transformer.StockTypeTransformer;

@Service
public class StockTypeService extends AbstractServiceImpl<Integer, StockTypeModel, StockType, StockTypeRepositoryService, StockTypeTransformer> {

	@Autowired
	private StockTypeRepositoryService stockTypeRepositoryService;

	@Autowired
	private StockTypeTransformer stockTypeTransformer;

	@Override
	protected StockTypeRepositoryService repoService() {
		return stockTypeRepositoryService;
	}

	@Override
	protected StockTypeTransformer transformer() {
		return stockTypeTransformer;
	}

	public List<StockTypeModel> getAllByMethod(String method) {
		return transformer().transformTo(repoService().findAllByMethod(method));
	}
	
	public List<StockTypeModel> getAllByMethodAndRemove(String method, boolean remove) {
		return transformer().transformTo(repoService().findAllByMethodAndRemove(method, remove));
	}
}
