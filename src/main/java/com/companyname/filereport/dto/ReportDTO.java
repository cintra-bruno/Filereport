package com.companyname.filereport.dto;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class ReportDTO {

	private Long inFileCustomersQuantity;
	private Long inFileSalesmanQuantity;
	private Integer mostExpensiveSalesId;
	private String worstSalesmanName;

	public Long getInFileCustomersQuantity() {
		return inFileCustomersQuantity;
	}

	public void setInFileCustomersQuantity(Long inFileCustomersQuantity) {
		this.inFileCustomersQuantity = inFileCustomersQuantity;
	}

	public Long getInFileSalesmanQuantity() {
		return inFileSalesmanQuantity;
	}

	public void setInFileSalesmanQuantity(Long inFileSalesmanQuantity) {
		this.inFileSalesmanQuantity = inFileSalesmanQuantity;
	}

	public Integer getMostExpensiveSalesId() {
		return mostExpensiveSalesId;
	}

	public void setMostExpensiveSalesId(Integer mostExpensiveSalesId) {
		this.mostExpensiveSalesId = mostExpensiveSalesId;
	}

	public String getWorstSalesmanName() {
		return worstSalesmanName;
	}

	public void setWorstSalesmanName(String worstSalesmanName) {
		this.worstSalesmanName = worstSalesmanName;
	}

}
