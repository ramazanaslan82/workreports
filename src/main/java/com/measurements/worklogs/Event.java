package com.measurements.worklogs;

import java.util.List;

import com.measurements.employees.Employee;
import com.measurements.measurements.MeasureEquipment;
import com.measurements.measurements.Measurement;

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

}
