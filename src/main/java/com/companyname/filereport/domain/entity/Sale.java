package com.companyname.filereport.domain.entity;

import java.util.List;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class Sale extends EntityBase {

	private Integer id;
	private List<Item> itens;
	private String salesmanName;
	
	public Sale(Integer id, List<Item> itens, String salesmanName) {
		super();
		this.id = id;
		this.itens = itens;
		this.salesmanName = salesmanName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

}
