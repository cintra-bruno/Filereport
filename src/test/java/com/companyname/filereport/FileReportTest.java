package com.companyname.filereport;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.companyname.filereport.exception.InvalidDirectoryException;

public class FileReportTest {
	
	public FileReport fileReport;
	
	@Before
	public void init() {
		fileReport = new FileReport();
	}

	@Test
	public void assertIfThrowsInvalidDirectoryExceptionForNull() {
		Exception exception = assertThrows(InvalidDirectoryException.class, () -> {
			fileReport.processFiles(null);
	    });
		
		String expectedMessage = "vari�vel de ambiente HOMEPATH n�o criada";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void assertIfThrowsInvalidDirectoryExceptionForInexistentDirectory() {
		String directory = "/randomnonexistentdirectory/random//";
		
		Exception exception = assertThrows(InvalidDirectoryException.class, () -> {
			fileReport.processFiles(directory);
	    });
		
		String expectedMessage = directory + " n�o existe";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
