package bg.infosys.daeu.db.entity.pub;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity(name = "logging")
public class Logging {

    @Id
    @SequenceGenerator(name = "logging_seq", sequenceName = "logging_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logging_seq")

    private Integer id;

    @Column(name = "method")
    @NotNull
    private String method;

    @Column(name = "success")
    @NotNull
    private Boolean success;

    @Column(name = "timeStamp")
    @NotNull
    private LocalDate timeStamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

}
