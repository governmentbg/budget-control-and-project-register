package bg.infosys.daeu.ws.services;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.daeu.ws.pub.dao.AttachedDocsDAO;
import bg.infosys.daeu.ws.pub.dao.BudgetYearDAO;
import bg.infosys.daeu.ws.pub.dao.PublicBudgetFormAdditionalDataDAO;
import bg.infosys.daeu.ws.pub.dao.PublicBudgetFormDataDAO;
import bg.infosys.daeu.ws.pub.dao.PublicBudgetFormDataDetailDAO;
import bg.infosys.daeu.ws.pub.dao.PublicProjectDataDAO;
import bg.infosys.daeu.ws.pub.dao.PublicProjectDataDetailDAO;
import bg.infosys.daeu.ws.pub.dao.PublicTechSpecDataDAO;
import bg.infosys.daeu.ws.pub.dao.PublicTechSpecDataDetailDAO;
import bg.infosys.daeu.ws.pub.entity.AttachedDoc;
import bg.infosys.daeu.ws.pub.entity.BudgetYear;
import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormAdditionalData;
import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormData;
import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormDataDetail;
import bg.infosys.daeu.ws.pub.entity.PublicProjectData;
import bg.infosys.daeu.ws.pub.entity.PublicProjectDataDetail;
import bg.infosys.daeu.ws.pub.entity.PublicTechSpecData;
import bg.infosys.daeu.ws.pub.entity.PublicTechSpecDataDetail;

@Service
public class PublicInfoService {
    @Autowired private PublicBudgetFormDataDAO publicBudgetFormDataDAO;
    @Autowired private PublicProjectDataDAO publicProjectDataDAO;
    @Autowired private PublicTechSpecDataDAO publicTechSpecDataDAO;
    
    @Autowired private PublicBudgetFormDataDetailDAO publicBudgetFormDataDetailDAO;
    @Autowired private PublicBudgetFormAdditionalDataDAO publicBudgetFormAdditionalDataDAO;
    @Autowired private PublicProjectDataDetailDAO publicProjectDataDetailDAO;
    @Autowired private PublicTechSpecDataDetailDAO publicTechSpecDataDetailDAO;
    
    @Autowired private AttachedDocsDAO attachedDocsDAO;
    
    @Autowired private BudgetYearDAO budgetYearDAO;

    @Transactional("secondaryTransactionManager")
    public PublicBudgetFormData findPublicBudgetForm(Integer publicId) {
    	try {
    		return publicBudgetFormDataDAO.findByPublicId(publicId);
    	} catch (NoResultException | NonUniqueResultException e) {
    		return null;
    	}
    }

    @Transactional("secondaryTransactionManager")
    public PublicProjectData findPublicProject(Integer publicId) {
    	try {
    		return publicProjectDataDAO.findByPublicId(publicId);
    	} catch (NoResultException | NonUniqueResultException e) {
    		return null;
    	}
    }
    
    @Transactional("secondaryTransactionManager")
    public PublicTechSpecData findPublicTechSpec(Integer publicId) {
    	try {
    		return publicTechSpecDataDAO.findByPublicId(publicId);
    	} catch (NoResultException | NonUniqueResultException e) {
    		return null;
    	}
    }
    
    @Transactional("secondaryTransactionManager")
    public PublicBudgetFormData savePublicBudgetForm(PublicBudgetFormData data) {
    	return publicBudgetFormDataDAO.saveOrUpdate(data);
    }

    @Transactional("secondaryTransactionManager")
    public PublicProjectData savePublicProject(PublicProjectData data) {
    	return publicProjectDataDAO.saveOrUpdate(data);
    }
    
    @Transactional("secondaryTransactionManager")
    public PublicTechSpecData savePublicTechSpec(PublicTechSpecData data) {
    	return publicTechSpecDataDAO.saveOrUpdate(data);
    }

    @Transactional("secondaryTransactionManager")
	public List<PublicProjectDataDetail> findProjectDetailsById(Integer publicId) {
		return publicProjectDataDetailDAO.findByPublicId(publicId);
    }

    @Transactional("secondaryTransactionManager")
	public PublicProjectDataDetail savePublicDetail(PublicProjectDataDetail publicProjectDataDetail) {
		return publicProjectDataDetailDAO.saveOrUpdate(publicProjectDataDetail);
	}
    
    @Transactional("secondaryTransactionManager")
   	public List<PublicTechSpecDataDetail> findTechSpecDetailsById(Integer publicId) {
   		return publicTechSpecDataDetailDAO.findByPublicId(publicId);
    }

    @Transactional("secondaryTransactionManager")
   	public PublicTechSpecDataDetail savePublicDetail(PublicTechSpecDataDetail publicTechSpecDataDetail) {
   		return publicTechSpecDataDetailDAO.saveOrUpdate(publicTechSpecDataDetail);
   	}
    
    @Transactional("secondaryTransactionManager")
   	public List<PublicBudgetFormDataDetail> findBudgetFormDetailsById(Integer publicId) {
   		return publicBudgetFormDataDetailDAO.findByPublicId(publicId);
    }

    @Transactional("secondaryTransactionManager")
   	public PublicBudgetFormDataDetail savePublicDetail(PublicBudgetFormDataDetail publicBudgetFormDataDetail) {
   		return publicBudgetFormDataDetailDAO.saveOrUpdate(publicBudgetFormDataDetail);
   	}

    @Transactional("secondaryTransactionManager")
	public BudgetYear saveBudgetYear(BudgetYear year) {
    	return budgetYearDAO.saveOrUpdate(year);
	}

    @Transactional("secondaryTransactionManager")
	public List<PublicBudgetFormAdditionalData> findBudgetFormAddFieldsById(Integer publicId) {
		return publicBudgetFormAdditionalDataDAO.findByPublicId(publicId);
	}

    @Transactional("secondaryTransactionManager")
	public void saveBudgetAddField(PublicBudgetFormAdditionalData entity) {
    	publicBudgetFormAdditionalDataDAO.saveOrUpdate(entity);
	}
    
    @Transactional("secondaryTransactionManager")
	public void saveAttachedDoc(AttachedDoc entity) {
    	attachedDocsDAO.saveOrUpdate(entity);
	}
}
