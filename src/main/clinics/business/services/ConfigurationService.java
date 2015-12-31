package clinics.business.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import clinics.entity.Configuration;
import clinics.jpa.services.ConfigurationRepositoryService;
import clinics.model.ConfigurationModel;
import clinics.transformer.ConfigurationTransformer;
import clinics.utils.Constants;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ConfigurationService extends AbstractServiceImpl<Integer, ConfigurationModel, Configuration, ConfigurationRepositoryService, ConfigurationTransformer> {

	@Autowired
	private ConfigurationRepositoryService configurationRepositoryService;

	@Autowired
	private ConfigurationTransformer configurationTransformer;

	@Override
	protected ConfigurationRepositoryService repoService() {
		return configurationRepositoryService;
	}

	@Override
	protected ConfigurationTransformer transformer() {
		return configurationTransformer;
	}

	public ConfigurationModel getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@PostConstruct
	public void init() {
		cacheConfigItems();
	}

	@Override
	public ConfigurationModel save(ConfigurationModel resource) {
		ConfigurationModel saved = super.save(resource);
		if (saved.getAutoLoad()) {
			cacheConfigItem(saved.getName(), saved);
		} else {
			clearFromCache(saved);
		}
		return saved;
	}

	public void cacheConfigItem(String name, ConfigurationModel valueModel) {
		int value = 0;
		if (valueModel != null) {
			value = valueModel.getValue();
			System.setProperty(name, Integer.toString(value));

			if (StringUtils.isNotBlank(valueModel.getStrValue())) {
				System.setProperty(name, valueModel.getStrValue());
			}
		}
	}

	private void cacheConfigItems() {
		List<ConfigurationModel> startupItems = transformer().transformTo(repoService().findCacheableItems());
		for (ConfigurationModel configurationModel : startupItems) {
			cacheConfigItem(configurationModel.getName(), configurationModel);
		}
	}

	public int getIntPropertyFromCache(String name) {
		int returnValue = 0;
		try {
			returnValue = Integer.parseInt(System.getProperty(Constants.PAGE_SIZE));
		} catch (Exception e) {
			ConfigurationModel fromDb = getByName(name);
			if (fromDb != null) {
				returnValue = fromDb.getValue().intValue();
				fromDb.setAutoLoad(true);
				save(fromDb);
			}
		}
		return returnValue;
	}

	public void remove(ConfigurationModel item) {
		clearFromCache(item);
		removeById(item.getId());
	}

	private void clearFromCache(ConfigurationModel saved) {
		System.clearProperty(saved.getName());
	}
}
