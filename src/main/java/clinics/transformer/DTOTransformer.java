package clinics.transformer;

import java.util.List;
import java.util.Set;

import clinics.entity.BaseEntity;
import clinics.model.Model;

public interface DTOTransformer<From extends Model, To extends BaseEntity<?>> {

	public Set<To> transformFrom(Set<From> sources);

	public To transformFrom(From source);

	public From transformTo(To source);

	public Set<From> transformTo(Set<To> sources);

	public List<To> transformFrom(List<From> sources);

	public List<From> transformTo(List<To> sources);
}
