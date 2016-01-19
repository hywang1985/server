package clinics.transformer;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Staff;
import clinics.jpa.services.UserRepositoryService;
import clinics.model.StaffModel;

@Component
public class StaffTransformer extends AbstractDTOTransformer<StaffModel, Staff> {

	private static final String[] FROM_EXCLUDES = new String[] { "staffDepartments", "user" };
	private static final String[] TO_EXCLUDES = new String[] { "staffDepartments", "user" };

	@Autowired
	private UserRepositoryService userRepositoryService;

	@Override
	public Staff transformFrom(StaffModel source) {
		Staff dest = null;
		if (source != null) {
			try {
				dest = new Staff();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				if (dest.getId() == null) {
					dest.setCreatedDate(new Date());
				} else {
					dest.setModifiedDate(new Date());
				}
				if (StringUtils.isNotBlank(source.getFirstName())) {
					dest.setFirstName(source.getFirstName());
				}
				if (StringUtils.isNotBlank(source.getLastName())) {
					dest.setLastName(source.getLastName());
				}
				if (null != source.getUser()) {
					dest.setUser(userRepositoryService.findOne(source.getUser()));
					dest.getUser().setStaff(dest);
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public StaffModel transformTo(Staff source) {
		StaffModel dest = null;
		if (source != null) {
			try {
				dest = new StaffModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
				if (null != source.getUser()) {
					dest.setUser(source.getUser().getId());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}