package clinics.rest.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import clinics.enums.Gender;
import clinics.enums.Prefix;

@RestController
@RequestMapping(value = "/meta", produces = { MediaType.APPLICATION_JSON_VALUE })
public class MetaResourceImpl {

	@RequestMapping(value = "/genders", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> genders() {
		Gender[] all = Gender.values();
		Set<String> asSet = new HashSet<String>();
		for (Gender each : all) {
			asSet.add(each.toString());
		}
		return asSet;
	}

	@RequestMapping(value = "/prefixes", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> prefixes() {
		Prefix[] all = Prefix.values();
		Set<String> asSet = new HashSet<String>();
		for (Prefix each : all) {
			asSet.add(each.toString());
		}
		return asSet;
	}
}
