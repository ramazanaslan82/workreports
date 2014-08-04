package model;

import java.util.Date;
import java.util.List;

public class Event {

	private String companyName;
	private String address;
	private List<Measurement> measurements;
	private String vehicle;
	private List<MeasureEquipment> equipments;
	private List<Employee> workers;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public List<MeasureEquipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<MeasureEquipment> equipments) {
		this.equipments = equipments;
	}

	public List<Employee> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Employee> workers) {
		this.workers = workers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [companyName=");
		builder.append(companyName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", measurements=");
		builder.append(measurements);
		builder.append(", vehicle=");
		builder.append(vehicle);
		builder.append(", equipments=");
		builder.append(equipments);
		builder.append(", workers=");
		builder.append(workers);
		builder.append("]");
		return builder.toString();
	}

}
