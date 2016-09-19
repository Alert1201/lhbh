package org.jeff.projs.ihbh.data.domains;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeDto {
	String id;
	String label;
	boolean inode;
	boolean open;
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
