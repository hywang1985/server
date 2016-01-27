package clinics.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import clinics.entity.BaseEntity;
import clinics.model.Model;

public abstract class AbstractDTOTransformer<From extends Model, To extends BaseEntity<?>>
		implements DTOTransformer<From, To> {

	public Set<To> transformFrom(Set<From> sources) {
		Set<To> toList = new HashSet<To>();
		if (sources != null && !sources.isEmpty()) {
			for (From source : sources) {
				To to = transformFrom(source);
				if (to != null) {
					toList.add(to);
				}
			}

		}
		return toList;
	}

	public Set<From> transformTo(Set<To> sources) {
		Set<From> fromList = new HashSet<From>();
		if (sources != null && !sources.isEmpty()) {
			for (To source : sources) {
				From from = transformTo(source);
				if (from != null) {
					fromList.add(from);
				}
			}
		}
		return fromList;
	}

	public List<To> transformFrom(List<From> sources) {
		List<To> toList = new ArrayList<To>();
		if (sources != null && !sources.isEmpty()) {
			for (From source : sources) {
				To to = transformFrom(source);
				if (to != null) {
					toList.add(to);
				}
			}

		}
		return toList;
	}

	public List<From> transformTo(List<To> sources) {
		List<From> fromList = new ArrayList<From>();
		if (sources != null && !sources.isEmpty()) {
			for (To source : sources) {
				From from = transformTo(source);
				if (from != null) {
					fromList.add(from);
				}
			}
		}
		return fromList;
	}

	public abstract To transformFrom(From source);

	public abstract From transformTo(To source);

}
