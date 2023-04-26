package hrm.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class User {
	@Id
  	private final String id;
  	private final String username;
  	private final String password;
  	
  	public User(String id, String username, String password) {
  		this.id = id;
  		this.username = username;
  		this.password = password;
  	}
}