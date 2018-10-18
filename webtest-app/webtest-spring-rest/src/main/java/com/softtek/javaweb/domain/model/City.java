package com.softtek.javaweb.domain.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

import org.hibernate.validator.constraints.Length;

@XmlRootElement
@Entity
@Table (name = "city")
public class City implements Serializable{

	private static final long serialVersionUID = 5985769049342837742L;

	@Id @Column (name = "city_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long cityId;

	@NotNull (message = "<description> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=100, message = "<description> {org.hibernate.validator.constraints.Length.message}")
	@Column (name = "description")
	private String description;
	
	@NotNull (message = "<state> {javax.validation.constraints.NotNull.message}")
	@ManyToOne
	@JoinColumn (name = "state_id")
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
