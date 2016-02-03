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

import clinics.business.services.BillService;
import clinics.business.services.ConfigurationService;
import clinics.business.services.DiagnosticService;
import clinics.business.services.MedicationService;
import clinics.business.services.PatientService;
import clinics.business.services.VisitService;
import clinics.entity.Patient;
import clinics.model.BillModel;
import clinics.model.DiagnosticModel;
import clinics.model.MedicationModel;
import clinics.model.PatientModel;
import clinics.model.UserModel;
import clinics.model.VisitModel;
import clinics.security.YuownTokenAuthenticationService;
import clinics.utils.Constants;

@RestController
@RequestMapping(value = "/patients", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PatientResourceImpl {

	@Autowired
	private PatientService patientService;

	@Autowired
	private VisitService visitService;

	@Autowired
	private MedicationService medicationService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private BillService billService;

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
				visitService.removeByPatientId(item.getId());
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
			pagedItems = patientService.getAllByPageNumber(page, size,
					configurationService.getIntPropertyFromCache(Constants.PAGE_SIZE));
		}
		items = patientService.transformer().transformTo(pagedItems.getContent());

		headers.add("pages", pagedItems.getTotalPages() + StringUtils.EMPTY);
		headers.add("totalItems", pagedItems.getTotalElements() + StringUtils.EMPTY);

		return new ResponseEntity<List<PatientModel>>(items, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/visits")
	public ResponseEntity<List<VisitModel>> getPatientVisits(@PathVariable("id") int id) {
		PatientModel patient = patientService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		List<VisitModel> visits = null;
		if (null != patient) {
			visits = visitService.getAllVisitsOfPatient(patient.getId());
		} else {
			headers.add("errorMessage", "Patient ID Invalid");
		}
		return new ResponseEntity<List<VisitModel>>(visits, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/visits", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<VisitModel> saveVisit(@PathVariable("id") int id, @RequestBody VisitModel model,
			@Context HttpServletRequest httpRequest) {
		model.setCreatedBy(yuownTokenAuthenticationService.getUser(httpRequest));

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		PatientModel patient = patientService.getById(id);
		if (null != patient) {
			model.setPatientId(patient.getId());
			model = visitService.save(model);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<VisitModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{patientId}/visits/{visitId}/medications")
	public ResponseEntity<List<MedicationModel>> getPatientMedications(@PathVariable("patientId") int patientId, @PathVariable("visitId") int visitId) {
		PatientModel patient = patientService.getById(patientId);
		VisitModel visit = visitService.getById(visitId);
		HttpHeaders headers = new HttpHeaders();
		List<MedicationModel> medications = null;
		if (null != patient && null != visit) {
			medications = medicationService.getPatientVisitMedications(patient.getId(), visit.getId());
		} else {
			headers.add("errorMessage", "Patient ID or Visit ID Invalid");
		}
		return new ResponseEntity<List<MedicationModel>>(medications, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{patientId}/visits/{visitId}/medications", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<MedicationModel> saveMedication(@PathVariable("patientId") int patientId,
			@PathVariable("visitId") int visitId, @RequestBody MedicationModel model,
			@Context HttpServletRequest httpRequest) {
		UserModel user = yuownTokenAuthenticationService.getUserObject(httpRequest);

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		PatientModel patient = patientService.getById(patientId);
		VisitModel visit = visitService.getById(visitId);
		if (null != patient && null != visit) {
			model.setPatient(patient.getId());
			model.setVisit(visit.getId());
			model.setDoctor(user.getStaff());
			model = medicationService.save(model);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<MedicationModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/medications/{medicationId}")
	@ResponseBody
	public ResponseEntity<String> deleteMedication(@PathVariable("medicationId") int medicationId) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		MedicationModel medication = medicationService.getById(medicationId);
		if (null != medication) {
			medicationService.removeById(medicationId);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<String>(headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{patientId}/visits/{visitId}/diagnostics")
	public ResponseEntity<List<DiagnosticModel>> getPatientDiagnostics(@PathVariable("patientId") int patientId, @PathVariable("visitId") int visitId) {
		PatientModel patient = patientService.getById(patientId);
		VisitModel visit = visitService.getById(visitId);
		HttpHeaders headers = new HttpHeaders();
		List<DiagnosticModel> diagnostics = null;
		if (null != patient && null != visit) {
			diagnostics = diagnosticService.getPatientVisitDiagnostics(patient.getId(), visit.getId());
		} else {
			headers.add("errorMessage", "Patient ID or Visit ID Invalid");
		}
		return new ResponseEntity<List<DiagnosticModel>>(diagnostics, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{patientId}/visits/{visitId}/diagnostics", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<DiagnosticModel> saveDiagnostic(@PathVariable("patientId") int patientId,
			@PathVariable("visitId") int visitId, @RequestBody DiagnosticModel model,
			@Context HttpServletRequest httpRequest) {
		UserModel user = yuownTokenAuthenticationService.getUserObject(httpRequest);

		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		PatientModel patient = patientService.getById(patientId);
		VisitModel visit = visitService.getById(visitId);
		if (null != patient && null != visit) {
			model.setPatient(patient.getId());
			model.setVisit(visit.getId());
			model.setDoctor(user.getStaff());
			model = diagnosticService.save(model);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<DiagnosticModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/diagnostics/{diagnosticId}")
	@ResponseBody
	public ResponseEntity<String> deleteDiagnostic(@PathVariable("diagnosticId") int diagnosticId) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		DiagnosticModel diagnostic = diagnosticService.getById(diagnosticId);
		if (null != diagnostic) {
			diagnosticService.removeById(diagnosticId);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<String>(headers, responseStatus);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{patientId}/visits/{visitId}/bills")
	public ResponseEntity<List<BillModel>> getPatientBills(@PathVariable("patientId") int patientId, @PathVariable("visitId") int visitId) {
		PatientModel patient = patientService.getById(patientId);
		VisitModel visit = visitService.getById(visitId);
		HttpHeaders headers = new HttpHeaders();
		List<BillModel> bills = null;
		if (null != patient && null != visit) {
			bills = billService.getPatientVisitBills(patient.getId(), visit.getId());
		} else {
			headers.add("errorMessage", "Patient ID or Visit ID Invalid");
		}
		return new ResponseEntity<List<BillModel>>(bills, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{patientId}/visits/{visitId}/bills", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BillModel> saveBill(@PathVariable("patientId") int patientId,
			@PathVariable("visitId") int visitId, @RequestBody BillModel model,
			@Context HttpServletRequest httpRequest) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		PatientModel patient = patientService.getById(patientId);
		VisitModel visit = visitService.getById(visitId);
		if (null != patient && null != visit) {
			model.setPatient(patient.getId());
			model.setVisit(visit.getId());
			model = billService.save(model);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<BillModel>(model, headers, responseStatus);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/bills/{billId}")
	@ResponseBody
	public ResponseEntity<String> deleteBill(@PathVariable("billId") int billId) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		BillModel bill = billService.getById(billId);
		if (null != bill) {
			billService.removeById(billId);
			responseStatus = HttpStatus.OK;
		}
		return new ResponseEntity<String>(headers, responseStatus);
	}
}
