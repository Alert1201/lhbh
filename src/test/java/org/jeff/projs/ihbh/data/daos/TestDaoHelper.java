package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnalDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlSaveStateDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlTuneDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlUserDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlUserTypeDaoImpl;
import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.data.domains.HymnDto;
import org.jeff.projs.ihbh.data.domains.HymnalDto;
import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.data.domains.SaveStateDto;
import org.jeff.projs.ihbh.data.domains.TuneDto;
import org.jeff.projs.ihbh.data.domains.UserDto;
import org.jeff.projs.ihbh.data.domains.UserTypeDto;
import org.jeff.projs.ihbh.services.impl.LookupServiceImpl;
import org.jeff.projs.ihbh.utils.Constants;
import org.jeff.projs.ihbh.utils.SecurityHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDaoHelper {
	public static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"test-application-context.xml");

	public static MySqlCategoryDaoImpl categoryDaoImpl = ctx
			.getBean(MySqlCategoryDaoImpl.class);
	
	public static MySqlMeterDaoImpl meterDaoImpl = ctx
			.getBean(MySqlMeterDaoImpl.class);
	
	public static MySqlUserTypeDaoImpl userTypeDaoImpl = ctx
			.getBean(MySqlUserTypeDaoImpl.class);

	public static MySqlUserDaoImpl userDaoImpl = ctx.getBean(MySqlUserDaoImpl.class);

	public static MySqlSaveStateDaoImpl stDaoImpl = ctx
			.getBean(MySqlSaveStateDaoImpl.class);

	public static MySqlAuthorDaoImpl authorDaoImpl = ctx
			.getBean(MySqlAuthorDaoImpl.class);

	public static MySqlHymnalDaoImpl hymnalDaoImpl = ctx
			.getBean(MySqlHymnalDaoImpl.class);

	public static MySqlTuneDaoImpl tuneDaoImpl = ctx.getBean(MySqlTuneDaoImpl.class);
	public static MySqlHymnDaoImpl hymnDaoImpl = ctx.getBean(MySqlHymnDaoImpl.class);
	public static LookupServiceImpl lookupServiceImpl  = ctx.getBean(LookupServiceImpl.class);

	/***************************************/
	// Setup Dtos
	/***************************************/

	//Hymn DTO setup
	static HymnDto hymnHowGrDto = new HymnDto(0, 0,"How Great Thou Art","When peace like a river", 0, "1980", 345);
	static HymnDto hymnAndCanDto = new HymnDto(0, 0,"And Can it Be","And can it be that I should gain.", 0, "1980", 333);
	static HymnDto hymnUpdateDto = new HymnDto(0, 0,"XXX xx CCCC","FirsLine la lala", 0, "3333", 111);
	static int hymnHowGrId;
	static int hymnAndCanId;
	
	// Tune DTO setup
	static TuneDto tuneStuttgartDto;
	static TuneDto tuneLastUnsDto;
	static TuneDto tuneUpdateDto;
	static int tuneStuttgartId;
	static int tuneLastUnsId;

	// Hymnal DTO Setup
	static HymnalDto hymnalTrinityDto = new HymnalDto("Trinity", "TRI");
	static HymnalDto hymnalFamilyDto = new HymnalDto("Family", "FAM");
	static HymnalDto hymnalUpateDto = new HymnalDto("Update", "XXX");
	static int hymnalTrinityId;
	static int hymnalFamilyId;

	// Meter DTO Setup
	static MeterDto meter8Dto = new MeterDto("8.8.8.8", "Long");
	static MeterDto meter4Dto = new MeterDto("4.4.4.4",  "Common");
	static MeterDto updateMeterXDto = new MeterDto("X.X.X.X", "Short");
	static int meter8Id;
	static int meter4Id;

	// Author DTO Setup

	static AuthorDto authorTuneTimDto = new AuthorDto("Jeff", "Sulman", "03",
			"1459", "05", "1890", "1460", "1891", "German", new byte[0]);
	static AuthorDto authorTuneBillDto = new AuthorDto("Bill", "Brown", "06",
			"1700", "03", "1701", "1790", "1791", "French", new byte[0]);
	static AuthorDto authorUpdatelDto = new AuthorDto("Zzzz", "Yyyy", "11",
			"2222", "44", "3333", "5555", "6666", "Martian", new byte[0]);
	static int authorTuneTimId;
	static int authorTuneBillId;

	static AuthorDto authorHymnJoeDto = new AuthorDto("Joe", "Smith", "22",
			"1444", "02", "1800", "1260", "1991", "American", new byte[0]);
	static AuthorDto authorHymnBettyDto = new AuthorDto("Betty", "Crocker",
			"16", "2700", "23", "2701", "2790", "2791", "British", new byte[0]);
	static AuthorDto authorTuneUpdatelDto = new AuthorDto("Tttt", "Llll", "91",
			"6565", "55", "0000", "3333", "8888", "Lunar", new byte[0]);
	static int authorHymnJoeId;
	static int authorHymnBettyId;

	// Save State DTO Setup
	static SaveStateDto firstSaveStateDto = new SaveStateDto(0, 1, 2, 3);
	static SaveStateDto secondSaveStateDto = new SaveStateDto(0, 4, 5, 6);
	static SaveStateDto updateSaveStateDto = new SaveStateDto(0, 7, 8, 9);

	static int firstSaveStateId;
	static int secondSaveStateId;

	// Category DTO Setup
	static CategoryDto level1NoPar1 = new CategoryDto(
			"God: His Being, Works and Word", 0, 1, 1);
	static CategoryDto level2aParLevel1 = new CategoryDto("The Glory of God", 0, 1, 2);
	static CategoryDto level3aParLevel2a = new CategoryDto(
			"The Divine Perfections");
	static CategoryDto level3bParLevel2a = new CategoryDto("God: Unchangeable");
	static CategoryDto level2bParLevel1 = new CategoryDto("The Holy Trinity", 0, 2, 2);
	static CategoryDto level2cParLevel1 = new CategoryDto(
			"The Lord Jesus Christ", 0, 3, 2);
	static CategoryDto level3aParLevel2c = new CategoryDto("His Deity", 0, 1, 3);
	static CategoryDto level3bParLevel2c = new CategoryDto("His Praise", 0, 2, 3);
	static CategoryDto level1NoPar2 = new CategoryDto("The Church", 0, 2, 1);
	static CategoryDto level2aParLevel2 = new CategoryDto("The Church of God", 0, 3, 1);
	static CategoryDto level2bParLevel2 = new CategoryDto("The Lord's Day");
	static CategoryDto level3aParLevel2b = new CategoryDto("Morning");
	static CategoryDto level3bParLevel2b = new CategoryDto("Evening");
	static int level3aParLevel2cId;  //Evening
	static int level2aParLevel1Id; //The Church
	
	// UserTypes DTO Setup
	static UserTypeDto userTypeAdmDto = new UserTypeDto("Administrator", "ADM");
	static UserTypeDto userTypeUsrDto = new UserTypeDto("User", "USR");
	static UserTypeDto userTypeUpdateDto = new UserTypeDto("Yyyyyy", "XXX");
	static int userTypeAdmDtoId;
	static int userTypeUsrDtoId;
	static int userTypeUpdateDtoId;

	// Users DTO Setup - Create when we have the UserTypeId
	static byte[] jeffSalt = SecurityHelper.getSalt(Constants.ALGORITHM);
	static byte[] frankSalt = SecurityHelper.getSalt(Constants.ALGORITHM);
	static byte[] updateSalt = SecurityHelper.getSalt(Constants.ALGORITHM);
	static UserDto userJeffDto = new UserDto("jbsulman@email.com", "Jeff",
			"Sulman", "hpForJeff", 0, "jbsulman", jeffSalt);
	static UserDto userFrankDto = new UserDto("frdelani@hotmail.com", "Frank",
			"Delani", "hpForFrank", 0, "fdelani", frankSalt);;
	static UserDto userUpdateDto = new UserDto("xxxxx@xxx.net", "xxxxxx",
			"yyyyyy", "hpForxxxxx", 0, "xyyyyy", updateSalt);

	static int userFrankId;
	static int userJeffId;

	/***************************************
	 Lookups
	***************************************/
	static public String[] lkpAuthorFnameLname = {"Alpha, Jeff", "Tetra, Frank", "Zeta, Bill"};

	static public String[] lkpMeter = { "1.1.1.1", "8.8.8.8", "9.9.9.9"};

	static public String[] lkpTune = { "Blister", "Stutgard", "Uncommon"};
	
	static public String[] lkpCatNoPar = { "AAA First", "BBB Second", "CCC Third"};

	static public String[] lkpCatWithPar = { "AAA Child1", "AAA Child2", "AAA Child3"};

	static public String[] lkpHymnalName = {"Family", "Psalter","Trinity"};

	static public String[] lkpHymnalAbbreviation = {"FAM", "PSA","TRI"};

	static public String[] lkpUserType = {"Administrator", "Master", "User"};

	static public String[] lkpUserFnameLname = {"Alpha, Jeff", "Tetra, Frank", "Zeta, Bill"};

	static public String[] lkpUserUserName = {"BillZ", "FrankT", "JeffA"};

	static public int parIdLookup;
	
	// Author
	static public void addAuthorLookup(){
	
		authorDaoImpl.add(new AuthorDto("Frank", "Tetra", "",
				"", "", "", "", "", "", null));
		authorDaoImpl.add(new AuthorDto("Bill", "Zeta", "",
				"", "", "", "", "", "", null));
		authorDaoImpl.add(new AuthorDto("Jeff", "Alpha", "",
				"", "", "", "", "", "", null));	
	}
	static public void addCategoryLookup(){
		//add first category
		categoryDaoImpl.add(new CategoryDto("AAA First", 0, 1));
		categoryDaoImpl.add(new CategoryDto("BBB Second", 0, 1));
		categoryDaoImpl.add(new CategoryDto("CCC Third", 0, 1));
		
		parIdLookup = categoryDaoImpl.getCategoryByCategory("AAA First").getId();
		categoryDaoImpl.add(new CategoryDto("AAA Child1", parIdLookup, 2));
		categoryDaoImpl.add(new CategoryDto("AAA Child2", parIdLookup, 2));
		categoryDaoImpl.add(new CategoryDto("AAA Child3", parIdLookup, 2));
	}
	static public int addFirstMainAndChildren() {
		List<CategoryDto> list = null;
		int count = 0;

		CategoryDto parentDto;

		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1); // Add Parent
		count++;
		// *********** Add Second Level
		// Get parent ID of first level
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());

		// Set parids for level 2
		TestDaoHelper.level2aParLevel1.setParId(parentDto.getId());
		TestDaoHelper.level2bParLevel1.setParId(parentDto.getId());
		TestDaoHelper.level2cParLevel1.setParId(parentDto.getId());

		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2aParLevel1);
		count++;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2bParLevel1);
		count++;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2cParLevel1);
		count++;
		// Test Level 2
		list = TestDaoHelper.categoryDaoImpl.getChildrenByParentId(parentDto
				.getId());
		for (CategoryDto dto : list) {
			assertEquals(dto.getParId(), parentDto.getId());
		}

		// *********** Add Third Level
		// Get parent ID of 2nd level 1
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level2aParLevel1
						.getCategory());

		// Set parids for level 3a
		TestDaoHelper.level3aParLevel2a.setParId(parentDto.getId());
		TestDaoHelper.level3bParLevel2a.setParId(parentDto.getId());
		// Set order for level 3a
	
		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3aParLevel2a);
		count++;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3bParLevel2a);
		count++;

		// Get parent ID of 2nd level 3c - No level 3b
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level2cParLevel1
						.getCategory());

		// Set parids for level 2c - No 2b
		TestDaoHelper.level3aParLevel2c.setParId(parentDto.getId());
		TestDaoHelper.level3bParLevel2c.setParId(parentDto.getId());
		// Set order for level 2c - No 2b

		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3aParLevel2c);
		count++;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3bParLevel2c);
		TestDaoHelper.level3aParLevel2cId = (TestDaoHelper.categoryDaoImpl.getCategoryByCategory(level3aParLevel2c.getCategory())).getId();
		TestDaoHelper.level2aParLevel1Id = (TestDaoHelper.categoryDaoImpl.getCategoryByCategory(level2aParLevel1.getCategory())).getId();
		count++;

		return count;
	}
	static public void addHymnalLookup(){
		hymnalDaoImpl.add(new HymnalDto("Trinity","TRI"));
		hymnalDaoImpl.add(new HymnalDto("Family","FAM"));
		hymnalDaoImpl.add(new HymnalDto("Psalter","PSA"));
	}
	static public void addMeterLookup(){
		meterDaoImpl.add(new MeterDto("8.8.8.8"));
		meterDaoImpl.add(new MeterDto("1.1.1.1"));
		meterDaoImpl.add(new MeterDto("9.9.9.9"));
	}
	static public void addTunesLookup(){
		addAuthorLookup();
		addMeterLookup();
		int meterIdOne = meterDaoImpl.getMeterByMeter("8.8.8.8").getId();
		int meterIdTwo = meterDaoImpl.getMeterByMeter("1.1.1.1").getId();
		int meterIdThree = meterDaoImpl.getMeterByMeter("9.9.9.9").getId();
		int authorIdOne = authorDaoImpl.getAuthorByFullName("Frank", "Tetra").getId();
		int authorIdTwo = authorDaoImpl.getAuthorByFullName("Bill", "Zeta").getId();
		int authorIdThree = authorDaoImpl.getAuthorByFullName("Jeff", "Alpha").getId();
		tuneDaoImpl.add(new TuneDto("Stutgard",meterIdOne,"1900",authorIdOne,"Swiss"));
		tuneDaoImpl.add(new TuneDto("Blister",meterIdTwo,"1910",authorIdTwo,"Dutch"));
		tuneDaoImpl.add(new TuneDto("Uncommon",meterIdThree,"1100",authorIdThree,"German"));
	}
	static void addTwoHymnals() {
		hymnalDaoImpl.add(hymnalTrinityDto);
		hymnalDaoImpl.add(hymnalFamilyDto);
		hymnalTrinityId = hymnalDaoImpl.getHymnalByAbbreviation(
				hymnalTrinityDto.getAbbreviation()).getId();
		hymnalFamilyId = hymnalDaoImpl.getHymnalByAbbreviation(
				hymnalFamilyDto.getAbbreviation()).getId();
	}
	static void addTwoHymnAuthors() {
		TestDaoHelper.authorDaoImpl.add(TestDaoHelper.authorHymnJoeDto);
		TestDaoHelper.authorDaoImpl.add(TestDaoHelper.authorHymnBettyDto);
		authorHymnJoeId = (authorDaoImpl
				.getAuthorByFullName(authorHymnJoeDto.getFirstName(),
						authorHymnJoeDto.getLastName())).getId();
		
		authorHymnBettyId = (authorDaoImpl.getAuthorByFullName(
				authorHymnBettyDto.getFirstName(),
				authorHymnBettyDto.getLastName())).getId();
		authorHymnJoeDto.setId(authorHymnJoeId);
		authorHymnBettyDto.setId(authorHymnBettyId);
		
	}
	/***************************************/
	// Database Setup 
	/***************************************/
	
	static void addTwoHymns() {
		addTwoTunes();
		addTwoHymnals();
		addFirstMainAndChildren();
		addTwoHymnAuthors();
		
		TestDaoHelper.hymnAndCanDto.setHymnalId(TestDaoHelper.hymnalTrinityId);
		TestDaoHelper.hymnAndCanDto.setAuthorId(TestDaoHelper.authorHymnBettyId);
		TestDaoHelper.hymnAndCanDto.setTuneId(TestDaoHelper.tuneStuttgartId);
		TestDaoHelper.hymnHowGrDto.setHymnalId(TestDaoHelper.hymnalTrinityId);
		TestDaoHelper.hymnHowGrDto.setAuthorId(TestDaoHelper.authorHymnJoeId);
		TestDaoHelper.hymnHowGrDto.setTuneId(TestDaoHelper.tuneLastUnsId);
		TestDaoHelper.hymnDaoImpl.add(TestDaoHelper.hymnHowGrDto);
		TestDaoHelper.hymnDaoImpl.add(TestDaoHelper.hymnAndCanDto);
		hymnAndCanId = TestDaoHelper.hymnDaoImpl
				.getHymnsByTitle("And Can it Be").get(0).getId();
		
		hymnHowGrId = TestDaoHelper.hymnDaoImpl
				.getHymnsByTitle("How Great Thou Art").get(0).getId();
			}
	static void addTwoMeters() {
		meterDaoImpl.add(meter8Dto);
		meterDaoImpl.add(meter4Dto);
		// Store IDs
		meter8Id = meterDaoImpl.getMeterByMeter(meter8Dto.getMeter()).getId();
		meter4Id = meterDaoImpl.getMeterByMeter(meter4Dto.getMeter()).getId();
		meter8Dto.setId(meter8Id);
		meter4Dto.setId(meter4Id);
	}

	
	static void addTwoSavedStates() {
		addTwoUsers();
		firstSaveStateDto.setUserId(userJeffId);
		secondSaveStateDto.setUserId(userFrankId);
		stDaoImpl.add(firstSaveStateDto);
		stDaoImpl.add(secondSaveStateDto);
		firstSaveStateId = (stDaoImpl.getSaveStateByUserId(userJeffId)).getId();
		secondSaveStateId = (stDaoImpl.getSaveStateByUserId(userFrankId))
				.getId();
	}
	
	static void addTwoTuneAuthors() {
		TestDaoHelper.authorDaoImpl.add(TestDaoHelper.authorTuneTimDto);
		TestDaoHelper.authorDaoImpl.add(TestDaoHelper.authorTuneBillDto);
		authorTuneTimId = (authorDaoImpl
				.getAuthorByFullName(authorTuneTimDto.getFirstName(),
						authorTuneTimDto.getLastName())).getId();
		authorTuneBillId = (authorDaoImpl.getAuthorByFullName(
				authorTuneBillDto.getFirstName(),
				authorTuneBillDto.getLastName())).getId();
		authorTuneTimDto.setId(authorTuneTimId);
		authorTuneBillDto.setId(authorTuneBillId);
	}
	
	static void addTwoTunes() {
		addTwoTuneAuthors();
		addTwoMeters();
		tuneStuttgartDto = new TuneDto("Stuttgart", meter8Dto.getId(), "1715",
				authorTuneTimDto.getId(), "German");
		tuneLastUnsDto = new TuneDto("Last Uns Erfreuen", meter4Dto.getId(), "1622",
				authorTuneBillDto.getId(), "French");
		tuneUpdateDto = new TuneDto("CCCCCCC", 0, "3333", 3, "Swaheely");
		tuneDaoImpl.add(tuneStuttgartDto);
		tuneDaoImpl.add(tuneLastUnsDto);
		tuneStuttgartId = tuneDaoImpl.getTuneByName(tuneStuttgartDto.getName())
				.getId();
		tuneLastUnsId = tuneDaoImpl.getTuneByName(tuneLastUnsDto.getName())
				.getId();
	}
	
	static void addTwoUsers() {
		addTwoUserTypes();
		// Add user Type for update user type
		userTypeUpdateDtoId = userTypeDaoImpl.getUserTypeByUserType(
				userTypeUsrDto.getUserType()).getId();
		TestDaoHelper.userJeffDto.setTypeId(TestDaoHelper.userTypeAdmDtoId);
		TestDaoHelper.userFrankDto.setTypeId(TestDaoHelper.userTypeUsrDtoId);
		userDaoImpl.add(userJeffDto);
		userDaoImpl.add(userFrankDto);
		userJeffId = userDaoImpl.getUserByUserName(userJeffDto.getUsername())
				.getId();
		userFrankId = userDaoImpl.getUserByUserName(userFrankDto.getUsername())
				.getId();
		userJeffDto.setId(userJeffId);
		userFrankDto.setId(userFrankId);
		
	}
	
	static void addTwoUserTypes() {
		userTypeDaoImpl.add(userTypeAdmDto);
		userTypeDaoImpl.add(userTypeUsrDto);

		// Store Ids
		userTypeAdmDtoId = userTypeDaoImpl.getUserTypeByUserType(
				userTypeAdmDto.getUserType()).getId();
		userTypeUsrDtoId = userTypeDaoImpl.getUserTypeByUserType(
				userTypeUsrDto.getUserType()).getId();
	}
	
	static void addTwoWholeTunes() {
		addTwoTuneAuthors();
		addTwoMeters();
		tuneStuttgartDto = new TuneDto("Stuttgart", meter8Dto, "1715",
				authorTuneTimDto, "German");
		tuneLastUnsDto = new TuneDto("Last Uns Erfreuen", meter4Dto, "1622",
				authorTuneBillDto, "French");
		tuneUpdateDto = new TuneDto("CCCCCCC", 0, "3333", 3, "Swaheely");
		tuneDaoImpl.add(tuneStuttgartDto);
		tuneDaoImpl.add(tuneLastUnsDto);
		tuneStuttgartId = tuneDaoImpl.getTuneByName(tuneStuttgartDto.getName())
				.getId();
		tuneLastUnsId = tuneDaoImpl.getTuneByName(tuneLastUnsDto.getName())
				.getId();
	}
	
	static public void addUsersLookup(){
		userDaoImpl.add(new UserDto("Frank", "Tetra","FrankT", "FrankT@gmail.com"));
		userDaoImpl.add(new UserDto("Bill", "Zeta","BillZ", "BillZ@gmail.com"));
		userDaoImpl.add(new UserDto("Jeff", "Alpha","JeffA", "JeffA@gmail.com"));
	}
	
	static public void addUserTypeLookup(){
		userTypeDaoImpl.add(new UserTypeDto("User", "USR"));
		userTypeDaoImpl.add(new UserTypeDto("Administrator", "ADM"));
		userTypeDaoImpl.add(new UserTypeDto("Master", "MST"));
	}

}
