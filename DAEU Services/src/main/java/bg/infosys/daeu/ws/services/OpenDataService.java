package bg.infosys.daeu.ws.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.daeu.db.dao.pub.OpenDataDAO;
import bg.infosys.daeu.db.entity.pub.OpenData;

@Service
@Transactional
public class OpenDataService {
    @Autowired private OpenDataDAO openDataDAO;

    public OpenData saveOpenData(OpenData openData) {
        return openDataDAO.saveOrUpdate(openData);
    }

    public OpenData findOpenDataByID(int id) {
        return openDataDAO.findById(id);
    }

    public OpenData findByURI(String uri) {
        return openDataDAO.findAll().stream().filter(e -> e.getOpenDataURI().equals(uri)).findFirst().orElse(null);
    }

    public List<OpenData> findAll() {
        return openDataDAO.findAll();
    }
}
