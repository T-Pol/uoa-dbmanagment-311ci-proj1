package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.GraffitiRemoval;

public interface GraffitiRemovalService {

	public GraffitiRemoval save(GraffitiRemoval gr);

	GraffitiRemoval findbyid(String id);

	GraffitiRemoval update(GraffitiRemoval g);
}