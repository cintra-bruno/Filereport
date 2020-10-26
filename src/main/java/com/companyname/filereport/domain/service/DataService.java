package com.companyname.filereport.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.companyname.filereport.domain.entity.Customer;
import com.companyname.filereport.domain.entity.Entity;
import com.companyname.filereport.domain.entity.Item;
import com.companyname.filereport.domain.entity.Sale;
import com.companyname.filereport.domain.entity.Salesman;
import com.companyname.filereport.domain.factory.EntityFactory;
import com.companyname.filereport.dto.ReportDTO;
import com.companyname.filereport.exception.InvalidFormatException;
import com.companyname.filereport.exception.InvalidLineException;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class DataService {

	public ReportDTO generateReportData(List<String> lines) throws InvalidLineException, InvalidFormatException {
		
		if(lines == null) {
			return new ReportDTO(); 
		}
		
		List<Entity> records = new ArrayList<Entity>();
		for (String line : lines) {
			records.add(EntityFactory.instance(line).create(line));
		}
		ReportDTO reportData = new ReportDTO();
		
		reportData.setInFileCustomersQuantity(records.stream().filter(record -> record instanceof Customer).count());
		reportData.setInFileSalesmanQuantity(records.stream().filter(record -> record instanceof Salesman).count());
		
		List<Sale> sales = records.stream().filter(reg -> reg instanceof Sale).map(reg -> (Sale) reg)
				.collect(Collectors.toList());
		
		reportData.setMostExpensiveSalesId(searchMostExpensiveSaleId(sales));
		reportData.setWorstSalesmanName(searchWorstSalesmanName(sales));

		return reportData;
	}

	private Integer searchMostExpensiveSaleId(List<Sale> sales) {
		BigDecimal mostExpensivePrice = BigDecimal.ZERO;
		Integer mostExpensiveSaleId = 0;

		for (Sale sale : sales) {
			BigDecimal purchaseTotal = calculateTotalPurchase(sale);
			if (purchaseTotal.compareTo(mostExpensivePrice) > 0) {
				mostExpensiveSaleId = sale.getId();
				mostExpensivePrice = purchaseTotal;
			}
		}
		return mostExpensiveSaleId;
	}

	private BigDecimal calculateTotalPurchase(Sale sale) {
		BigDecimal purchaseTotal = BigDecimal.ZERO;
        for (Item item : sale.getItens()) {
            purchaseTotal = purchaseTotal.add(item.getPrice());
        }
        return purchaseTotal;
	}

	private String searchWorstSalesmanName(List<Sale> sales) {
		BigDecimal worstSalePrice = calculateTotalPurchase(sales.get(0));
		Sale worstSale = sales.get(0);
		for (Sale sale : sales) {
			if (worstSalePrice.compareTo(calculateTotalPurchase(sale)) < 0) {
			} else {
				worstSalePrice = calculateTotalPurchase(sale);
				worstSale = sale;
			}
		}

		return worstSale.getSalesmanName();
	}

}
