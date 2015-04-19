import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> pagesName = new ArrayList<String>();
	transient ArrayList<Page> pages = new ArrayList<Page>();
	static ArrayList <String> index = new ArrayList <String> ();
	String currentPage = "0";
	static String strTableName;
	Hashtable<String, String> htblColNameType;
	Hashtable<String, String> htblColNameRefs;
	
public	 String strKeyColName;
   public ExtensibleHash tableHash ;
	public Table(String strTableName,
			Hashtable<String,String> htblColNameType,
			Hashtable<String,String>htblColNameRefs,
			String strKeyColName) {
		this.strKeyColName = strKeyColName;
		this.strTableName = strTableName;
		this.htblColNameRefs = htblColNameRefs;
		this.htblColNameType = htblColNameType;
	}

	public ArrayList<String> getPagesName() {
		return pagesName;
	}

	public void setPagesName(ArrayList<String> pagesName) {
		this.pagesName = pagesName;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public ArrayList<String> getIndex() {
		return index;
	}

	public void setIndex(ArrayList<String> index) {
		this.index = index;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getStrTableName() {
		return strTableName;
	}

	public void setStrTableName(String strTableName) {
		this.strTableName = strTableName;
	}

	public Hashtable<String, String> getHtblColNameType() {
		return htblColNameType;
	}

	public void setHtblColNameType(Hashtable<String, String> htblColNameType) {
		this.htblColNameType = htblColNameType;
	}

	public Hashtable<String, String> getHtblColNameRefs() {
		return htblColNameRefs;
	}

	public void setHtblColNameRefs(Hashtable<String, String> htblColNameRefs) {
		this.htblColNameRefs = htblColNameRefs;
	}

	public String getStrKeyColName() {
		return strKeyColName;
	}

	public void setStrKeyColName(String strKeyColName) {
		this.strKeyColName = strKeyColName;
	}

	public ExtensibleHash getTableHash() {
		return tableHash;
	}

	public void setTableHash(ExtensibleHash tableHash) {
		this.tableHash = tableHash;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void insert(Hashtable<String, String> htblColNameValue,String strTableName)
			throws Exception {
		updatePages();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				new File(strTableName + currentPage + ".ser")));
		Page p = (Page) ois.readObject();
		ois.close();
		p.insert(strTableName, htblColNameValue);

		
		ObjectOutputStream oosP = new ObjectOutputStream(
				new FileOutputStream(new File(strTableName
						+ currentPage + ".ser")));
		oosP.writeObject(p);
		oosP.close();
	
	}
		

	public void updatePages() throws Exception {
		Page p;
		if (pages.isEmpty()) {
			p = new Page(currentPage, htblColNameType.size(),this);
			pagesName.add(currentPage);
			pages.add(p);

		} else {
			p = pages.get(Integer.parseInt(currentPage));
			if (p.exceedLimit()) {
				currentPage = (Integer.parseInt(currentPage) + 1) + "";
				p = new Page(currentPage, htblColNameType.size(),this);
				pagesName.add(currentPage);
				pages.add(p);

			} 
				
			
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				new File(currentPage + ".ser")));
		oos.writeObject(p);
		oos.close();
	}
	public ExtensibleHash getUpdatedExtensibleHash(String columnName)throws Exception{
		ExtensibleHash h = new ExtensibleHash();
		index.add(columnName);
		Enumeration<String> en = htblColNameType.keys();
		int count = 0;
		int columnNo = 0;
		while(!en.hasMoreElements()){
			if(en.nextElement().equals(columnName)){
				columnNo = htblColNameType.size()-1-count;
			}
			count++;
		}
		for(int i = 0; i< pages.size();i++){
			Page p=pages.get(i);
			for(int j = 0; j<200;j++){
			String index = p.tuples[columnNo][j]+","+i+j;
			h.insertIndex(index);
			}
			
		}
		return h;
	}
	public void delete(String strTableName,
			Hashtable<String, String> htblColNameValue, String strOperator) throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				new File(strTableName + currentPage + ".ser")));
		Page p = (Page) ois.readObject();
		ois.close();
		p.deleteFromTable(strTableName, htblColNameValue, strOperator);
	}
}

