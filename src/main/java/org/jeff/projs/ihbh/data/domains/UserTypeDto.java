package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class UserTypeDto implements BaseDto {

	private int id;
	private String userType;
	private String description;

	public UserTypeDto() {

	}

	public UserTypeDto(String description, String userType) {
		this.description = description;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public String getUserType() {
		return userType;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(UserTypeDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof UserTypeDto) {
			return internalEquals((UserTypeDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(UserTypeDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper.areObjectsEqual(this.description, other.description)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.userType, other.userType)) {
			return false;
		}
		return true;
	}
}
