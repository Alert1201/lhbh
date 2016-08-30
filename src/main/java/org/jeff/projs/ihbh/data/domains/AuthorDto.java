package org.jeff.projs.ihbh.data.domains;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.jeff.projs.ihbh.utils.EqualsHelper;

public class AuthorDto implements BaseDto{
	
	private int id;
	private String firstName;
	private String lastName;
	private String dobMonth;
	private String dobYear;
	private String dodMonth;
	private String dodYear;
	private String dobCirca;
	private String dodCirca;
	private String nationality;
	private byte[] bio;
	
	public AuthorDto(){}
	
	public AuthorDto(int authorId){
		this.id = authorId;
	}
	
	public AuthorDto(ResultSet rs) throws SQLException{
		this.id = rs.getInt("author_id");
		this.firstName = rs.getString("first_name");
		this.lastName = rs.getString("last_name");
		this.dobMonth = rs.getString("DOB_month");
		this.dobYear = rs.getString("DOB_year");
		this.dobCirca = rs.getString("DOB_circa");
		this.dodMonth = rs.getString("DOD_month");
		this.dodYear = rs.getString("DOD_year");
		this.dodCirca = rs.getString("DOD_circa");
		this.nationality = rs.getString("author_nationality");
		this.bio=rs.getBytes("bio");
	}
	
	public AuthorDto(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public AuthorDto(String firstName, String lastName, 
			String dobMonth, String dobYear, 
			String dodMonth, String dodYear, 
			String dobCirca, String dodCirca, String nationality,
			byte[] bio){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDobMonth(dobMonth);
		this.setDobYear(dobYear);
		this.setDodMonth(dodMonth);
		this.setDodYear(dodYear);
		this.setDobCirca(dobCirca);
		this.setDodCirca(dodCirca);
		this.setNationality(nationality);
		this.setBio(bio);
	}
	
	public AuthorDto(String firstName, String lastName, 
			String dobMonth, String dobYear, 
			String dodMonth, String dodYear, 
			String nationality)
{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDobMonth(dobMonth);
		this.setDobYear(dobYear);
		this.setDodMonth(dodMonth);
		this.setDodYear(dodYear);
		this.setNationality(nationality);
}

	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFullName(){
		return this.lastName + ", " + this.firstName;
	}
	public String getDobMonth() {
		return dobMonth;
	}
	public String getDobYear() {
		return dobYear;
	}
	public String getDodMonth() {
		return dodMonth;
	}
	public String getDodYear() {
		return dodYear;
	}
	public String getDobCirca() {
		return dobCirca;
	}
	public String getDodCirca() {
		return dodCirca;
	}
	public byte[] getBio() {
		return bio;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}
	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}
	public void setDodMonth(String dodMonth) {
		this.dodMonth = dodMonth;
	}
	public void setDodYear(String dodYear) {
		this.dodYear = dodYear;
	}
	public void setDobCirca(String dobCirca) {
		this.dobCirca = dobCirca;
	}
	public void setDodCirca(String dodCirca) {
		this.dodCirca = dodCirca;
	}
	public void setBio(byte[] bio) {
		this.bio = bio;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	   public boolean equals(AuthorDto other) {
	      if(this == other){
	         return true;
	      } 
	      if(other == null){
	          return false;
	      }
	      if (other instanceof AuthorDto) {
	          return internalEquals((AuthorDto) other);
	      }
	      return super.equals(other);
	   }

	   protected boolean internalEquals(AuthorDto other) {        
	        //suppose fuelType can be Integer.
	        if (!EqualsHelper.areObjectsEqual(this.dobCirca, other.dobCirca)){                   
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.dobMonth, other.dobMonth)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.dobYear, other.dobYear)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.dodCirca, other.dodCirca)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.dodMonth, other.dodMonth)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.dodYear, other.dodYear)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.firstName, other.firstName)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.lastName, other.lastName)){ 
	            return false;
	        }
	        if (!EqualsHelper.areObjectsEqual(this.nationality, other.nationality)){ 
	            return false;
	        }
	        if (!Arrays.equals(this.bio, other.bio)){
	        	return false;
	        }
	        return true;
	    }
	
}
