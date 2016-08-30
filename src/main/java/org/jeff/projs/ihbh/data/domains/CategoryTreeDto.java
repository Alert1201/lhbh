package org.jeff.projs.ihbh.data.domains;

public class CategoryTreeDto {

	String category;
	int id;
	int parId;
	int level;
	int order;
	
	public String getCategory() {
		return category;
	}
	public int getId() {
		return id;
	}
	public int getParId() {
		return parId;
	}
	public int getLevel() {
		return level;
	}
	public int getOrder() {
		return order;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setParId(int parId) {
		this.parId = parId;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
