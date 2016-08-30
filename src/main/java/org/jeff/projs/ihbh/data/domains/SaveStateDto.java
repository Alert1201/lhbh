package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class SaveStateDto implements BaseDto {

	private int id;
	private int userId;
	private int currentHymnId;
	private int hymnVolume;
	private int hymnTempo;
	
	public SaveStateDto(){
		
	}
	
	public SaveStateDto(int userId, int currentHymnId, int hymnVolume, int hymnTempo){
		this.userId = userId;
		this.currentHymnId = currentHymnId;
		this.hymnVolume = hymnVolume;
		this.hymnTempo = hymnTempo;
	}
	
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public int getCurrentHymnId() {
		return currentHymnId;
	}
	public int getHymnVolume() {
		return hymnVolume;
	}
	public int getHymnTempo() {
		return hymnTempo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setCurrentHymnId(int currentHymnId) {
		this.currentHymnId = currentHymnId;
	}
	public void setHymnVolume(int hymnVolume) {
		this.hymnVolume = hymnVolume;
	}
	public void setHymnTempo(int hymnTempo) {
		this.hymnTempo = hymnTempo;
	}
	public boolean equals(SaveStateDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof SaveStateDto) {
			return internalEquals((SaveStateDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(SaveStateDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.currentHymnId, other.currentHymnId)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.hymnTempo, other.hymnTempo)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.hymnVolume, other.hymnVolume)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.userId, other.userId)) {
			return false;
		}		return true;
	}
	
}
