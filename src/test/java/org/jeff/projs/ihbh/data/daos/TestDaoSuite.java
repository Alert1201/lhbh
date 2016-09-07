package org.jeff.projs.ihbh.data.daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.jeff.projs.ihbh.data.daos.TestMySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlHymnDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlHymnalDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlTunesDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlUserTypesDaoImpl;
import org.jeff.projs.ihbh.data.daos.TestMySqlUsersDaoImpl;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestMySqlMeterDaoImpl.class,
	TestMySqlHymnalDaoImpl.class,
	TestMySqlAuthorDaoImpl.class,
	TestMySqlCategoryDaoImpl.class,
	TestMySqlTunesDaoImpl.class,
	TestMySqlUserTypesDaoImpl.class,
	TestMySqlUsersDaoImpl.class,
	TestMySqlHymnDaoImpl.class,
	TestMySqlPlayListDaoImpl.class
})
public class TestDaoSuite {   
} 
