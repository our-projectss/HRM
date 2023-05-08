package hrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.sql.Date;

@Data
@Entity
public class RequestOff {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private String status; // reject, approved, pending

	private Long approvedBy;

	@NotNull
	private Date dayOff;

	@NotNull
	private String shift; // morning, afternoon, fullday

	private String reason; 
}