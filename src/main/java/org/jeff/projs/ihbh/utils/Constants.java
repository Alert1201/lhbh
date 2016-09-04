package org.jeff.projs.ihbh.utils;

import org.springframework.dao.DataIntegrityViolationException;

public interface Constants {
	final public String ALGORITHM = "SHA1PRNG";
	final public int MESSAGE_TYPE_INFO = 0;
	final public int MESSAGE_TYPE_WARNING = 1;
	final public int MESSAGE_TYPE_ERROR= 2;
	
	final public int DB_DATAINTEGRITYVIOLATIONEXCEPTION_RETVALUE = -1;

}
