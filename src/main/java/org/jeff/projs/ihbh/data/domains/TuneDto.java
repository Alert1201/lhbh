package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class TuneDto implements BaseDto {

	private int id;
	private String name;
	private MeterDto meter;
	private String compositionYear;
	private AuthorDto author;
	private String nationality;
	
	public TuneDto(){}
	
	public TuneDto(String name, int meterId, String compositionYear, int authorId, String nationality){
		this.name = name;
		this.meter = new MeterDto(meterId);
		this.compositionYear = compositionYear;
		this.author = new AuthorDto(authorId);
		this.nationality = nationality;
	}
	
	public TuneDto(String name, MeterDto meter, String compositionYear, AuthorDto author, String nationality){
		this.name = name;
		this.meter = meter;
		this.compositionYear = compositionYear;
		this.author = author;
		this.nationality = nationality;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCompositionYear() {
		return compositionYear;
	}
	
	public AuthorDto getAuthor() {
		return author;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMeter(MeterDto meter) {
		this.meter = meter;
	}
	
	public MeterDto getMeter() {
		return meter;
	}
	
	public int getMeterId(){
		return this.getMeter().getId();
	}
	
	public int getAuthorId(){
		return this.getAuthor().getId();
	}
	public void setCompositionYear(String compositionYear) {
		this.compositionYear = compositionYear;
	}
	
	public void setAuthor(AuthorDto author) {
		this.author = author;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public boolean equals(TuneDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof TuneDto) {
			return internalEquals((TuneDto) other);
		}
		return super.equals(other);
	}

	public boolean deepEquals(TuneDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof TuneDto) {
			return deepInternalEquals((TuneDto) other);
		}
		return super.equals(other);
	}
	//May not need this.  Each detail is unique so only test for failure.
	protected boolean deepInternalEquals(TuneDto other) {
		// suppose fuelType can be Integer.
		if (!this.author.equals(other.author)){
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.compositionYear, other.compositionYear)) {
			return false;
		}
		if (!this.meter.equals(other.meter)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.name, other.name)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.nationality, other.nationality)) {
			return false;
		}
		return true;
	}
	
	//May not need this.  Each detail is unique so only test for failure.
		protected boolean internalEquals(TuneDto other) {
			// suppose fuelType can be Integer.
			if (!EqualsHelper
					.areObjectsEqual(this.author.getId(), other.author.getId())) {
				return false;
			}

			if (!EqualsHelper
					.areObjectsEqual(this.compositionYear, other.compositionYear)) {
				return false;
			}
			if (!EqualsHelper
					.areObjectsEqual(this.meter.getId(), other.meter.getId())) {
				return false;
			}			if (!EqualsHelper
					.areObjectsEqual(this.name, other.name)) {
				return false;
			}
			if (!EqualsHelper
					.areObjectsEqual(this.nationality, other.nationality)) {
				return false;
			}
			return true;
		}
}
