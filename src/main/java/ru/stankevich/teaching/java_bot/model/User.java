package ru.stankevich.teaching.java_bot.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "users")
@Entity(name = "users")
@Data
@Accessors(chain = true)
public class User implements UserDetails {

	@Id
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	private Long id;

	private String username;

	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(nullable = false)
	private boolean expired = false;

	@Column(nullable = false)
	private boolean locked = false;

	@Column(nullable = false)
	private boolean enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(role);
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public Role getRole() {
		return role;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}
}