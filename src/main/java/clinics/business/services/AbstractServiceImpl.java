package clinics.business.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import clinics.entity.BaseEntity;
import clinics.jpa.repository.BaseRepository;
import clinics.jpa.services.AbstractRepositoryService;
import clinics.model.Model;
import clinics.transformer.DTOTransformer;

@Transactional
public abstract class AbstractServiceImpl<ID extends Serializable, DTO extends Model, E extends BaseEntity<ID>, RS extends AbstractRepositoryService<? extends BaseRepository<E, ID>, E, ID>, TR extends DTOTransformer<DTO, E>> {

	public DTO save(DTO resource) {
		return transformer().transformTo(repoService().save(transformer().transformFrom(resource)));
	}

	public DTO getById(ID id) {
		return transformer().transformTo(repoService().findOne(id));
	}

	public void removeById(ID id) {
		repoService().delete(id);
	}

	public List<DTO> getAll() {
		return transformer().transformTo(repoService().findAll());
	}

	public Page<E> getAllByPageNumber(Integer page, Integer size, Integer fromSystem) {
		if (page == null || page < 0) {
			page = 0;
		}
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		PageRequest pageRequest = new PageRequest(page, size);
		return repoService().findAll(pageRequest);
	}

	protected abstract RS repoService();

	protected abstract TR transformer();

}
