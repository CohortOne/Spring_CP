package Springcar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_status")
public class CarStatus {

	@Id
	@Column(name = "CARSTTSID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carSttsId;

	@Column(length = 15, nullable = false, unique = true)
	private String carSttsName;

	public Integer getVehSttsId() {
		return carSttsId;
	}

	public void setVehSttsId(Integer vehSttsId) {
		this.carSttsId = vehSttsId;
	}

	public String getName() {
		return carSttsName;
	}

	public void setName(String name) {
		this.carSttsName = name;
	}

	/**
	 * 
	 */
	public CarStatus() {

	}

	@Override
	public String toString() {
		return carSttsName;
	}

}
