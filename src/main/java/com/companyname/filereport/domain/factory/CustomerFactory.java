package com.companyname.filereport.domain.factory;

import com.companyname.filereport.domain.entity.Customer;
import com.companyname.filereport.domain.entity.Entity;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class CustomerFactory extends EntityFactory {


	public Entity create(String data) {
		String[] splitedData = data.split(DATA_SEPARATOR); 
		String cnpj = splitedData[1];
		String name =  splitedData[2];
		String salary = splitedData[3];
		return new Customer(cnpj, name, salary);
	}

}
