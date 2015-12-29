package clinics.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import clinics.business.services.ConfigurationService;
import clinics.business.services.PatientService;
import clinics.entity.Patient;
import clinics.model.PatientModel;
import clinics.security.YuownTokenAuthenticationService;
import clinics.utils.Constants;

@RestController
@RequestMapping(value = "/patients", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PatientResourceImpl {

	@Autowired
	private PatientService patientService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private YuownTokenAuthenticationService yuownTokenAuthenticationService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<PatientModel> save(@RequestBody PatientModel model, @Context HttpServletRequest httpRequest) {
		model.setCreatedBy(yuownTokenAuthenticationService.getUser(httpRequest));

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = patientService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<PatientModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public PatientModel getById(@PathVariable("id") int id) {
		return patientService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		PatientModel item = patientService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Item with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				patientService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Item with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PatientModel>> getAll(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		HttpHeaders headers = new HttpHeaders();
		Page<Patient> pagedItems = null;
		List<PatientModel> items = null;
		if (StringUtils.isNotBlank(name)) {
			pagedItems = patientService.search(name, page, size);
		} else {
			pagedItems = patientService.getAllByPageNumber(page, size, configurationService.getIntPropertyFromCache(Constants.PAGE_SIZE));
		}
		items = patientService.transformer().transformTo(pagedItems.getContent());

		headers.add("pages", pagedItems.getTotalPages() + StringUtils.EMPTY);
		headers.add("totalItems", pagedItems.getTotalElements() + StringUtils.EMPTY);

		return new ResponseEntity<List<PatientModel>>(items, headers, HttpStatus.OK);
	}
}
