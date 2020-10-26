package com.companyname.filereport.domain.factory;

import com.companyname.filereport.domain.entity.Entity;
import com.companyname.filereport.exception.InvalidFormatException;
import com.companyname.filereport.exception.InvalidLineException;

/**
 * 
 * @author Bruno.cintra
 *
 */
public abstract class EntityFactory {

	public static final String DATA_SEPARATOR = "ç";

	public abstract Entity create(String data) throws InvalidFormatException;

	public static EntityFactory instance(String line) throws InvalidLineException {
		String type = line.substring(0, 3);

		switch (type) {
		case "001":
			return new SalesmanFactory();
		case "002":
			return new CustomerFactory();
		case "003":
			return new SaleFactory();
		default:
			throw new InvalidLineException();
		}
	}

}
