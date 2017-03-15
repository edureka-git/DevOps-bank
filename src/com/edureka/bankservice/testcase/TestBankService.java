package com.edureka.bankservice.testcase;

import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TestBankService extends TestCase{

	 @Mock
	 HttpServletRequest request;
	 @Mock
	 HttpServletResponse response;
	 @Mock
	 static HttpSession session;

	 @Mock
	 RequestDispatcher rd;
		 
	 @Before
	 protected void setUp() throws Exception {
	  MockitoAnnotations.initMocks(this);  
	 }

	 @Test
	 public void testLogin() throws Exception {
		 
	  String FILENAME = "/var/lib/jenkins/workspace/FP_QA_PACKAGE/target/DevopsBank-0.0.1-SNAPSHOT/WEB-INF/classes/Login.txt";
		 
	  when(request.getParameter("username")).thenReturn("1111");
	  when(request.getParameter("password")).thenReturn("p1");
	  when(request.getSession()).thenReturn(session);
	  when(request.getRequestDispatcher("GetAccountAndBalance")).thenReturn(rd);
	  
	  BufferedReader br = null;
		
		FileReader fr = null;
		try 
		{
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) 
			{
				//splitting the line into words and matching the userid and password
				
				String []logindata=sCurrentLine.split(",");
				assertEquals(logindata[0], request.getParameter("username"));
				assertEquals(logindata[1], request.getParameter("password"));	
				break;
		
			}
		}		 catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	
	 }
	 
	 @Test 
	 public void testDetails() throws Exception {	
		 
	 	String FILENAME = "/var/lib/jenkins/workspace/FP_QA_PACKAGE/target/DevopsBank-0.0.1-SNAPSHOT/WEB-INF/classes/details.txt";

	 	when(request.getParameter("account_number")).thenReturn("1111");
        when(request.getSession()).thenReturn(session);
		when(request.getRequestDispatcher("Trans.jsp")).thenReturn(rd);
	 	
	 	//file connection
	 	BufferedReader br = null;
	 	FileReader fr = null;
	 	try 
	 	{
	 		fr = new FileReader(FILENAME);
	 		br = new BufferedReader(fr);
	 		String sCurrentLine;
	 		br = new BufferedReader(new FileReader(FILENAME));

	 		while ((sCurrentLine = br.readLine()) != null) 
	 		{
	 			//splitting the line into words and matching the userid and password
	 			String [] accountDetails=sCurrentLine.split(",");
	 			assertEquals(accountDetails[0], request.getParameter("account_number"));
	 			assertEquals(accountDetails[1], "Saurav Kumar");
	 			assertEquals(accountDetails[2], "9000");
 				break;
	 			
	 		}

	 		} catch (Exception e) {

	 			e.printStackTrace();

	 		} finally {

	 			try {

	 				if (br != null)
	 					br.close();

	 				if (fr != null)
	 					fr.close();

	 			} catch (IOException ex) {

	 				ex.printStackTrace();

	 			}

	 		}
	 	}

	 

}

