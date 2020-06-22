package br.com.example.farmacia.model;

import java.io.Serializable;
import java.util.Date;

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
	private Date dataChangeCreatedTime;
	
	@Column(name = "DataChange_LastTime")
	private Date dataChangeLastModifiedTime;
	
	
	@PrePersist
	protected void prePresist() {
		if (this.dataChangeCreatedTime == null) dataChangeCreatedTime = new Date();
		if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = new Date();
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.dataChangeLastModifiedTime = new Date();
	}
	
	@PreRemove
	protected void preRemove() {
		this.dataChangeLastModifiedTime = new Date();
	}
	
}


	