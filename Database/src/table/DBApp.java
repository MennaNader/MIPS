package table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class DBApp {

	// public void init( );
	ArrayList<Table> tables = new ArrayList<Table>();

	public void createTable(String strTableName,
			Hashtable<String, String> htblColNameType,
			Hashtable<String, String> htblColNameRefs, String strKeyColName)
			throws IOException// DBAppException
	{
		String currentLine = "";
		String currentTblName = "";
		FileReader f = new FileReader("metadata.csv");
		BufferedReader br = new BufferedReader(f);
		while ((currentLine = br.readLine()) != null) {
			String[] x = currentLine.split(",");
			if (currentTblName.length() == 0||!currentTblName.equals(x[0])) {
				currentTblName = x[0];
				Table t = new Table(currentTblName);
				tables.add(t);
			}
			Table currentTable =tables.get(tables.size()-1);
			currentTable.columnNamesTypes.put(x[1], x[2]);
			if(x[5]!=null){
			currentTable.columnNamesRefs.put(x[1], x[5]);
			}
			

		}

	}
}
