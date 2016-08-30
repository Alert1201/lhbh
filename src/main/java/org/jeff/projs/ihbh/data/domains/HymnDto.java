package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class HymnDto implements BaseDto {

	private int id;
	private int tuneId;
	private String title;
	private String firstLine;
	private int authorId;
	private String composedYear;
	private int number;
	private int hymnalId;
	
	public HymnDto(){
		
	}
	
public HymnDto(int hymnalId, int tuneId, String title, String firstLine, int authorId, String composedYr, int number){
		this.hymnalId = hymnalId;
		this.tuneId = tuneId;
		this.title = title;
		this.firstLine = firstLine;
		this.authorId = authorId;
		this.composedYear = composedYr;
		this.number = number;
	}
	
	public int getId() {
		return id;
	}
	public int getTuneId() {
		return tuneId;
	}
	public String getTitle() {
		return title;
	}
	public String getFirstLine() {
		return firstLine;
	}
	public int getAuthorId() {
		return authorId;
	}
	public String getComposedYear() {
		return composedYear;
	}
	public int getNumber() {
		return number;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTuneId(int tuneId) {
		this.tuneId = tuneId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public void setComposedYear(String composedYear) {
		this.composedYear = composedYear;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getHymnalId() {
		return hymnalId;
	}
	public void setHymnalId(int hymnalId) {
		this.hymnalId = hymnalId;
	}
	
	public boolean equals(HymnDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof HymnDto) {
			return internalEquals((HymnDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(HymnDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.authorId, other.authorId)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.composedYear, other.composedYear)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.firstLine, other.firstLine)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.hymnalId, other.hymnalId)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.number, other.number)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.title, other.title)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.tuneId, other.tuneId)) {
			return false;
		}
		return true;
	}
	
}
