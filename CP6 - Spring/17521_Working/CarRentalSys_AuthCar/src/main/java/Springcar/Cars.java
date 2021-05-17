package Springcar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "car")
@DynamicUpdate
public class Cars {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARID")
	private Long carId;

	@Column(name = "CARTYPE")
	@Size(min = 1, max = 10)
	@NotNull
	private String type;

	@Column(name = "BRAND")
	@Size(min = 3, max = 15)
	@NotNull
	private String brand;

	@Column(name = "CARREGNO")
	@Size(min = 3, max = 8)
	@NotNull
	private String carRegNo;
	
	@Column(name="DAILYRATE")
	@Min(value=10)
	@Max(value=300)
	@NotNull
	private int dailyRate;

//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name = "CARSTTSID", nullable = false)
//	private CarStatus carStatus;

	public Cars() {
	}

	public Cars(Long carId, @Size(min = 1, max = 10) @NotNull String type,
			@Size(min = 3, max = 15) @NotNull String brand, @Size(min = 3, max = 8) @NotNull String carRegNo,
			@Min(10) @Max(300) @NotNull int dailyRate) {
		super();
		this.carId = carId;
		this.type = type;
		this.brand = brand;
		this.carRegNo = carRegNo;
		this.dailyRate = dailyRate;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarRegNo() {
		return carRegNo;
	}

	public void setCarRegNo(String carRegNo) {
		this.carRegNo = carRegNo;
	}

	public int getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(int dailyRate) {
		this.dailyRate = dailyRate;
	}

//	public CarStatus getCarStatus() {
//		return carStatus;
//	}
//
//	public void setCarStatus(CarStatus carStatus) {
//		this.carStatus = carStatus;
//	}

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", type=" + type + ", brand=" + brand + ", carRegNo=" + carRegNo
				+ ", dailyRate=" + dailyRate + "]";
	}

}
