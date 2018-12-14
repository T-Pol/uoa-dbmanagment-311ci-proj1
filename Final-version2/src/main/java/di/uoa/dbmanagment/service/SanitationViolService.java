package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.SanitationViol;

public interface SanitationViolService {

	public SanitationViol save(SanitationViol sv);

	SanitationViol findbyid(String id);

	SanitationViol update(SanitationViol st);
}