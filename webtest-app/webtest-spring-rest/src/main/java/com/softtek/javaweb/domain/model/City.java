package com.softtek.javaweb.domain.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

import org.hibernate.validator.constraints.Length;

@XmlRootElement
public class City {
	private Long cityId;
	@NotNull (message = "<description> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=100, message = "<description> {org.hibernate.validator.constraints.Length.message}")
	private String description;
	@NotNull (message = "<state> {javax.validation.constraints.NotNull.message}")
	private State state;
	
	public City(Long cityId, String description, State state) {
		this.cityId = cityId;
		this.description = description;
		this.state = state;
	}
	public City() {}
	
	@NotNull (message = "<username> {javax.validation.constraints.NotNull.message}")
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", description=" + description + ", state=" + (state != null ? state.toString() : null) + "]";
	}
	
	
}
