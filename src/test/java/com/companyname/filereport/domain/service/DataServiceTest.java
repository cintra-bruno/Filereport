package com.companyname.filereport.domain.service;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.companyname.filereport.dto.ReportDTO;

public class DataServiceTest {
	
	private List<String> lines;
	
	@Before
	public void init() {
		lines = new ArrayList<String>();
		lines.add("001�1234567891234�Pedro�50000");
		lines.add("001�3245678865434�Paulo�40000.99");
		lines.add("002�2345675434544345�Jose da Silva�Rural");
		lines.add("002�2345675433444345�Eduardo Pereira�Rural");
		lines.add("003�10�[1-10-100,2-30-2.50,3-40-3.10]�Pedro");
		lines.add("003�08�[1-34-10,2-33-1.50,3-40-0.10]�Paulo");
	}

	@Test
	public void assertIfCustomersQuantityEquals2() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(lines);
		assertThat(report.getInFileCustomersQuantity(), equalTo(2L));
		
	}
	
	@Test
	public void assertIfSalesmanQuantityEquals2() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(lines);
		assertThat(report.getInFileSalesmanQuantity(), equalTo(2L));
		
	}
	
	@Test
	public void assertIfMostExpensiveSalesIdEquals10() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(lines);
		assertThat(report.getMostExpensiveSalesId(), equalTo(10));
		
	}
	
	@Test
	public void assertIfWorstSalesmanNameEqualsPaulo() throws Exception {
		DataService service = new DataService();
		ReportDTO report = service.generateReportData(lines);
		assertThat(report.getWorstSalesmanName(), equalTo("Paulo"));
		
	}
}
