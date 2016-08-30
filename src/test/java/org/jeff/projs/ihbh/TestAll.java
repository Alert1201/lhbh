package org.jeff.projs.ihbh;



import org.jeff.projs.ihbh.data.daos.TestDaoSuite;
import org.jeff.projs.ihbh.services.TestLookupService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestLookupService.class,
	TestDaoSuite.class
})
public class TestAll {   
} 

