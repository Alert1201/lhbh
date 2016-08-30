package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class CategoryDto  implements BaseDto {

	private int id;
	private String category;
	private int parId;
	private int listOrder;
	private int level;
	
	public CategoryDto(){}
	
	public CategoryDto(String category){
		this.category = category;
	}

	public CategoryDto(String category, int parId){
		this.category = category;
		this.parId = parId;
	}
	
	public CategoryDto(String category, int parId, int listOrder){
		this.category = category;
		this.parId = parId;
		this.listOrder = listOrder;
	}
	
	public CategoryDto(String category, int parId, int listOrder, int level){
		this.category = category;
		this.parId = parId;
		this.listOrder = listOrder;
		this.level = level;
	}
	
	public int getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public int getParId() {
		return parId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setParId(int parId) {
		this.parId = parId;
	}
	
	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean equals(CategoryDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof CategoryDto) {
			return internalEquals((CategoryDto) other);
		}
		return super.equals(other);
	}

	protected boolean internalEquals(CategoryDto other) {
		if (!EqualsHelper
				.areObjectsEqual(this.category, other.category)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.listOrder, other.listOrder)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.category, other.category)) {
			return false;
		}
		if (!EqualsHelper
				.areObjectsEqual(this.parId, other.parId)) {
			return false;
		}
		
		if (!EqualsHelper
				.areObjectsEqual(this.level, other.level)) {
			return false;
		}
		return true;
	}
}
