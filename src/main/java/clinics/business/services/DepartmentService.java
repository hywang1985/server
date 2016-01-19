package clinics.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Department;
import clinics.entity.Staff;
import clinics.entity.StaffDepartment;
import clinics.enums.Days;
import clinics.jpa.services.DepartmentRepositoryService;
import clinics.model.DepartmentModel;
import clinics.model.StaffModel;
import clinics.transformer.DepartmentTransformer;
import clinics.transformer.StaffTransformer;

@Service
public class DepartmentService extends AbstractServiceImpl<Integer, DepartmentModel, Department, DepartmentRepositoryService, DepartmentTransformer> {

	@Autowired
	private DepartmentRepositoryService departmentRepositoryService;

	@Autowired
	private DepartmentTransformer departmentTransformer;

	@Autowired
	private StaffTransformer staffTransformer;

	@Override
	protected DepartmentRepositoryService repoService() {
		return departmentRepositoryService;
	}

	@Override
	protected DepartmentTransformer transformer() {
		return departmentTransformer;
	}

	public List<DepartmentModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public DepartmentModel save(DepartmentModel resource) {
		Department equipment = transformer().transformFrom(resource);
		if (null != equipment.getId()) {
			Department fromDb = repoService().findOne(equipment.getId());
			Set<StaffDepartment> existing = fromDb.getStaffDepartments();
			if (null != resource.getChief()) {
				for (StaffDepartment staffDepartment : existing) {
					if (staffDepartment.getStaff().getId() == resource.getChief()) {
						staffDepartment.setChief(true);
					} else {
						staffDepartment.setChief(false);
					}
				}
			}
			equipment.getStaffDepartments().addAll(existing);
		}
		repoService().save(equipment);
		return resource;
	}

	public List<DepartmentModel> getAll() {
		List<Department> all = repoService().findAll();
		return getDepartmentWithStaff(all);
	}

	public List<StaffModel> getDeptStaff(Integer deptId) {
		List<StaffModel> staffs = new ArrayList<StaffModel>();
		Department department = repoService().findOne(deptId);
		if (null != department) {
			Set<StaffDepartment> l = department.getStaffDepartments();
			for (StaffDepartment staffDepartment : l) {
				StaffModel staff = staffTransformer.transformTo(staffDepartment.getStaff());
				staffs.add(staff);
			}
		}
		return staffs;
	}

	public List<StaffModel> getDeptStaffByDay(Integer deptId, Integer day) {
		List<StaffModel> staffs = new ArrayList<StaffModel>();
		Department department = repoService().findOne(deptId);
		if (null != department) {
			Set<StaffDepartment> l = department.getStaffDepartments();
			for (StaffDepartment staffDepartment : l) {
				if (canGo(staffDepartment.getStaff(), day)) {
					StaffModel staff = staffTransformer.transformTo(staffDepartment.getStaff());
					staffs.add(staff);
				}
			}
		}
		return staffs;
	}

	private boolean canGo(Staff staff, Integer day) {
		boolean flag = false;
		Days d = Days.values()[day];
		switch (d) {
		case MONDAY:
			flag = staff.getMon() != null ? staff.getMon() : false;
			break;
		case TUESDAY:
			flag = staff.getTue() != null ? staff.getTue() : false;
			break;
		case WEDNSDAY:
			flag = staff.getWed() != null ? staff.getWed() : false;
			break;
		case THURSDAY:
			flag = staff.getThu() != null ? staff.getThu() : false;
			break;
		case FRIDAY:
			flag = staff.getFri() != null ? staff.getFri() : false;
			break;
		case SATURDAY:
			flag = staff.getSat() != null ? staff.getSat() : false;
			break;
		case SUNDAY:
			flag = staff.getSun() != null ? staff.getSun() : false;
			break;
		}
		return flag;
	}

	public List<DepartmentModel> getAllAppointmentable() {
		List<Department> all = repoService().findAllAppointmentable(true);
		return getDepartmentWithStaff(all);
	}

	private List<DepartmentModel> getDepartmentWithStaff(List<Department> all) {
		List<DepartmentModel> allDepts = new ArrayList<DepartmentModel>();
		for (Department department : all) {
			DepartmentModel transformTo = departmentTransformer.transformTo(department);
			Set<StaffDepartment> staffDepts = department.getStaffDepartments();
			for (StaffDepartment staffDepartment : staffDepts) {
				if (null != staffDepartment.getChief() && staffDepartment.getChief()) {
					transformTo.setChief(staffDepartment.getStaff().getId());
					break;
				}
			}
			allDepts.add(transformTo);
		}
		return allDepts;
	}
}
