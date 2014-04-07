package groupprovider;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import validationgroup.USValidation;
import domain.ContactData;
import domain.ContactDataBis;
import domain.Country;

public class ContactDataSequenceProvider implements DefaultGroupSequenceProvider<ContactData>{
	
	
	public List<Class<?>> getValidationGroups(ContactData contactData) {
		List<Class<?>> sequence = new ArrayList<Class<?>>();
		
		/*
		 * ContactDataBis must be added to the returned list so that the validator gets to know
		 * the default validation rules, at the very least.
		 */
		sequence.add(ContactDataBis.class);
		
		/*
		 *  Here, we can implement a certain logic to determine what are the additional group of rules
		 *  that must be applied. 
		 */
		if(contactData != null && contactData.getCountry() == Country.US){
			sequence.add(USValidation.class);
		}
		
		return sequence;
	}

}
