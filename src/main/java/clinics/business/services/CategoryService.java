package clinics.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Category;
import clinics.jpa.services.CategoryRepositoryService;
import clinics.model.DepartmentModel;
import clinics.transformer.CategoryTransformer;

@Service
public class CategoryService extends AbstractServiceImpl<Integer, DepartmentModel, Category, CategoryRepositoryService, CategoryTransformer> {

	@Autowired
	private CategoryRepositoryService categoryRepositoryService;

	@Autowired
	private CategoryTransformer categoryTransformer;

	@Override
	protected CategoryRepositoryService repoService() {
		return categoryRepositoryService;
	}

	@Override
	protected CategoryTransformer transformer() {
		return categoryTransformer;
	}
}
