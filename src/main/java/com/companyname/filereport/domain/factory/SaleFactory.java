package com.companyname.filereport.domain.factory;

import java.util.ArrayList;
import java.util.List;

import com.companyname.filereport.domain.entity.Entity;
import com.companyname.filereport.domain.entity.Item;
import com.companyname.filereport.domain.entity.Sale;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class SaleFactory extends EntityFactory {

	public static String ITEM_SEPARATOR = ",";

	public Entity create(String data) {
		String[] splitedData = data.split(DATA_SEPARATOR);
		Integer id = Integer.parseInt(splitedData[1].trim());
		String itensData = splitedData[2];
		String salesmanName = splitedData[3];

		return new Sale(id, createItens(itensData), salesmanName);
	}

	private List<Item> createItens(String itensData) {

		String[] data = itensData.substring(1, itensData.length() - 2).split(ITEM_SEPARATOR);

		List<Item> itens = new ArrayList<Item>();

		EntityFactory itemFactory = new ItemFactory();

		for (String iten : data) {
			itens.add((Item) itemFactory.create(iten));
		}

		return itens;
	}

}
