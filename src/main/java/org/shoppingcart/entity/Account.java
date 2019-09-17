package org.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 741815838741238857L;

	public static final String ROLE_MANAGER = "MANAGER";
	public static final String ROLE_EMPLOYEE = "EMPLOYEE";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "accountId", length = 20, nullable = false)
	private String accountId;

	@Column(name = "username", length = 20, unique = true, nullable = false)
	private String username;

	@Column(name = "password", length = 20, nullable = false)
	private String password;

	@Column(name = "active", length = 1, nullable = false)
	private boolean active;

	@Column(name = "userrole", length = 20, nullable = false)
	private String userRole;

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the userName
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUsername(String userName) {
		this.username = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "[" + this.username + "," + this.password + "," + this.userRole + "]";
	}
}