package clinics.rest.impl;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/meta", produces = { MediaType.APPLICATION_JSON_VALUE })
public class MetaResourceImpl {

	@RequestMapping(value = "/itemTypes", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> itemTypes() {
		return null;
	}
}
