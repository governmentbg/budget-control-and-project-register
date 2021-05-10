package bg.infosys.daeu.ws.envers;

import org.hibernate.envers.RevisionListener;

public class RevListener implements RevisionListener {

	@SuppressWarnings("unused")
	@Override
	public void newRevision(Object revisionEntity) {
		Revision revEntity = (Revision) revisionEntity;
		
	}
}
