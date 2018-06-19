package com.mwt.login.ds.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent user entity with JPA markup. Accounts are stored in an H2
 * relational database.
 * 
 * @author v.manea
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	protected Long id;

	@Column(name = "username")
	protected String username;

	@Column(name = "password")
	protected String password;

	@Column(name = "role")
	protected String role;

	/**
	 * This is a very simple, and non-scalable solution to generating unique ids.
	 * Not recommended for a real application. Consider using the
	 * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
	 * 
	 * @return The next available id.
	 */
	protected static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}

	/**
	 * Default constructor for JPA only.
	 */
	protected User() {
	}

	public User(String username) {
		id = getNextId();
		this.username = username;
	}

	public long getId() {
		return id;
	}

	/**
	 * Set JPA id - for testing and JPA only. Not intended for normal use.
	 * 
	 * @param id
	 *            The new id.
	 */
	protected void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	protected void setUsername(String owner) {
		this.username = owner;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return id + ": " + username;
	}

}
