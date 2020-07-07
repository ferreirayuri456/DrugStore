package br.com.example.farmacia.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@MappedSuperclass //são aplicadas às entidades que herdam dela
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "deleted")
	private boolean deleted = false;

	@Column(name = "DataChange_CreatedTime", nullable = false)
	protected LocalDateTime dataChangeCreatedTime;
	
	@Column(name = "DataChange_LastTime")
	protected LocalDateTime dataChangeLastModifiedTime;
	
	
	@PrePersist
	protected void prePresist() {
		if (this.dataChangeCreatedTime == null) dataChangeCreatedTime = LocalDateTime.now();
		if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.dataChangeLastModifiedTime = LocalDateTime.now();
	}
	
	@PreRemove
	protected void preRemove() {
		this.dataChangeLastModifiedTime = LocalDateTime.now();
	}
	
}


	