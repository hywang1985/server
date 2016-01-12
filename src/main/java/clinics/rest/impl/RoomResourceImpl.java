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

import clinics.business.services.RoomService;
import clinics.model.RoomModel;

@RestController
@RequestMapping(value = "/rooms", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RoomResourceImpl {

	@Autowired
	private RoomService roomService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<RoomModel> save(@RequestBody RoomModel model) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		model = roomService.save(model);
		responseStatus = HttpStatus.OK;

		return new ResponseEntity<RoomModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public RoomModel getById(@PathVariable("id") int id) {
		return roomService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) {
		RoomModel item = roomService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Room with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				roomService.removeById(id);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Room with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RoomModel>> getAll(@RequestParam(value = "name", required = false) String name) {
		List<RoomModel> items = null;
		if (StringUtils.isNotBlank(name)) {
			items = roomService.getByName(name);
		} else {
			items = roomService.getAll();
		}
		return new ResponseEntity<List<RoomModel>>(items, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{roomId}/equipment/{equipmentId}")
	public ResponseEntity<String> removeRoomEquipmentById(@PathVariable("roomId") int roomId, @PathVariable("equipmentId") int equipmentId) {
		RoomModel item = roomService.getById(roomId);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Room with ID " + roomId + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				roomService.removeRoomEquipmentById(roomId, equipmentId);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Room Equipment with ID " + equipmentId + " cannot be Removed");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
