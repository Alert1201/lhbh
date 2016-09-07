package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class UserDto implements BaseDto {
	String email;
	String firstName;
	String lastName;
	String hashedPassword;
	int typeId;
	String username;
	int id;
	byte[] salt;

	public UserDto() {
	}

	public UserDto(String email, String firstName, String lastName,
			String hashedPassword, int typeId, String userName, byte[] salt) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hashedPassword = hashedPassword;
		this.typeId = typeId;
		this.username = userName;
		this.salt = salt;
	}

	public UserDto(String firstName, String lastName, String userName, String email, int typeId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.hashedPassword="";
		this.typeId = typeId;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public boolean equals(UserDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof UserDto) {
			return internalEquals((UserDto) other);
		}
		return super.equals(other);
	}

	// May not need this. Each detail is unique so only test for failure.
	protected boolean internalEquals(UserDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper.areObjectsEqual(this.email, other.email)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.firstName, other.firstName)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.hashedPassword,
				other.hashedPassword)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.lastName, other.lastName)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.typeId, other.typeId)) {
			return false;
		}
		if (!EqualsHelper.areObjectsEqual(this.username, other.username)) {
			return false;
		}
		if (!EqualsHelper.areArraysEquals(this.salt, other.salt)) {
			return false;
		}
		return true;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
}
