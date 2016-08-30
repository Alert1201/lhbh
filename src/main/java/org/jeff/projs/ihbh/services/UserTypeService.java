/**
 * 
 */
package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserTypeDto;

/**
 * @author Jeff Sulman
 *
 */
public interface UserTypeService  {
	public int save(UserTypeDto dto);
	public int delete(int id);
	public UserTypeDto getUserTypeById(int id);
	public UserTypeDto getUserTypeByDescription(UserTypeDto dto);
	public UserTypeDto getUserTypeByUserType(UserTypeDto dto);
	public List<UserTypeDto> getAll();
	public int getCount();
}
