package org.jeff.projs.ihbh.data.domains;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class MeterDto implements BaseDto {

	String meter;
	int id;
	String description;
	
	public MeterDto(){}
	
	public MeterDto(int id){
		this.id = id;
	}
	
	public MeterDto(String meter){
		this.meter = meter;
	}
	
	public MeterDto(ResultSet rs) throws SQLException{
		this.id = rs.getInt("meter_id");
		this.meter = rs.getString("meter");
		this.description = rs.getString("description");
	}
	
	public MeterDto(String meter, String description){
		this.meter = meter;
		this.description = description;
	}
	
	public String getMeter() {
		return meter;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMeter(String meter) {
		this.meter = meter;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean equals(MeterDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof MeterDto) {
			return internalEquals((MeterDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(MeterDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.meter, other.meter)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.description, other.description)) {
			return false;
		}
		return true;
	}	
}
