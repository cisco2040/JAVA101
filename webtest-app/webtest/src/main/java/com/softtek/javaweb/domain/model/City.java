package com.softtek.javaweb.domain.model;

public class City {
	private Long cityId;
	private String description;
	private State state;
	public City(Long cityId, String description, State state) {
		this.cityId = cityId;
		this.description = description;
		this.state = state;
	}
	public City() {}
	
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
		return "City [cityId=" + cityId + ", description=" + description + ", state=" + state.toString() + "]";
	}
	
	
}
