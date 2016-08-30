package org.jeff.projs.ihbh.data.domains;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class CategoryDetailDto implements BaseDto {
	
	private int categoryId;
	private int hymnId;
	
	public int getCategoryId() {
		return categoryId;
	}
	public int getHymnId() {
		return hymnId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setHymnId(int hymnId) {
		this.hymnId = hymnId;
	}
	
	public boolean equals(CategoryDetailDto other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other instanceof CategoryDetailDto) {
			return internalEquals((CategoryDetailDto) other);
		}
		return super.equals(other);
	}

	//May not need this.  Each detail is unique so only test for failure.
	protected boolean internalEquals(CategoryDetailDto other) {
		// suppose fuelType can be Integer.
		if (!EqualsHelper
				.areObjectsEqual(this.hymnId, other.hymnId)) {
			return false;
		}
		return true;
	}
	
}
