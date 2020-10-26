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
		correctLines.add("001Á1234567891234ÁPedroÁ50000");
		correctLines.add("001Á3245678865434ÁPauloÁ40000.99");
		correctLines.add("002Á2345675434544345ÁJose da SilvaÁRural");
		correctLines.add("002Á2345675433444345ÁEduardo PereiraÁRural");
		correctLines.add("003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁPedro");
		correctLines.add("003Á08Á[1-34-10,2-33-1.50,3-40-0.10]ÁPaulo");
		
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
		incorrectLines.add("004Á1234567891234ÁPedroÁ50000");
		
		assertThrows(InvalidLineException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
	}
	
	@Test
	public void assertIfThrowsFormatoInvalidoExceptionForPriceItem() throws Exception {
		DataService service = new DataService();
		
		List<String> incorrectLines = new ArrayList<String>(correctLines);
		incorrectLines.add("003Á10Á[1-10- ,2-30-2.50,3-40-3.10]ÁPedro");
		
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
		incorrectLines.add("003Á10Á[ -10-20,2-30-2.50,3-40-3.10]ÁPedro");
		
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
		incorrectLines.add("003Á10Á[1-  -100,2-30-2.50,3-40-3.10]ÁPedro");
		
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
		incorrectLines.add("003Á Á[1-10-20,2-30-2.50,3-40-3.10]ÁPedro");
		
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
		incorrectLines.add("001Á1234567891234ÁJoaoÁ ");
		
		Exception exception = assertThrows(InvalidFormatException.class, () -> {
			service.generateReportData(incorrectLines);
	    });
		
		String expectedMessage = "salary_salesman";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
}
