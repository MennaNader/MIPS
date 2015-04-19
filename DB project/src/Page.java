import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pageNo; // no of the page
	String[][] tuples; // 2d array representing the tuples in the same order as
	String strKeyColName ;			// the columns in the hashtable given when inserting
	int currentLine = 0; //specifies which line in the page is empty now 
	String strTableName;
	   String tableHash ;
	   Table table ;
		

	public Page(String pageNo, int columnNo,String strTableName) {
		this.pageNo = pageNo;
		tuples = new String[columnNo][200];
	//	this.strKeyColName =strKeyColName;
		this.strTableName = strTableName;//assume 200 max no of tuples for now
	//	this.tableHash = tableHash ; */
		table = new Table (strTableName);
	}

	public void insert(Hashtable<String, String> htblColNameValue) throws FileNotFoundException, IOException, ClassNotFoundException{
		
	   String Format = "" ;
	   String x;
	   String y ;
		Enumeration<String> enKeys = htblColNameValue.keys();
		Enumeration<String> enElements = htblColNameValue.elements();
		int j = tuples.length -1;
	
		while(enKeys.hasMoreElements()&&(enElements.hasMoreElements())){
		   x =  enKeys.nextElement();
		   y= enElements.nextElement();
		//   Map.Entry entry = (Map.Entry) enKeys.next();
		   if( (this.getTable().getIndex().contains(x)) || 
				   ( (x.equals(this.strKeyColName))&&(this.getTable().getIndex().contains(x))  ) )
			{  // we need a hash no 
				ObjectInputStream oisHash = new ObjectInputStream(new FileInputStream(
						new File(this.strTableName + this .pageNo +".ser")));
				ExtensibleHash EX = (ExtensibleHash) oisHash.readObject();
				
			 
			
		     Format = Integer.parseInt(y, 2) +","+this.pageNo+","+ currentLine;
				
				EX.insertIndex(Format);
				oisHash.close();
				// save
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
						new File(this.strTableName + this .pageNo  + ".ser")));
				oos.writeObject(EX);
				oos.close();
				}//	insertIndex 
			tuples[j][currentLine] = enKeys.nextElement();
			
			j--;
		}
		
		
		Enumeration<String> en = htblColNameValue.elements();
		int i = tuples.length -1;
		while(en.hasMoreElements()){
			tuples[i][currentLine] = en.nextElement();
			i--;
		}
		currentLine ++;
		
	
	}
	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String[][] getTuples() {
		return tuples;
	}

	public void setTuples(String[][] tuples) {
		this.tuples = tuples;
	}

	public int getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}

	public String getStrTableName() {
		return strTableName;
	}

	public void setStrTableName(String strTableName) {
		this.strTableName = strTableName;
	}

	public String getTableHash() {
		return tableHash;
	}

	public void setTableHash(String tableHash) {
		this.tableHash = tableHash;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean exceedLimit(){
		if(tuples[0].length==199)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
	//	Page p = new Page("0", 4,"hi","",);
		Hashtable<String,String> h = new Hashtable<String, String>();
		h.put("id", "1");
		h.put("department", "csen");
		h.put("gpa", "0.95");
		Enumeration<String> en = h.elements();
        while(en.hasMoreElements()){
		System.out.println(en.nextElement());
	}
	}
}
