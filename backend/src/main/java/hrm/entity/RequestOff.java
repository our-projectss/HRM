package hrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class RequestOff {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	private long userId;
	private String status;
	private long approvedBy;
	private Date dayOff;
	private String shift;
}