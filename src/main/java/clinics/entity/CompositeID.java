package clinics.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public abstract class CompositeID<E1 extends Serializable, E2 extends Serializable> {

	public abstract E1 getE1();

	public abstract void setE1(E1 e);

	public abstract E2 getE2();

	public abstract void setE2(E2 e);

}
