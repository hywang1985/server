package clinics.rest.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import clinics.enums.Gender;
import clinics.enums.MedicationRule;
import clinics.enums.Prefix;
import clinics.enums.QuantityType;
import clinics.enums.VisitType;

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

	@RequestMapping(value = "/visitTypes", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> visitTypes() {
		VisitType[] all = VisitType.values();
		Set<String> asSet = new HashSet<String>();
		for (VisitType each : all) {
			asSet.add(each.toString());
		}
		return asSet;
	}

	@RequestMapping(value = "/medicationRules", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> medicationRules() {
		MedicationRule[] all = MedicationRule.values();
		Set<String> asSet = new HashSet<String>();
		for (MedicationRule each : all) {
			asSet.add(each.toString());
		}
		return asSet;
	}

	@RequestMapping(value = "/quantityTypes", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> quantityTypes() {
		QuantityType[] all = QuantityType.values();
		Set<String> asSet = new HashSet<String>();
		for (QuantityType each : all) {
			asSet.add(each.toString());
		}
		return asSet;
	}
}
