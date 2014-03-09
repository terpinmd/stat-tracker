package com.hammond.stattracker.domain;

import java.io.Serializable;

public abstract class AbstractDomainClass implements Serializable {

	private static final long serialVersionUID = -1639467997160510068L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
