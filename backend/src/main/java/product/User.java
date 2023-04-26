package product;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 private final String username;
	 private final String password;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return  Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	 @Override
	 public boolean isAccountNonExpired() {
	 return true;
	 }
	 @Override
	 public boolean isAccountNonLocked() {
	 return true;
	 }
	 @Override
	 public boolean isCredentialsNonExpired() {
	 return true;
	 }
	 @Override
	 public boolean isEnabled() {
	 return true;
	 }
}