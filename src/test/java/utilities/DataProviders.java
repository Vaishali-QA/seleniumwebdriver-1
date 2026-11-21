package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider (name="LoginData")
	
	public String [][] getData() throws IOException
	{
		
		String path = System.getProperty("user.dir")
		        + "/src/test/resources/TestData/LoginData.xlsx";  //taking file from testdata
		ExcelUtility xlutl=new ExcelUtility(path);
		
		int totalrows=xlutl.getRowCount("Sheet1");
		int totalcols=xlutl.getCellCount("Sheet1",1);
		
		String logindata[][]=new String [totalrows][totalcols];
		
		for (int i=1;i<=totalrows;i++)
		{
			for (int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]= xlutl.getCellData("Sheet1", i, j);
			}
		}
		
	    return logindata;  //returning two dimension arrayS
		
	}
			

}
