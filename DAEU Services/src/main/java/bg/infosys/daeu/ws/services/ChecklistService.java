package bg.infosys.daeu.ws.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infosys.daeu.db.dao.pub.ChecklistConfigDAO;
import bg.infosys.daeu.db.dao.pub.ChecklistControlConfigDAO;
import bg.infosys.daeu.db.dao.pub.ChecklistRelationDAO;
import bg.infosys.daeu.db.dao.pub.ChecklistStatusDAO;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.ChecklistConfig;
import bg.infosys.daeu.db.entity.pub.ChecklistControlConfig;
import bg.infosys.daeu.db.entity.pub.ChecklistRelation;
import bg.infosys.daeu.db.entity.pub.ChecklistStatus;
import bg.infosys.daeu.db.entity.pub.ChecklistType;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.ws.dto.ChecklistInfoDto;
import bg.infosys.daeu.db.entity.pub.ChecklistStatus.ChecklistStatusConst;

@Service
public class ChecklistService {
	@Autowired private ChecklistStatusDAO checklistStatusDAO;
	@Autowired private ChecklistControlConfigDAO checklistControlConfigDAO;
	@Autowired private ChecklistConfigDAO checklistConfigDAO;
	@Autowired private ChecklistRelationDAO checklistRelationDAO;
	
	@Transactional
	public Checklist createChecklist(ChecklistType checklistType, boolean dontGetHiddenColumns) {
		Checklist result;
		
		List<ChecklistConfig> parentChecklistConfigs = findChecklistElementsByChecklistType(checklistType, dontGetHiddenColumns);
		List<ChecklistControlConfig> parentChecklistControlConfigs = findChecklistControlConfigByChecklistType(checklistType, dontGetHiddenColumns);
		
		result = new Checklist(parentChecklistConfigs, parentChecklistControlConfigs);
		result.setChecklistStatus(findChecklistStatusByCode(ChecklistStatusConst.IN_PROGRESS.getCode()));
		result.setChecklistType(checklistType);
		
		return result;
	}
	
	@Transactional
	public List<ChecklistRelation> createChecklistsRelations(Checklist parentChecklist, Iterator<ChecklistType> typesIterator, boolean dontGetHiddenColumns) {
		List<ChecklistRelation> relations = new ArrayList<ChecklistRelation>();

		while (typesIterator.hasNext()) {
			ChecklistType checklistType = typesIterator.next();

			Checklist checklist = createChecklist(checklistType, dontGetHiddenColumns);

			ChecklistRelation newRelation = new ChecklistRelation();
			newRelation.setParentChecklist(parentChecklist);
			newRelation.setChildChecklist(checklist);

			relations.add(newRelation);
		}

		return relations;
	}
	
	@Transactional
	public ChecklistInfoDto createParentChecklistAndRelations(FormType formType, boolean dontGetHiddenColumns) {
		ChecklistInfoDto result = new ChecklistInfoDto();
		
		Set<ChecklistType> types = formType.getTypes().stream()
													  .filter(type -> type.getIsValid().equals("Y"))
													  .collect(Collectors.toSet());
		
		Optional<ChecklistType> crucialType = types.stream()
				  								   .filter(type -> type.getIsCrucial().equals("Y"))
				  								   .findAny();
		
		if (crucialType.isPresent()) {
			ChecklistType type = crucialType.get();
			
			result.setParentChecklist(createChecklist(type, dontGetHiddenColumns));
		} else {
			Iterator<ChecklistType> typesIterator = types.iterator();
			ChecklistType parentType = typesIterator.next();

			Checklist parentChecklist = createChecklist(parentType, dontGetHiddenColumns);
			
			result.setParentChecklist(parentChecklist);
			result.setRelations(createChecklistsRelations(parentChecklist, typesIterator, dontGetHiddenColumns));
		}
		
		return result;
	}
	
	@Transactional
	public ChecklistStatus findChecklistStatusByCode(String code) {
		return checklistStatusDAO.findByCode(code);
	}
	
	@Transactional
	public List<ChecklistControlConfig> findChecklistControlConfigByChecklistType(ChecklistType checklistType, boolean dontGetHiddenColumns) {
		return checklistControlConfigDAO.findChecklistControlConfigByType(checklistType, dontGetHiddenColumns);
	}
	
	@Transactional
	public List<ChecklistConfig> findChecklistElementsByChecklistType(ChecklistType checklistType, boolean dontGetHiddenColumns) {
		return checklistConfigDAO.findChecklistElementsByChecklistType(checklistType, dontGetHiddenColumns);
	}
	
	@Transactional
	public void saveChecklistRelation(ChecklistRelation relation) {
		relation = checklistRelationDAO.merge(relation);
		relation = checklistRelationDAO.saveOrUpdate(relation);
	}
}
