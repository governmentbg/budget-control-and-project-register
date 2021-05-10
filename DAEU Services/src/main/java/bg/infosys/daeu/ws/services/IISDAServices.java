package bg.infosys.daeu.ws.services;

import bg.infosys.daeu.db.dao.pub.OrganizationTypeDAO;
import bg.infosys.daeu.db.dao.pub.SubjectDAO;
import bg.infosys.daeu.db.dao.pub.SubjectVersionDAO;
import bg.infosys.daeu.db.entity.pub.OrganizationType;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IISDAServices {

    @Autowired
    private SubjectVersionDAO subjectVersionDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private OrganizationTypeDAO organizationTypeDAO;

    public Subject saveSubject(Subject subject) {
        return subjectDAO.saveOrUpdate(subject);
    }

    public List<Subject> findAllSubjects(OrganizationType orgType) {
        return subjectDAO.findAllSubjectsByOrgType(orgType);
    }

    public List<SubjectVersion> findAllSubjectVersion() {
        return subjectVersionDAO.findAll();
    }

    public OrganizationType getOrganizationByCode(String code) {
        return organizationTypeDAO.getOrganizationByCode(code);
    }

}
