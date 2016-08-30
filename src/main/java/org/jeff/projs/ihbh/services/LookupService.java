package org.jeff.projs.ihbh.services;

import java.util.Map;

public interface LookupService {
	Map<Integer, String> populateTunes();
	Map<Integer, String> populateCategories(int parId);
	Map<Integer, String> populateHymnalsByName();
	Map<Integer, String> populateHymnalsByAbbrev();
	Map<Integer, String> populateMeters();
	Map<Integer, String> populateAuthors();
	Map<Integer, String> populateUserTypes();
	Map<Integer, String> populateUsersByFullName();
	Map<Integer, String> populateUsersByUserName();
}
