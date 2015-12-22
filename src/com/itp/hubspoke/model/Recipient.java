package com.itp.hubspoke.model;

public class Recipient {

	/*
	 * 
	 * 
	 * 
	 * "Address":{  
            "FirstName":"Will",
            "LastName":"Smith",
            "Street":"460 Park Ave S.",
            "ApartmentOrSuite":null,
            "CareOf":null,
            "City":"New York",
            "StateOrProvince":"NY",
            "Country":"US",
            "PostalCode":"10016",
            "TelephoneNumber":"212-333-4444",
            "Email":null,
            "CompanyName":null,
            "IsResidential":false,
            "Name":null
         },
         
         */
	private String firstName;
	private String lastName;
	private String StringWithNumber;
	private String city;
	private String stateOrProvinceInUS;
	private String country;
	private String postcode;
	private String telephone;
	private String email;
	
	
	
	public Recipient(String firstName, String lastName, String streetWithNumber,
			String postcode, String city, String stateOrProvince, String country, String telephone) {
		//new Recipient("Vincent", "Van Gogh", "Mekelweg 4", "2628CD", "Delft", "", "the Netherlands", "+31(0)152789111");
		this.firstName = firstName;
		this.lastName = lastName;
		this.StringWithNumber = streetWithNumber;
		this.postcode = postcode;
		this.city = city;
		this.stateOrProvinceInUS = stateOrProvince;
		this.country = country;
		this.telephone = telephone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return StringWithNumber;
	}
	public void setStreet(String street) {
		StringWithNumber = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateOrProvinceInUS() {
		return stateOrProvinceInUS;
	}
	public void setStateOrProvinceInUS(String stateOrProvinceInUS) {
		this.stateOrProvinceInUS = stateOrProvinceInUS;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
