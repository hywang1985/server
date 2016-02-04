package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Bill;
import clinics.jpa.services.BillRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.BillModel;

@Component
public class BillTransformer extends AbstractDTOTransformer<BillModel, Bill> {

	private static final String[] FROM_EXCLUDES = new String[] { "visit", "patient" };
	private static final String[] TO_EXCLUDES = new String[] { "visit", "patient" };

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Autowired
	private BillRepositoryService billRepositoryService;

	@Override
	public Bill transformFrom(BillModel source) {
		Bill dest = null;
		if (source != null) {
			try {
				dest = new Bill();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setParticulars(dest.getParticulars().toUpperCase());
				if (null == source.getId()) {
					if (null != source.getPatient()) {
						dest.setPatient(patientRepositoryService.findOne(source.getPatient()));
					}
					if (null != source.getVisit()) {
						dest.setVisit(visitRepositoryService.findOne(source.getVisit()));
					}
				} else {
					Bill fromDb = billRepositoryService.findById(source.getId());
					if (null != fromDb) {
						dest.setPatient(fromDb.getPatient());
						dest.setVisit(fromDb.getVisit());
					}
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public BillModel transformTo(Bill source) {
		BillModel dest = null;
		if (source != null) {
			try {
				dest = new BillModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
				if (null == source.getDeletable()) {
					dest.setDeletable(false);
				}
				if (null != source.getPatient()) {
					dest.setPatient(source.getPatient().getId());
				}
				if (null != source.getVisit()) {
					dest.setVisit(source.getVisit().getId());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}