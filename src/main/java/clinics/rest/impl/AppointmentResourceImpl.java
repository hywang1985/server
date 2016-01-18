package clinics.rest.impl;

import java.util.List;

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

import clinics.business.services.AppointmentService;
import clinics.model.AppointmentModel;

@RestController
@RequestMapping(value = "/appointments", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AppointmentResourceImpl {

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<AppointmentModel> save(@RequestBody AppointmentModel model) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = appointmentService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<AppointmentModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public AppointmentModel getById(@PathVariable("id") int id) {
		return appointmentService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		AppointmentModel item = appointmentService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Appointment with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				appointmentService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Appointment with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AppointmentModel>> getAll() {
		List<AppointmentModel> items = appointmentService.getAll();
		return new ResponseEntity<List<AppointmentModel>>(items, HttpStatus.OK);
	}
}
