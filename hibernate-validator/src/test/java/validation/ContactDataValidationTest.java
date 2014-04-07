package validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import validationgroup.USValidation;
import domain.ContactData;
import domain.ContactDataBis;
import domain.Country;

public class ContactDataValidationTest {
	private Validator validator;

	@BeforeClass
	public void initValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testValidationWithNoGroup(){
		/*
		 *  As there are no default validation rules, validating an empty ContactData bean instance
		 *  shouldn't yield any validation error 
		 */
		ContactData cd = new ContactData(Country.US, null, null);
		Set<ConstraintViolation<ContactData>> validationResult = validator.validate(cd);
		Assert.assertEquals(validationResult.size(), 0);
	}
	
	@Test
	public void testValidationWithUsRules1(){
		/*
		 *  In this test, we tell pass a second argument to the validator, telling it to apply
		 *  the validation rules that belong to the group "UsValidation". As a result, we expect
		 *  an error regarding the mandatory zip code. 
		 */
		ContactData cd = new ContactData(Country.US, null, null);
		Set<ConstraintViolation<ContactData>> validationResult = validator.validate(cd, USValidation.class);
		Assert.assertEquals(validationResult.size(), 1);
		
		String errorMessage = validationResult.iterator().next().getMessage();
		Assert.assertEquals(errorMessage, "Zip code is mandatory");
	}
	
	@Test
	public void testValidationWithUsRules2(){
		/*
		 * In this test, we act as if we don't hold reference the validator object. To tell it it
		 * should apply the validation rules from the "UsValidation" group, we used the 
		 * @GroupSequenceProvider annotation
		 */
		ContactData cd = new ContactDataBis(Country.US, null, null);
		Set<ConstraintViolation<ContactData>> validationResult = validator.validate(cd);
		Assert.assertEquals(validationResult.size(), 1);
		
		String errorMessage = validationResult.iterator().next().getMessage();
		Assert.assertEquals(errorMessage, "Zip code is mandatory");
	}
}
