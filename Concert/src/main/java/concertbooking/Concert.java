package concertbooking;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Concert_table")
public class Concert {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ccId;
    private String ccName;
    private String ccDate;
    private Long stock;

    @PostPersist
    public void onPostPersist(){
        Registered registered = new Registered();
        BeanUtils.copyProperties(this, registered);
        registered.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        Canceled canceled = new Canceled();
        BeanUtils.copyProperties(this, canceled);
        canceled.publishAfterCommit();


    }


    public Long getCcId() {
        return ccId;
    }

    public void setCcId(Long ccId) {
        this.ccId = ccId;
    }
    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }
    public String getCcDate() {
        return ccDate;
    }

    public void setCcDate(String ccDate) {
        this.ccDate = ccDate;
    }
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }




}
