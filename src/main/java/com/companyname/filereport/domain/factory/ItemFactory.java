package com.companyname.filereport.domain.factory;

import java.math.BigDecimal;

import com.companyname.filereport.domain.entity.Entity;
import com.companyname.filereport.domain.entity.Item;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class ItemFactory extends EntityFactory {

	public static String DATA_SEPARATOR = "-";
	
	public Entity create(String data) {
		String[] splitedData = data.split(DATA_SEPARATOR); 
		Integer id = Integer.parseInt(splitedData[0].trim());
		Integer quantity = Integer.parseInt(splitedData[1].trim());
		BigDecimal salary = new BigDecimal(splitedData[2].trim());
		return new Item(id, quantity, salary);
	}

}
