package utility;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Utilities {
	public static int count  = 0;
	
	public static String getData(int id, String field) {
		Recordset recordset=null;
		String column = "";

		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("//Users//amitkumar//Documents//TestData_Result.xlsx");
			String query = "Select * From Sheet1 where ID="+id;
			recordset = connection.executeQuery(query);
			count  = recordset.getCount();
			while (recordset.next()) {
				column = recordset.getField(field);
			}
			recordset.close();
			connection.close();
		}

		catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return column;
	}

}
