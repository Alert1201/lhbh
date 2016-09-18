package org.jeff.projs.ihbh.data.domains;

import java.util.Date;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class PlayListDetailDto implements BaseDto {

	int id;
	int playListId;
	int hymnId;
	int part;
	int type;
		
	public PlayListDetailDto(int playListId, int hymnId, int part, int type) {
		super();
		this.playListId = playListId;
		this.hymnId = hymnId;
		this.part = part;
		this.type = type;
	}

	public PlayListDetailDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayListId() {
		return playListId;
	}

	public void setPlayListId(int playListId) {
		this.playListId = playListId;
	}

	public int getHymnId() {
		return hymnId;
	}

	public void setHymnId(int hymnId) {
		this.hymnId = hymnId;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean equals(PlayListDetailDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof PlayListDetailDto) {
			return internalEquals((PlayListDetailDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(PlayListDetailDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.hymnId, other.hymnId)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.part, other.part)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.playListId, other.playListId)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.type, other.type)) {
			return false;
		}
		return true;
	}	
}
