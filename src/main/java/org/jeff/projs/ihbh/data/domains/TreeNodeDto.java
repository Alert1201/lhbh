package org.jeff.projs.ihbh.data.domains;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeDto {
	String id;
	String label;
	boolean inode;
	boolean open;
	boolean checkbox;
	boolean radio;
	boolean checked;
	boolean selected;
	boolean disabled;
	String source;
	String order;
	String parentId;
	String level;
	
	List<TreeNodeDto> branch;
	
	public TreeNodeDto(){
		branch = new ArrayList<TreeNodeDto>();
	}
	
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public boolean isInode() {
		return inode;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setInode(boolean inode) {
		this.inode = inode;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * @return the checkbox
	 */
	public boolean isCheckbox() {
		return checkbox;
	}

	/**
	 * @param checkbox the checkbox to set
	 */
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	/**
	 * @return the radio
	 */
	public boolean isRadio() {
		return radio;
	}

	/**
	 * @param radio the radio to set
	 */
	public void setRadio(boolean radio) {
		this.radio = radio;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the _order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param _order the _order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the _parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param _parentId the _parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the _level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param _level the _level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	public List<TreeNodeDto> getBranch() {
		return branch;
	}

	public void setBranch(List<TreeNodeDto> branch) {
		this.branch = branch;
	}
	
	public void addBranch(TreeNodeDto node){
		branch.add(node);
	}
	
	
}
