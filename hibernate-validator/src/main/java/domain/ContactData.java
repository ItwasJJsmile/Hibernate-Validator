package domain;

import javax.validation.constraints.NotNull;

import validationgroup.USValidation;


public class ContactData {
	private Country country; // ENUM indicating the user's country

	private String zipCode;

	private String phoneNumber;

	public ContactData(Country country, String zipCode, String phoneNumber) {
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
	}

	public Country getCountry(){
		return this.country;
	}
	
	@NotNull(message="Zip code is mandatory", groups={USValidation.class})
	public String getZipCode() {
		return zipCode;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
}
