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
	private Long id;

	private Long user_id;

	private String status;

	private Long approved_by;

	private Date day_off;

	private String shift;
}