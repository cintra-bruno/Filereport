package com.companyname.filereport.domain.entity;

import java.math.BigDecimal;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class Salesman extends EntityBase {

	private String cpf;
	private String name;
	private BigDecimal salary;

	public Salesman(String cpf, String name, BigDecimal salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
