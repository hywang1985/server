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

import clinics.business.services.EquipmentService;
import clinics.model.EquipmentModel;

@RestController
@RequestMapping(value = "/equipments", produces = { MediaType.APPLICATION_JSON_VALUE })
public class EquipmentResourceImpl {

	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<EquipmentModel> save(@RequestBody EquipmentModel model) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = equipmentService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<EquipmentModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EquipmentModel getById(@PathVariable("id") int id) {
		return equipmentService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		EquipmentModel item = equipmentService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Equipment with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				equipmentService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Equipment with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EquipmentModel>> getAll(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "working", required = false) Boolean working) {
		List<EquipmentModel> items = null;
		if (StringUtils.isNotBlank(name)) {
			items = equipmentService.getByName(name);
		} else {
			items = equipmentService.getAll(working);
		}
		return new ResponseEntity<List<EquipmentModel>>(items, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/available")
	public ResponseEntity<List<EquipmentModel>> availableEquipments() {
		return new ResponseEntity<List<EquipmentModel>>(equipmentService.getAvailableEquipments(), HttpStatus.OK);
	}
}
