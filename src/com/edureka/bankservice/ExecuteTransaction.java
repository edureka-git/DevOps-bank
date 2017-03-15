package com.edureka.bankservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExecuteTransaction")
public class ExecuteTransaction extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	//ServletContext context = getContext();
	//URL url1 = context.getResource("/WEB-INF/classes/details.txt");
	//URL url2 = context.getResource("/WEB-INF/classes/details_tmp.txt");
	private static final String FILENAME = "DevOpsCert/details.txt";
	private static final String FILENAME_TMP = "DevOpsCert/details_tmp.txt";

    public ExecuteTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session=request.getSession();
		String acounnum =(String)session.getAttribute("account_number");
		//changing the balance for the given account
		String UpdatedRecord = null;
		BufferedReader br = null;
		BufferedWriter br_tmp = null;
		int flag=-1;
		FileReader fr = null;
		FileWriter fr_tmp = null;
		try 
		{
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(FILENAME));

			//File inputFile = new File(FILENAME_TMP);
			fr_tmp = new FileWriter(FILENAME_TMP);
			br_tmp = new BufferedWriter(fr_tmp);
			String sCurrentLine_tmp;
			br_tmp = new BufferedWriter(new FileWriter(FILENAME_TMP));
			
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
				//splitting the line into words and matching the userid and password
				String []logindata=sCurrentLine.split(",");
				
				if(logindata[0].equals(acounnum))
				{
					//fr_tmp = new FileWriter(FILENAME_TMP);
					//System.out.println(request.getParameter("balance"));
					logindata[2] = Integer.toString(Integer.parseInt(logindata[2])  + Integer.parseInt(request.getParameter("balance"))) ;
					
					UpdatedRecord = logindata[0] + "," + logindata[1] + "," +  logindata[2];
					br_tmp.write(UpdatedRecord);
					br_tmp.newLine();
 					
				}
				else {
					
					br_tmp.write(sCurrentLine);
					br_tmp.newLine();
				}
				
			}
			
			br.close();
			br_tmp.close();
			fr.close();
			fr_tmp.close();
			
			File oldfile =new File(FILENAME_TMP);
			File newfile =new File(FILENAME);
			Boolean sucess = newfile.delete();
			//System.out.println("************************File Delete Status**************************");
			System.out.println(sucess);
			oldfile.renameTo(newfile);
			
			
			//control will come down here after break
			if(flag==1)
			{
				RequestDispatcher rd = request.getRequestDispatcher("GetAccountAndBalance");
		        rd.forward(request, response);
				
				//System.out.println("within  flag1 of execute transaction");
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("GetAccountAndBalance");
		        rd.forward(request, response);
			}

		} catch (IOException e) {

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
