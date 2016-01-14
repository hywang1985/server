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

import clinics.business.services.QualificationService;
import clinics.model.QualificationModel;

@RestController
@RequestMapping(value = "/qualifications", produces = { MediaType.APPLICATION_JSON_VALUE })
public class QualificationResourceImpl {

	@Autowired
	private QualificationService qualificationService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<QualificationModel> save(@RequestBody QualificationModel model) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = qualificationService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<QualificationModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public QualificationModel getById(@PathVariable("id") int id) {
		return qualificationService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		QualificationModel item = qualificationService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Qualification with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				qualificationService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Qualification with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<QualificationModel>> getAll(
			@RequestParam(value = "name", required = false) String name) {
		List<QualificationModel> items = null;
		if (StringUtils.isNotBlank(name)) {
			items = qualificationService.getByName(name);
		} else {
			items = qualificationService.getAll();
		}
		return new ResponseEntity<List<QualificationModel>>(items, HttpStatus.OK);
	}
}
