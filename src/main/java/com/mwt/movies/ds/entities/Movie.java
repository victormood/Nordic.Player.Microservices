package com.mwt.movies.ds.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent account entity with JPA markup. Accounts are stored in an H2
 * relational database.
 * 
 * @author Victor Manea
 */
@Entity
@Table(name = "T_ACCOUNT")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	protected Long id;

	protected String number;

	@Column(name = "username")
	protected String username;

	@Column(name = "password")
	protected String password;

	protected BigDecimal balance;

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
	protected Movie() {
		balance = BigDecimal.ZERO;
	}

	public Movie(String number, String owner) {
		id = getNextId();
		this.number = number;
		this.username = owner;
		balance = BigDecimal.ZERO;
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

	public String getNumber() {
		return number;
	}

	protected void setNumber(String accountNumber) {
		this.number = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	protected void setUsername(String owner) {
		this.username = owner;
	}

	public BigDecimal getBalance() {
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void withdraw(BigDecimal amount) {
		balance.subtract(amount);
	}

	public void deposit(BigDecimal amount) {
		balance.add(amount);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return number + " [" + username + "]: $" + balance;
	}

}
