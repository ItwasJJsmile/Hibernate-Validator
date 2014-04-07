package domain;

import groupprovider.ContactDataSequenceProvider;

import org.hibernate.validator.group.GroupSequenceProvider;

@GroupSequenceProvider(value = ContactDataSequenceProvider.class)
public class ContactDataBis extends ContactData{

	public ContactDataBis(Country country, String zipCode, String phoneNumber) {
		super(country, zipCode, phoneNumber);
	}
	
}
