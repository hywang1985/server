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

import clinics.business.services.ConfigurationService;
import clinics.model.ConfigurationModel;

@RestController
@RequestMapping(value = "/settings", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SettingsResourceImpl {

	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping(value = "/configs", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ConfigurationModel>> getAll() {
		HttpHeaders headers = new HttpHeaders();
		List<ConfigurationModel> all = configurationService.getAll();

		return new ResponseEntity<List<ConfigurationModel>>(all, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/configs", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<ConfigurationModel> save(@RequestBody ConfigurationModel model, @Context HttpServletRequest httpRequest) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
		model = configurationService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<ConfigurationModel>(model, headers, responseStatus);
	}

	@RequestMapping(value = "/configs/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		ConfigurationModel item = configurationService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Config Item with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				configurationService.remove(item);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Config Item with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
