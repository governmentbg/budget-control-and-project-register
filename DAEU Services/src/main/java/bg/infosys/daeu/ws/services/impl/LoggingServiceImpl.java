package bg.infosys.daeu.ws.services.impl;

import bg.infosys.daeu.db.dao.pub.LoggingDAO;
import bg.infosys.daeu.db.entity.pub.Logging;
import bg.infosys.daeu.ws.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private LoggingDAO loggingDAO;

    @Override
    public void logTime(String method, LocalDate timeStamp, boolean success) {

        Logging logEntity = new Logging();
        logEntity.setMethod(method);
        logEntity.setTimeStamp(timeStamp);
        logEntity.setSuccess(success);

        loggingDAO.save(logEntity);
    }

    @Override
    public Logging getLog(int id) {
        return loggingDAO.findById(id);
    }

    @Override
    public List<Logging> findAll() {
        return loggingDAO.findAll();
    }
}
