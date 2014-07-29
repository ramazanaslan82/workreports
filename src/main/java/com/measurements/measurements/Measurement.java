package com.measurements.measurements;

public class Measurement {

	private String name;
	private int points;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int count) {
		this.points = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Measurement [name=");
		builder.append(name);
		builder.append(", points=");
		builder.append(points);
		builder.append("]");
		return builder.toString();
	}

}
