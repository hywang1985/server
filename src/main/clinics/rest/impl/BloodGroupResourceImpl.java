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

import clinics.business.services.BloodGroupService;
import clinics.model.meta.BloodGroupModel;
import clinics.security.YuownTokenAuthenticationService;

@RestController
@RequestMapping(value = "/bloodGroups", produces = { MediaType.APPLICATION_JSON_VALUE })
public class BloodGroupResourceImpl {

	@Autowired
	private BloodGroupService bloodGroupService;

	@Autowired
	private YuownTokenAuthenticationService yuownTokenAuthenticationService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BloodGroupModel> save(@RequestBody BloodGroupModel model, @Context HttpServletRequest httpRequest) {
		model.setCreatedBy(yuownTokenAuthenticationService.getUser(httpRequest));

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = bloodGroupService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<BloodGroupModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public BloodGroupModel getById(@PathVariable("id") int id) {
		return bloodGroupService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		BloodGroupModel item = bloodGroupService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "BloodGroup with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				bloodGroupService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "BloodGroup with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BloodGroupModel>> getAll() {
		List<BloodGroupModel> items = bloodGroupService.getAll();
		return new ResponseEntity<List<BloodGroupModel>>(items, HttpStatus.OK);
	}
}
