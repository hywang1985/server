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

import clinics.business.services.DiagnosticTypeService;
import clinics.model.DiagnosticTypeModel;
import clinics.security.YuownTokenAuthenticationService;

@RestController
@RequestMapping(value = "/diagnosticTypes", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DiagnosticTypeResourceImpl {

	@Autowired
	private DiagnosticTypeService diagnosticTypeService;

	@Autowired
	private YuownTokenAuthenticationService yuownTokenAuthenticationService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<DiagnosticTypeModel> save(@RequestBody DiagnosticTypeModel model, @Context HttpServletRequest httpRequest) {
		model.setCreatedBy(yuownTokenAuthenticationService.getUser(httpRequest));

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = diagnosticTypeService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<DiagnosticTypeModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public DiagnosticTypeModel getById(@PathVariable("id") int id) {
		return diagnosticTypeService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		DiagnosticTypeModel item = diagnosticTypeService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "DiagnosticType with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				diagnosticTypeService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "DiagnosticType with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DiagnosticTypeModel>> getAll() {
		List<DiagnosticTypeModel> items = diagnosticTypeService.getAll();
		return new ResponseEntity<List<DiagnosticTypeModel>>(items, HttpStatus.OK);
	}
}
