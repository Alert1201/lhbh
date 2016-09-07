package org.jeff.projs.ihbh.data.domains;

import java.sql.Date;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class PlayListDto implements BaseDto {

	int id;
	String name;
	String description;
	boolean repeatPlay;
	int speed;
	boolean defaultPlay;
	int volume;
	Date lastUsed;
	int userId;
	
	public PlayListDto(String name, String description, boolean repeatPlay, int speed, boolean defaultPlay, int volume,
			java.sql.Date lastUsed, int userId) {
		super();
		this.name = name;
		this.description = description;
		this.repeatPlay = repeatPlay;
		this.speed = speed;
		this.defaultPlay = defaultPlay;
		this.volume = volume;
		this.lastUsed = lastUsed;
		this.userId  = userId;
	}
	
	public PlayListDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRepeat() {
		return repeatPlay;
	}

	public void setRepeat(boolean repeatPlay) {
		this.repeatPlay = repeatPlay;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isDef() {
		return defaultPlay;
	}

	public void setDef(boolean defaultPlay) {
		this.defaultPlay = defaultPlay;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public java.sql.Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(java.sql.Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public PlayListDto(){}
	
	public PlayListDto(int id){
		this.id = id;
	}

	
	public boolean equals(PlayListDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof PlayListDto) {
			return internalEquals((PlayListDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(PlayListDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.name, other.name)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.description, other.description)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.repeatPlay, other.repeatPlay)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.speed, other.speed)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.volume, other.volume)) {
			return false;
		}
		return true;
	}	
}
