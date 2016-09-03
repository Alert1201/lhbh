package org.jeff.projs.ihbh.data.database;

import org.jeff.projs.ihbh.data.daos.impl.MySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnalDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlTuneDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlUserTypeDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Clean {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-application-context.xml");
		MySqlAuthorDaoImpl authorsDaoImpl = ctx.getBean(MySqlAuthorDaoImpl.class);
		MySqlCategoryDaoImpl categoryDaoImpl = ctx.getBean(MySqlCategoryDaoImpl.class);
		MySqlHymnalDaoImpl hymnalDaoImpl = ctx.getBean(MySqlHymnalDaoImpl.class);
		MySqlMeterDaoImpl meterDaoImpl = ctx
				.getBean(MySqlMeterDaoImpl.class);
		MySqlTuneDaoImpl tunesDaoImpl = ctx.getBean(MySqlTuneDaoImpl.class);
		MySqlUserTypeDaoImpl userTypeDaoImpl = ctx
				.getBean(MySqlUserTypeDaoImpl.class);
		userTypeDaoImpl.deleteAll();
		tunesDaoImpl.deleteAll();
		meterDaoImpl.deleteAll();
		authorsDaoImpl.deleteAll();
		categoryDaoImpl.deleteAll();
		hymnalDaoImpl.deleteAll();

	}
}
