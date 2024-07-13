package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data Provider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_LoginData.xlsx";   //taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path);        //object for XLUtility
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCol = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalRows][totalCol];      //for 2D array
		//read the data from xl storing in two deminsional array
		
		for(int r=1; r<=totalRows; r++)    //row=1 -- 1st row is header
		{
			for(int c=0; c<totalCol; c++)
			{
				logindata[r-1][c] = xlutil.getCellData("Sheet1", r, c);
			}
		}
		
		return logindata;      ////returning two dimension array
	}

	//Data Provider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
