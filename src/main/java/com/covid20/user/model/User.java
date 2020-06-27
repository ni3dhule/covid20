package com.covid20.user.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/*
@NamedQueries(  
	    {  
	        @NamedQuery(  
	        name = "findEmployeeByName",  
	        query = "from Employee e where e.name = :name"  
	        ),
			@NamedQuery(  
	        name = "findEmployeeByName",  
	        query = "from Employee e where e.name = :name"  
	        )	
	    }  
	)  
*/

@NamedQueries(
	{  
        @NamedQuery(  
	        name = "findUserStatusById",  
	        query = " SELECT u.active FROM User u WHERE u.id = :id "  
		),
        @NamedQuery(  
	        name = "activateOrDeactivateUser",  
	        query = " UPDATE User u SET u.active = :actValue WHERE u.id = :userId "  
		),
        @NamedQuery(  
	        name = "deactivateUser",  
	        query = " UPDATE User u SET u.active = :actValue WHERE u.id = :userId "  
		)/*,
        @NamedQuery(  
	        name = "findUserStatusById",  
	        query = " SELECT u.active FROM User u WHERE u.id = :id "  
		)/*
			 * ,
			 * 
			 * @NamedQuery( name = "findEmployeeByName", query =
			 * "from Employee e where e.name = :name" )
			 */	
    }  
		
)


@Entity
@Table(name = "cvd_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class User {
	
	private static final Logger log = LoggerFactory.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String passwordSalt;
	private String passwordHash;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@Temporal(TemporalType.TIMESTAMP)	
	private Timestamp dateOfBirth;
	private String gender;
	private int loginAttempts;
	private boolean active;
	private int createdBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdOn;
	
	public User() {
		super();
		this.active = true;
		this.loginAttempts = 3;
		log.warn("user object is being constructed !!! ");
	}
	
	public User(String firstName, String lastName, String email, String username, String password, String passwordSalt,
			String passwordHash, Timestamp dateOfBirth, String gender, int loginAttempts, boolean active, int createdBy,
			Timestamp createdOn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.loginAttempts = loginAttempts;
		this.active = active;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{id : " + id + ", firstName : " + firstName + ", lastName : " + lastName + ", email : " + email
				+ ", username : " + username + ", password : " + password + ", passwordSalt : " + passwordSalt
				+ ", passwordHash : " + passwordHash + ", dateOfBirth : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateOfBirth) + ", gender : " + gender
				+ ", loginAttempts : " + loginAttempts + ", active : " + active + ", createdBy : " + createdBy
				+ ", createdOn : " + createdOn + "}";
	}
	
}
