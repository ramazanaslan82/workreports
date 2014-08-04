package model;

public class MeasureEquipment {

	private String toolName;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeasureEquipment [toolName=");
		builder.append(toolName);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}
