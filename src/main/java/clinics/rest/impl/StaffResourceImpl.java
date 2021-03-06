package clinics.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import clinics.business.services.StaffService;
import clinics.model.StaffModel;
import clinics.security.YuownTokenAuthenticationService;

@RestController
@RequestMapping(value = "/staffs", produces = { MediaType.APPLICATION_JSON_VALUE })
public class StaffResourceImpl {

	@Autowired
	private StaffService staffService;

	@Autowired
	private YuownTokenAuthenticationService yuownTokenAuthenticationService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<StaffModel> save(@RequestBody StaffModel model, @Context HttpServletRequest httpRequest) {
		model.setCreatedBy(yuownTokenAuthenticationService.getUser(httpRequest));

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = staffService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<StaffModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public StaffModel getById(@PathVariable("id") int id) {
		return staffService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		StaffModel item = staffService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Item with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				staffService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Item with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<StaffModel>> getAll() {
		HttpHeaders headers = new HttpHeaders();
		List<StaffModel> items = staffService.getAll();

		return new ResponseEntity<List<StaffModel>>(items, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{staffId}/department/{departmentId}")
	public ResponseEntity<String> removeStaffDepartmentById(@PathVariable("staffId") int staffId, @PathVariable("departmentId") int departmentId) {
		StaffModel item = staffService.getById(staffId);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Staff with ID " + staffId + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				staffService.removeStaffDepartmentById(staffId, departmentId);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Staff Department with ID " + departmentId + " cannot be Removed");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{staffId}/qualification/{qualificationId}")
	public ResponseEntity<String> removeStaffQualificationById(@PathVariable("staffId") int staffId, @PathVariable("qualificationId") int qualificationId) {
		StaffModel item = staffService.getById(staffId);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Staff with ID " + staffId + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				staffService.removeStaffQualificationById(staffId, qualificationId);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Staff Qualification with ID " + qualificationId + " cannot be Removed");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{staffId}/speciality/{specialityId}")
	public ResponseEntity<String> removeStaffSpecialityById(@PathVariable("staffId") int staffId, @PathVariable("specialityId") int specialityId) {
		StaffModel item = staffService.getById(staffId);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Staff with ID " + staffId + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				staffService.removeStaffSpecialityById(staffId, specialityId);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Staff Speciality with ID " + specialityId + " cannot be Removed");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
