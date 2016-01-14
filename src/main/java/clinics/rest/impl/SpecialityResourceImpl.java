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

import clinics.business.services.SpecialityService;
import clinics.model.SpecialityModel;

@RestController
@RequestMapping(value = "/specialities", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SpecialityResourceImpl {

	@Autowired
	private SpecialityService specialityService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<SpecialityModel> save(@RequestBody SpecialityModel model) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = specialityService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<SpecialityModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public SpecialityModel getById(@PathVariable("id") int id) {
		return specialityService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		SpecialityModel item = specialityService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Speciality with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				specialityService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Speciality with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SpecialityModel>> getAll(@RequestParam(value = "name", required = false) String name) {
		List<SpecialityModel> items = null;
		if (StringUtils.isNotBlank(name)) {
			items = specialityService.getByName(name);
		} else {
			items = specialityService.getAll();
		}
		return new ResponseEntity<List<SpecialityModel>>(items, HttpStatus.OK);
	}
}
