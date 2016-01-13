package clinics.rest.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import clinics.business.services.DepartmentService;
import clinics.model.DepartmentModel;

@RestController
@RequestMapping(value = "/departments", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DepartmentResourceImpl {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<DepartmentModel> save(@RequestBody DepartmentModel model) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = departmentService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<DepartmentModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public DepartmentModel getById(@PathVariable("id") int id) {
		return departmentService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		DepartmentModel item = departmentService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Department with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				departmentService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Department with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DepartmentModel>> getAll(@RequestParam(value = "name", required = false) String name) {
		List<DepartmentModel> items = null;
		if (StringUtils.isNotBlank(name)) {
			items = departmentService.getByName(name);
		} else {
			items = departmentService.getAll();
		}
		return new ResponseEntity<List<DepartmentModel>>(items, HttpStatus.OK);
	}
}
