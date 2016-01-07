package clinics.transformer;

import clinics.entity.BaseEntity;
import clinics.model.Model;

import java.util.List;

public interface DTOTransformer<From extends Model, To extends BaseEntity<?>> {

    public List<To> transformFrom(List<From> sources);

    public To transformFrom(From source);

    public From transformTo(To source);

    public List<From> transformTo(List<To> sources);

}
