package org.jeff.projs.ihbh;



import org.jeff.projs.ihbh.data.daos.TestDaoSuite;
import org.jeff.projs.ihbh.services.TestLookupService;
import org.jeff.projs.ihbh.utils.TestTreeViewUtils;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestLookupService.class,
	TestDaoSuite.class,
	TestTreeViewUtils.class
})
public class TestAll {   
} 

