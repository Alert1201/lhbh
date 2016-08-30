package org.jeff.projs.ihbh.services.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jeff.projs.ihbh.data.daos.impl.MySqlLookupDAOImpl;
import org.jeff.projs.ihbh.services.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service("lookupService")
public class LookupServiceImpl implements LookupService {

	@Autowired
	MySqlLookupDAOImpl lookupDAOImpl;

	@ModelAttribute("authors")
	public Map<Integer, String> populateAuthors() {
		return lookupDAOImpl
				.getLookup("select id, CONCAT(last_name, ', ',  first_name) as name from authors order by last_name, first_name");
	}

	@ModelAttribute("tunes")
	public Map<Integer, String> populateTunes() {
		return lookupDAOImpl
				.getLookup("select tunes.id, CONCAT(tunes.name , ' (', meter.meter,')') name from tunes JOIN meter on tunes.meter_id = meter.id order by name");
	}

	@ModelAttribute("categories")
	public Map<Integer, String> populateCategories(int parId) {
		return lookupDAOImpl.getLookup("select id, category as name from category where par_id = " +parId + " order by category");
	}

	@ModelAttribute("hymnalsByName")
	public Map<Integer, String> populateHymnalsByName() {
		return lookupDAOImpl.getLookup("select id, name from hymnals order by name");
	}

	@ModelAttribute("hymnalsByAbbrev")
	public Map<Integer, String> populateHymnalsByAbbrev() {
		return lookupDAOImpl.getLookup("select id, abbreviation name from hymnals order by name");
	}

	@ModelAttribute("meters")
	public Map<Integer, String> populateMeters() {
		return lookupDAOImpl.getLookup("select id, meter name from meter order by meter");
	}

	@ModelAttribute("userTypes")
	public Map<Integer, String> populateUserTypes() {
		return lookupDAOImpl.getLookup("select id, description name from user_types order by description");
	}

	@ModelAttribute("usersByFN")
	public Map<Integer, String> populateUsersByFullName() {
		return lookupDAOImpl.getLookup("select id, CONCAT(last_name, ', ',  first_name) as name from users order by last_name, first_name");
	}
	@ModelAttribute("usersByUserName")
	public Map<Integer, String> populateUsersByUserName() {
		return lookupDAOImpl.getLookup("select id, username as name from users order by username");
	}

	@ModelAttribute("dateMonth")
	public Map<String, String> getMonths() {
		LinkedHashMap<String, String> retValue = new LinkedHashMap<String, String>();
			retValue.put("01","January");
			retValue.put("02","February");
			retValue.put("03","March");
			retValue.put("04","April");
			retValue.put("05","May");
			retValue.put("06","June");
			retValue.put("07","July");
			retValue.put("08","August");
			retValue.put("09","September");
			retValue.put("10","October");
			retValue.put("11","November");
			retValue.put("12","December");
			return retValue;
		}
}
