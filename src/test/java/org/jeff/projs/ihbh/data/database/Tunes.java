package org.jeff.projs.ihbh.data.database;

import org.jeff.projs.ihbh.data.daos.impl.MySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlTuneDaoImpl;
import org.jeff.projs.ihbh.data.domains.TuneDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tunes {

	//get Author ids
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-application-context.xml");
		MySqlAuthorDaoImpl authorsDaoImpl = ctx.getBean(MySqlAuthorDaoImpl.class);
		MySqlMeterDaoImpl metersDaoImpl = ctx.getBean(MySqlMeterDaoImpl.class);
		MySqlTuneDaoImpl tunesDaoImpl = ctx.getBean(MySqlTuneDaoImpl.class);
		Meters.main(null);
		Authors.main(null);
		tunesDaoImpl.deleteAll();
		int authJB = authorsDaoImpl.getAuthorByFullName("Jeff", "Sulman").getId();
		int authLS = authorsDaoImpl.getAuthorByFullName("Linda", "Spindle").getId();
		int authLG = authorsDaoImpl.getAuthorByFullName("Marven", "Gay").getId();
		int meter8888 = metersDaoImpl.getMeterByMeter("8.8.8.8").getId();
		int meter9999 = metersDaoImpl.getMeterByMeter("9.9.9.9").getId();
		int meter10s = metersDaoImpl.getMeterByMeter("10.10.10.10").getId();
		tunesDaoImpl.add(new TuneDto("Tune1", meter8888, "1878", authJB, "German"));
		tunesDaoImpl.add(new TuneDto("Tune2", meter9999, "1900", authLS, "English"));
		tunesDaoImpl.add(new TuneDto("Tune3", meter10s, "1700", authLG, "American"));
		tunesDaoImpl.add(new TuneDto("Tune4", meter8888, "1600", authJB, "Chinese"));
	}
}
