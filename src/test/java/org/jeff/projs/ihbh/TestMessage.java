package org.jeff.projs.ihbh;

import static org.junit.Assert.*;

import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.Test;

public class TestMessage {
	
	String juStringNoParam = "stringNoParam";
	String dbStringNoParam = "Database StringNoParam";
	
	@Test
	public void testJUMessageNoParam(){
		System.out.println(MessageUtils.getMessage("ju","key1"));
		assertEquals(MessageUtils.getMessage("ju", "key1"), juStringNoParam);
	}

	@Test
	public void testJUMessageOneParam(){
		String param1 = "Param1";
		System.out.println(MessageUtils.getMessage("ju", "key2", new Object[] {param1}));
		assertEquals(MessageUtils.getMessage("ju", "key2", new Object[] {param1}), "stringOneParam param = Param1");
	}
	
	@Test
	public void testDBMessageNoParam(){
		System.out.println(MessageUtils.getMessage("db","key1"));
		assertEquals(MessageUtils.getMessage("db", "key1"), dbStringNoParam);
	}

	@Test
	public void testDBMessageOneParam(){
		String param1 = "Param1";
		System.out.println(MessageUtils.getMessage("db", "key2", new Object[] {param1}));
		assertEquals(MessageUtils.getMessage("db", "key2", new Object[] {param1}), "Database StringOneParam param = Param1");
	}
}
