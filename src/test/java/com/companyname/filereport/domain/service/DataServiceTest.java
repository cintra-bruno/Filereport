package com.companyname.filereport.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.companyname.filereport.dto.ReportDTO;
import com.companyname.filereport.exception.InvalidFormatException;
import com.companyname.filereport.exception.InvalidLineException;

public class DataServiceTest {
	
	private List<String> correctLines;
	
	@Before
	public void init() {
		correctLines = new ArrayList<String>();
		correctLines.add("001�1234567891234�Pedro�50000");
		correctLines.add("001�3245678865434�Paulo�40000.99");
		correctLines.add("002�2345675434544345�Jose da Silva�Rural");
		correctLines.add("002�2345675433444345�Eduardo Pereira�Rural");
		correctLines.add("003�10�[1-10-100,2-30-2.50,3-40-3.10]�Pedro");
		correctLines.add("003�08�[1-34-10,2-33-1.50,3-40-0.10]�Paulo");
		
	}

	@Test
	public void assertIfCustomersQuantityEquals2() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(correctLines);
		assertThat(report.getInFileCustomersQuantity(), equalTo(2L));
		
	}
	
	@Test
	public void assertIfSalesmanQuantityEquals2() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(correctLines);
		assertThat(report.getInFileSalesmanQuantity(), equalTo(2L));
		
	}
	
	@Test
	public void assertIfMostExpensiveSalesIdEquals10() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(correctLines);
		assertThat(report.getMostExpensiveSalesId(), equalTo(10));
		
	}
	
	@Test
	public void assertIfWorstSalesmanNameEqualsPaulo() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(correctLines);
		assertThat(report.getWorstSalesmanName(), equalTo("Paulo"));
		
	}
	
	@Test
	public void assertIfThrowsRegistroInvalidoException() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("004�1234567891234�Pedro�50000");
		
		assertThrows(InvalidLineException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
	}
	
	@Test
	public void assertIfThrowsFormatoInvalidoExceptionForPriceItem() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("003�10�[1-10- ,2-30-2.50,3-40-3.10]�Pedro");
		
		Exception exception = assertThrows(InvalidFormatException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
		
		String expectedMessage = "price_item";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void assertIfThrowsFormatoInvalidoExceptionForIdItem() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("003�10�[ -10-20,2-30-2.50,3-40-3.10]�Pedro");
		
		Exception exception = assertThrows(InvalidFormatException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
		
		String expectedMessage = "id_item";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void assertIfThrowsFormatoInvalidoExceptionForQuantityItem() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("003�10�[1-  -100,2-30-2.50,3-40-3.10]�Pedro");
		
		Exception exception = assertThrows(InvalidFormatException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
		
		String expectedMessage = "quantity_item";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void assertIfThrowsFormatoInvalidoExceptionForIdSales() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("003� �[1-10-20,2-30-2.50,3-40-3.10]�Pedro");
		
		Exception exception = assertThrows(InvalidFormatException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
		
		String expectedMessage = "id_sales";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void assertIfThrowsFormatoInvalidoExceptionForSalarySalesman() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("001�1234567891234�Joao� ");
		
		Exception exception = assertThrows(InvalidFormatException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
		
		String expectedMessage = "salary_salesman";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
}
