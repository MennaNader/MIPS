import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> pagesName = new ArrayList<String>();
	transient ArrayList<Page> pages = new ArrayList<Page>();
	ArrayList <String> index = new ArrayList <String> ();
	String currentPage = "0";
	String strTableName;
	Hashtable<String, String> htblColNameType;
	Hashtable<String, String> htblColNameRefs;
	
	
public	 String strKeyColName;
   public ExtensibleHash tableHash ;
	public Table(String tblName) {
		strTableName = tblName;
		htblColNameType = new Hashtable<String, String>();
		htblColNameRefs = new Hashtable<String, String>();
		
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

	public void insert(Hashtable<String, String> htblColNameValue)
			throws Exception {
		updatePages();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				new File(currentPage + ".ser")));
		Page p = (Page) ois.readObject();
		ois.close();
		p.insert(htblColNameValue);
	}

	public void updatePages() throws Exception {
		Page p;
		if (pages.isEmpty()) {
			p = new Page(currentPage, htblColNameType.size(),this.strTableName);
			pagesName.add(currentPage);
			pages.add(p);

		} else {
			p = pages.get(Integer.parseInt(currentPage));
			if (p.exceedLimit()) {
				currentPage = (Integer.parseInt(currentPage) + 1) + "";
				p = new Page(currentPage, htblColNameType.size(),this.strTableName);
				pagesName.add(currentPage);
				pages.add(p);

			} 
				
			
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				new File(currentPage + ".ser")));
		oos.writeObject(p);
		oos.close();
	}
}
