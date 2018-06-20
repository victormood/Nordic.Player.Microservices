package com.mwt.audios.ds.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent Audio entity with JPA markup.
 * 
 * @author v.manea
 */
@Entity
@Table(name = "AUDIO")
public class Audio implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	protected Long id;

	@Column(name = "title")
	protected String title;

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
	protected Audio() {
	}

	public Audio(String title) {
		id = getNextId();
		this.title = title;
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

	public String getTite() {
		return this.title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

}
