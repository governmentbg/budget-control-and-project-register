package bg.infosys.daeu.ws.services;


import bg.infosys.daeu.db.entity.pub.Logging;

import java.time.LocalDate;
import java.util.List;

public interface LoggingService {

    void logTime(String method, LocalDate timeStamp, boolean success);

    Logging getLog(int id);

    List<Logging> findAll();

}
