package entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@NotNull
	private String user_id;

	@Column
	@NotNull
	private String user_type;

	@Column
	@NotNull
	private String user_name;


	@Column
	@NotNull
	private String email;


	@Column
	@NotNull
	private String password;

	@Column
	@NotNull
	@CreationTimestamp
	private LocalDate create_time = LocalDate.now();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull
	private List<Request> requests;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull
	private List<Residence> residences;

	public User(){}

	public User(String id, String userType, String email, String password){
		this.user_id = id;
		this.user_type = userType;
		this.email = email;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreate_time() {
		return create_time;
	}

	public void setCreate_time(LocalDate create_time) {
		this.create_time = create_time;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<Residence> getResidences() {
		return residences;
	}

	public void setResidences(List<Residence> residences) {
		this.residences = residences;
	}
}
