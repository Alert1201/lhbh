package org.jeff.projs.ihbh.data.database;

import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnalDaoImpl;
import org.jeff.projs.ihbh.data.domains.HymnalDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hymnals {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-application-context.xml");
		MySqlHymnalDaoImpl hymnalDaoImpl = ctx.getBean(MySqlHymnalDaoImpl.class);
		hymnalDaoImpl.deleteAll();
		hymnalDaoImpl.add(new HymnalDto("Trinity Baptist Blue","TRB"));
		hymnalDaoImpl.add(new HymnalDto("Psaltar","PSL"));
		hymnalDaoImpl.add(new HymnalDto("Trinity Baptist Red","TRR"));
		hymnalDaoImpl.add(new HymnalDto("Hymns of God","HOG"));
	}
}
