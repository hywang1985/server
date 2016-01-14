package clinics.business.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Department;
import clinics.entity.Qualification;
import clinics.entity.Speciality;
import clinics.entity.Staff;
import clinics.entity.StaffDepartment;
import clinics.entity.StaffQualification;
import clinics.entity.StaffSpeciality;
import clinics.jpa.services.DepartmentRepositoryService;
import clinics.jpa.services.QualificationRepositoryService;
import clinics.jpa.services.SpecialityRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.model.IdValueModel;
import clinics.model.StaffModel;
import clinics.transformer.StaffTransformer;

@Service
public class StaffService extends AbstractServiceImpl<Integer, StaffModel, Staff, StaffRepositoryService, StaffTransformer> {

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private StaffTransformer staffTransformer;

	@Autowired
	private DepartmentRepositoryService departmentRepositoryService;

	@Autowired
	private QualificationRepositoryService qualificationRepositoryService;

	@Autowired
	private SpecialityRepositoryService specialityRepositoryService;

	@Override
	protected StaffRepositoryService repoService() {
		return staffRepositoryService;
	}

	@Override
	public StaffTransformer transformer() {
		return staffTransformer;
	}

	@Override
	public List<StaffModel> getAll() {
		return entitiesToModels(repoService().findAll());
	}

	@Override
	public StaffModel save(StaffModel resource) {
		Staff staff = transformer().transformFrom(resource);
		Staff fromDb = null;
		Set<StaffDepartment> existingDepartments = null;
		Set<StaffQualification> existingQualifications = null;
		Set<StaffSpeciality> existingSpecialities = null;
		if (null != staff.getId()) {
			fromDb = repoService().findOne(staff.getId());
			existingDepartments = fromDb.getStaffDepartments();
			existingQualifications = fromDb.getStaffQualifications();
			existingSpecialities = fromDb.getStaffSpecialities();
		}

		mergeDepartments(staff, existingDepartments, resource.getDepartments());
		mergeQualifications(staff, existingQualifications, resource.getQualifications());
		mergeSpecialities(staff, existingSpecialities, resource.getSpecialities());

		Staff saved = repoService().save(staff);
		resource.setId(saved.getId());
		return resource;
	}

	private void mergeDepartments(Staff staff, Set<StaffDepartment> existingDepartments,
			List<IdValueModel> departments) {
		if (null != departments && departments.size() > 0) {
			for (IdValueModel deptId : departments) {
				Department d = departmentRepositoryService.findOne(deptId.getId());
				if (d != null) {
					if (staff.getId() == null) {
						staff.addDepartment(d);
					} else {
						StaffDepartment curr = getStaffDepartment(existingDepartments, d.getId());
						if (curr != null) {
							staff.updateDepartment(curr);
						} else {
							staff.addDepartment(d);
						}
					}
				}
			}
		}
	}
	
	private void mergeQualifications(Staff staff, Set<StaffQualification> existingQualifications, List<IdValueModel> qualifications) {
		if (null != qualifications && qualifications.size() > 0) {
			for (IdValueModel qualId : qualifications) {
				Qualification q = qualificationRepositoryService.findOne(qualId.getId());
				if (q != null) {
					if (staff.getId() == null) {
						staff.addQualification(q);
					} else {
						StaffQualification curr = getStaffQualification(existingQualifications, q.getId());
						if (curr != null) {
							staff.updateQualification(curr);
						} else {
							staff.addQualification(q);
						}
					}
				}
			}
		}
	}
	
	private void mergeSpecialities(Staff staff, Set<StaffSpeciality> existingSpecialities, List<IdValueModel> specialities) {
		if (null != specialities && specialities.size() > 0) {
			for (IdValueModel specId : specialities) {
				Speciality s = specialityRepositoryService.findOne(specId.getId());
				if (s != null) {
					if (staff.getId() == null) {
						staff.addSpeciality(s);
					} else {
						StaffSpeciality curr = getStaffSpeciality(existingSpecialities, s.getId());
						if (curr != null) {
							staff.updateSpeciality(curr);
						} else {
							staff.addSpeciality(s);
						}
					}
				}
			}
		}
	}

	@Override
	public StaffModel getById(Integer id) {
		return toModel(repoService().findById(id));
	}

	private StaffDepartment getStaffDepartment(Set<StaffDepartment> existing, Integer departmentId) {
		for (StaffDepartment staffDepartment : existing) {
			if (staffDepartment.getId().getDepartment().getId() == departmentId) {
				return staffDepartment;
			}
		}
		return null;
	}
	
	private StaffQualification getStaffQualification(Set<StaffQualification> existing, Integer qualificationId) {
		for (StaffQualification staffQualification : existing) {
			if (staffQualification.getId().getQualification().getId() == qualificationId) {
				return staffQualification;
			}
		}
		return null;
	}
	
	private StaffSpeciality getStaffSpeciality(Set<StaffSpeciality> existing, Integer specialityId) {
		for (StaffSpeciality staffSpeciality : existing) {
			if (staffSpeciality.getId().getSpeciality().getId() == specialityId) {
				return staffSpeciality;
			}
		}
		return null;
	}

	public void removeStaffDepartmentById(int staffId, int departmentId) {
		Staff fromDb = repoService().findOne(staffId);
		if (null != fromDb.getId()) {
			Iterator<StaffDepartment> staffDepartmentsIterator = fromDb.getStaffDepartments().iterator();
			while (staffDepartmentsIterator.hasNext()) {
				StaffDepartment staffDepartment = staffDepartmentsIterator.next();
				if (departmentId == staffDepartment.getDepartment().getId()) {
					staffDepartmentsIterator.remove();
				}
			}
			repoService().save(fromDb);
		}
	}
	
	public void removeStaffQualificationById(int staffId, int qualificationId) {
		Staff fromDb = repoService().findOne(staffId);
		if (null != fromDb.getId()) {
			Iterator<StaffQualification> staffQualificationsIterator = fromDb.getStaffQualifications().iterator();
			while (staffQualificationsIterator.hasNext()) {
				StaffQualification staffQualification = staffQualificationsIterator.next();
				if (qualificationId == staffQualification.getQualification().getId()) {
					staffQualificationsIterator.remove();
				}
			}
			repoService().save(fromDb);
		}
	}
	
	public void removeStaffSpecialityById(int staffId, int specialityId) {
		Staff fromDb = repoService().findOne(staffId);
		if (null != fromDb.getId()) {
			Iterator<StaffSpeciality> staffSpecialitiesIterator = fromDb.getStaffSpecialities().iterator();
			while (staffSpecialitiesIterator.hasNext()) {
				StaffSpeciality staffSpeciality = staffSpecialitiesIterator.next();
				if (specialityId == staffSpeciality.getSpeciality().getId()) {
					staffSpecialitiesIterator.remove();
				}
			}
			repoService().save(fromDb);
		}
	}

	public List<StaffModel> entitiesToModels(List<Staff> entities) {
		List<StaffModel> staffs = new ArrayList<StaffModel>();
		for (Staff staff : entities) {
			StaffModel m = toModel(staff);
			staffs.add(m);
		}
		return staffs;
	}

	private StaffModel toModel(Staff staff) {
		StaffModel staffModel = transformer().transformTo(staff);
		Set<StaffDepartment> staffDepartments = staff.getStaffDepartments();
		for (StaffDepartment staffDept : staffDepartments) {
			staffModel.getDepartments().add(new IdValueModel(staffDept.getDepartment().getId()));
		}

		Set<StaffQualification> staffQualifications = staff.getStaffQualifications();
		for (StaffQualification staffQual : staffQualifications) {
			staffModel.getQualifications().add(new IdValueModel(staffQual.getQualification().getId()));
		}

		Set<StaffSpeciality> staffSpecialities = staff.getStaffSpecialities();
		for (StaffSpeciality staffSpec : staffSpecialities) {
			staffModel.getSpecialities().add(new IdValueModel(staffSpec.getSpeciality().getId()));
		}

		return staffModel;
	}
}
