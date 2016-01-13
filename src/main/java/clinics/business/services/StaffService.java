package clinics.business.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import clinics.entity.Department;
import clinics.entity.Staff;
import clinics.entity.StaffDepartment;
import clinics.jpa.services.DepartmentRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.model.IdValueModel;
import clinics.model.StaffModel;
import clinics.transformer.StaffTransformer;
import clinics.utils.Constants;

@Service
public class StaffService extends AbstractServiceImpl<Integer, StaffModel, Staff, StaffRepositoryService, StaffTransformer> {

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private StaffTransformer staffTransformer;

	@Autowired
	private DepartmentRepositoryService departmentRepositoryService;
	
	@Autowired
	private ConfigurationService configurationService;

	@Override
	protected StaffRepositoryService repoService() {
		return staffRepositoryService;
	}

	@Override
	public StaffTransformer transformer() {
		return staffTransformer;
	}

	public Page<Staff> search(String name, Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		Integer fromSystem = configurationService.getIntPropertyFromCache(Constants.PAGE_SIZE);
		if (size == null || (size < 0 || size > fromSystem)) {
			size = fromSystem;
		}
		return repoService().findAllByNameLike(name.toUpperCase(), new PageRequest(page, size));
	}

	protected Long getBeginTimeStampForDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	protected Long getEndTimeStampForDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	@Override
	public StaffModel save(StaffModel resource) {
		Staff staff = transformer().transformFrom(resource);
		Staff fromDb = null;
		Set<StaffDepartment> existing = null;
		if (null != staff.getId()) {
			fromDb = repoService().findOne(staff.getId());
			existing = fromDb.getStaffDepartments();
		}
		List<IdValueModel> departments = resource.getDepartments();

		if (null != departments && departments.size() > 0) {
			for (IdValueModel deptId : departments) {
				Department d = departmentRepositoryService.findOne(deptId.getId());
				if (d != null) {
					if (staff.getId() == null) {
						staff.addDepartment(d);
					} else {
						StaffDepartment curr = getStaffDepartment(existing, d.getId());
						if (curr != null) {
							staff.updateDepartment(curr);
						} else {
							staff.addDepartment(d);
						}
					}
				}
			}
		}
		repoService().save(staff);
		return resource;
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
			Department department = staffDept.getDepartment();
			staffModel.getDepartments().add(new IdValueModel(department.getId()));
		}
		return staffModel;
	}
}
