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
		lines.add("001Á1234567891234ÁPedroÁ50000");
		lines.add("001Á3245678865434ÁPauloÁ40000.99");
		lines.add("002Á2345675434544345ÁJose da SilvaÁRural");
		lines.add("002Á2345675433444345ÁEduardo PereiraÁRural");
		lines.add("003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁPedro");
		lines.add("003Á08Á[1-34-10,2-33-1.50,3-40-0.10]ÁPaulo");
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
