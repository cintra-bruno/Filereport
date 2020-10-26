package com.companyname.filereport.domain.factory;

import java.math.BigDecimal;

import com.companyname.filereport.domain.entity.Entity;
import com.companyname.filereport.domain.entity.Item;
import com.companyname.filereport.exception.InvalidFormatException;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class ItemFactory extends EntityFactory {

	public static String DATA_SEPARATOR = "-";

	public Entity create(String data) throws InvalidFormatException {
		String[] splitedData = data.split(DATA_SEPARATOR);
		Integer id;
		Integer quantity;
		BigDecimal price;
		try {
			id = Integer.parseInt(splitedData[0].trim());
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidFormatException("id_item");
		}
		try {
			quantity = Integer.parseInt(splitedData[1].trim());
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidFormatException("quantity_item");
		}
		try {
			price = new BigDecimal(splitedData[2].trim());
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidFormatException("price_item");
		}
		return new Item(id, quantity, price);
	}

}
