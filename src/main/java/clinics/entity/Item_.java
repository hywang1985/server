package clinics.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Patient.class)
public class Item_ {

	public static volatile SingularAttribute<Patient, Integer> id;
	public static volatile SingularAttribute<Patient, String> name;
	public static volatile SingularAttribute<Patient, String> itemType;
	public static volatile SingularAttribute<Patient, Category> category;
	public static volatile SingularAttribute<Patient, Long> createDate;
	public static volatile SingularAttribute<Patient, Long> updateDate;
	public static volatile SingularAttribute<Patient, Supplier> supplier;
	public static volatile SingularAttribute<Patient, StockType> stockType;
	public static volatile SingularAttribute<Patient, Supplier> lendTo;
	public static volatile SingularAttribute<Patient, Long> lendDate;
	public static volatile SingularAttribute<Patient, Boolean> sold;

}
