package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class HymnalDto implements BaseDto {

	private int id;
	private String name;
	private String abbreviation;

	public HymnalDto() {

	}

	public HymnalDto(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public boolean equals(HymnalDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof HymnalDto) {
			return internalEquals((HymnalDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(HymnalDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.abbreviation, other.abbreviation)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.name, other.name)) {
			return false;
		}
		return true;
	}
}
