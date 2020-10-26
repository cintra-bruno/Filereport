package com.companyname.filereport.domain.factory;

import java.math.BigDecimal;

import com.companyname.filereport.domain.entity.Entity;
import com.companyname.filereport.domain.entity.Salesman;
import com.companyname.filereport.exception.InvalidFormatException;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class SalesmanFactory extends EntityFactory {


	public Entity create(String data) throws InvalidFormatException {
		String[] splitedData = data.split(DATA_SEPARATOR); 
		String cpf = splitedData[1];
		String name =  splitedData[2];
		BigDecimal salary;
		try {
			salary = new BigDecimal(splitedData[3].trim());
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidFormatException("salary_salesman");
		}
		return new Salesman(cpf, name, salary);
	}

}
