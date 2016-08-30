package org.jeff.projs.ihbh.data.daos;

import java.util.Map;

public interface LookupDAO {
	public Map<Integer, String> getLookup(String sql);
}
