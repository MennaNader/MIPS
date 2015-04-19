import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pageNo; // no of the page
	String[][] tuples; // 2d array representing the tuples in the same order as
	String strKeyColName; // the columns in the hashtable given when inserting
	int currentLine = 0; // specifies which line in the page is empty now
	String strTableName;
	String tableHash;
	Table table;

	public Page(String pageNo, int columnNo, Table table) {
		this.pageNo = pageNo;
		tuples = new String[columnNo][200];
		// this.strKeyColName =strKeyColName;
		this.strTableName = strTableName;// assume 200 max no of tuples for now
		// this.tableHash = tableHash ; */
		this.table = table;
	}

	public void insert(String strTableName, Hashtable<String, String> htblColNameValue)
			throws Exception {

		String Format = "";
		String x;
		String y;
		Enumeration<String> enKeys = htblColNameValue.keys();
		Enumeration<String> enElements = htblColNameValue.elements();
		int j = tuples.length - 1;

		while (enKeys.hasMoreElements() && (enElements.hasMoreElements())) {
			x = enKeys.nextElement();
			y = enElements.nextElement();
			boolean found = false;
			String Key1 = null;
			String Key2 = null;
			// Map.Entry entry = (Map.Entry) enKeys.next();
			if (this.getTable().getIndex().contains(x)) {
				if (x.equals(this.getTable().getStrKeyColName())) {

					Key1 = x;
					ObjectInputStream oisHash = new ObjectInputStream(
							new FileInputStream(new File(strTableName
									+ this.pageNo + ".ser")));
					ExtensibleHash EX = (ExtensibleHash) oisHash.readObject();
					oisHash.close();
					Format = y + "," + this.pageNo + "," + currentLine;

					EX.insertIndex(Format);

					// save
					ObjectOutputStream oos = new ObjectOutputStream(
							new FileOutputStream(new File(strTableName
									+ this.pageNo + ".ser")));
					oos.writeObject(EX);
					oos.close();
				}
				if (!found) {
					Key2 = x;
					found = true;
				}
				if (found && Key2 != null && Key1 != null)

				{
					ObjectInputStream oisKD = new ObjectInputStream(
							new FileInputStream(new File(strTableName
									+ this.pageNo + ".ser")));
					KDTree KD = (KDTree) oisKD.readObject();
					oisKD.close();
					KD.put(Key1, Key2, Integer.toString(currentLine));
					ObjectOutputStream oosKD = new ObjectOutputStream(
							new FileOutputStream(new File(strTableName
									+ this.pageNo + ".ser")));
					oosKD.writeObject(KD);
					oosKD.close();
				}
			}// insertIndex
			tuples[j][currentLine] = enKeys.nextElement();

			j--;
		}

		Enumeration<String> en = htblColNameValue.elements();
		int i = tuples.length - 1;
		while (en.hasMoreElements()) {
			tuples[i][currentLine] = en.nextElement();
			i--;
		}
		currentLine++;
	}

	public String selectFromTable(String strTable,
			Hashtable<String, String> htblColNameValue, String strOperator)
			throws Exception {
		ObjectInputStream oisTable = new ObjectInputStream(new FileInputStream(
				new File(strTable + ".ser")));
		Table T = (Table) oisTable.readObject();
		oisTable.close();
		// Iterator <String> in = null ;
		String result = null;

		String s = "";
		String Output = "";
		String[] y = new String[3];
		String s1 = "";
		int j = 1;
		ArrayList<String> st = new ArrayList();

		Enumeration<String> en = htblColNameValue.elements();
		if (strOperator == "OR" || strOperator == "") {
			ObjectInputStream oisHash1 = new ObjectInputStream(
					new FileInputStream(new File(strTable + this.pageNo
							+ ".ser")));
			ExtensibleHash EX1 = (ExtensibleHash) oisHash1.readObject();
			oisHash1.close();
			while (en.hasMoreElements()) {
				s = en.nextElement();
				if (strOperator == "OR" && j <= 2) {
					j++;
					s1 = s;
					s = en.nextElement();
				}
				Output = EX1.search(s);
				y = Output.split(",");

				ObjectInputStream oisPage = new ObjectInputStream(
						new FileInputStream(new File(strTable + y[1] + ".ser")));
				Page P = (Page) oisPage.readObject();
				oisPage.close();
				P.currentLine = Integer.parseInt(y[2]);

				for (int i = 0; i < tuples.length; i++) {
					result += "," + tuples[i][P.currentLine];
				}

				ObjectOutputStream oosP = new ObjectOutputStream(
						new FileOutputStream(new File(strTable + y[1] + ".ser")));
				oosP.writeObject(P);
				oosP.close();

				ObjectOutputStream oosE1 = new ObjectOutputStream(
						new FileOutputStream(new File(strTable + this.pageNo
								+ ".ser")));
				oosE1.writeObject(EX1);
				oosE1.close();

			}
			if (strOperator == "OR") {
				ObjectInputStream oisHash2 = new ObjectInputStream(
						new FileInputStream(new File(strTable + this.pageNo
								+ ".ser")));
				ExtensibleHash EX2 = (ExtensibleHash) oisHash2.readObject();
				oisHash2.close();
				Output = EX2.search(s1);
				y = Output.split(",");

				ObjectInputStream oisPage1 = new ObjectInputStream(
						new FileInputStream(new File(strTable + y[1] + ".ser")));
				Page P1 = (Page) oisPage1.readObject();
				oisPage1.close();
				P1.currentLine = Integer.parseInt(y[2]);

				for (int i = 0; i < tuples.length; i++) {
					result += "," + tuples[i][P1.currentLine];
				}

				ObjectOutputStream oosP1 = new ObjectOutputStream(
						new FileOutputStream(new File(strTable + y[1] + ".ser")));
				oosP1.writeObject(P1);
				oosP1.close();

				ObjectOutputStream oosE2 = new ObjectOutputStream(
						new FileOutputStream(new File(strTable + this.pageNo
								+ ".ser")));
				oosE2.writeObject(EX2);
				oosE2.close();
			}
			j = 1;
		}
		if (strOperator == "AND") {
			ObjectInputStream oisKD1 = new ObjectInputStream(
					new FileInputStream(new File(strTable + this.pageNo
							+ ".ser")));
			KDTree KD1 = (KDTree) oisKD1.readObject();
			oisKD1.close();
			while (en.hasMoreElements() && j <= 2) {
				s1 = s;
				s = en.nextElement();
				j++;
			}
			st = KD1.get(s, s1);
			if (st == null) {
				result = "";
				return result;
			} else {
				for (int i = 0; i < 2; i++) {
					if (st.get(i).contains(s + "," + s1))
						result = st.get(i);
				}
				String[] Y = new String[3];
				ObjectInputStream oisPage2 = new ObjectInputStream(
						new FileInputStream(new File(strTable + y[1] + ".ser")));
				Page P2 = (Page) oisPage2.readObject();
				oisPage2.close();
				P2.currentLine = Integer.parseInt(y[2]);

				for (int i = 0; i < tuples.length; i++) {
					result += "," + tuples[i][P2.currentLine];
				}
				ObjectOutputStream oosP3 = new ObjectOutputStream(
						new FileOutputStream(new File(strTable + y[1] + ".ser")));
				oosP3.writeObject(P2);
				oosP3.close();

				ObjectOutputStream oosKD = new ObjectOutputStream(
						new FileOutputStream(new File(strTable + this.pageNo
								+ ".ser")));
				oosKD.writeObject(KD1);
				oosKD.close();

			}

		}

		ObjectOutputStream oosTable = new ObjectOutputStream(
				new FileOutputStream(new File(strTable + ".ser")));
		oosTable.writeObject(T);
		oosTable.close();

		return result;
	}

	public void deleteFromTable(String strTableName,
			Hashtable<String, String> htblColNameValue, String strOperator)
			throws Exception

	{

		ObjectInputStream oisTable = new ObjectInputStream(new FileInputStream(
				new File(strTableName + ".ser")));
		Table T = (Table) oisTable.readObject();
		oisTable.close();
		// Iterator <String> in = null ;
		String result = null;

		String s = "";
		String Output = "";
		String[] y = new String[3];
		String s1 = "";
		int j = 1;
		ArrayList<String> st = new ArrayList();

		Enumeration<String> en = htblColNameValue.elements();
		if (strOperator == "OR" || strOperator == "") {
			ObjectInputStream oisHash1 = new ObjectInputStream(
					new FileInputStream(new File(strTableName + this.pageNo
							+ ".ser")));
			ExtensibleHash EX1 = (ExtensibleHash) oisHash1.readObject();
			oisHash1.close();
			while (en.hasMoreElements()) {
				s = en.nextElement();
				if (strOperator == "OR" && j <= 2) {
					j++;
					s1 = s;
					s = en.nextElement();
				}
				Output = EX1.search(s);
				y = Output.split(",");

				ObjectInputStream oisPage = new ObjectInputStream(
						new FileInputStream(new File(strTableName + y[1]
								+ ".ser")));
				Page P = (Page) oisPage.readObject();
				oisPage.close();
				P.currentLine = Integer.parseInt(y[2]);

				for (int i = 0; i < tuples.length; i++) {
					tuples[i][P.currentLine] = null;

				}
				EX1.delete(Output);
				ObjectOutputStream oosP = new ObjectOutputStream(
						new FileOutputStream(new File(strTableName + y[1]
								+ ".ser")));
				oosP.writeObject(P);
				oosP.close();

				ObjectOutputStream oosE1 = new ObjectOutputStream(
						new FileOutputStream(new File(strTableName
								+ this.pageNo + ".ser")));
				oosE1.writeObject(EX1);
				oosE1.close();

			}
			if (strOperator == "OR") {
				ObjectInputStream oisHash2 = new ObjectInputStream(
						new FileInputStream(new File(strTableName + this.pageNo
								+ ".ser")));
				ExtensibleHash EX2 = (ExtensibleHash) oisHash2.readObject();
				oisHash2.close();
				Output = EX2.search(s1);
				y = Output.split(",");

				ObjectInputStream oisPage1 = new ObjectInputStream(
						new FileInputStream(new File(strTableName + y[1]
								+ ".ser")));
				Page P1 = (Page) oisPage1.readObject();
				oisPage1.close();
				P1.currentLine = Integer.parseInt(y[2]);

				for (int i = 0; i < tuples.length; i++) {
					tuples[i][P1.currentLine] = null;
				}
				EX2.delete(Output);

				ObjectOutputStream oosP1 = new ObjectOutputStream(
						new FileOutputStream(new File(strTableName + y[1]
								+ ".ser")));
				oosP1.writeObject(P1);
				oosP1.close();

				ObjectOutputStream oosE2 = new ObjectOutputStream(
						new FileOutputStream(new File(strTableName
								+ this.pageNo + ".ser")));
				oosE2.writeObject(EX2);
				oosE2.close();
			}
			j = 1;
		}
		if (strOperator == "AND") {
			ObjectInputStream oisKD1 = new ObjectInputStream(
					new FileInputStream(new File(strTableName + this.pageNo
							+ ".ser")));
			KDTree KD1 = (KDTree) oisKD1.readObject();
			oisKD1.close();
			while (en.hasMoreElements() && j <= 2) {
				s1 = s;
				s = en.nextElement();
				j++;
			}
			st = KD1.get(s, s1);
			if (st != null) {
				for (int i = 0; i < 2; i++) {
					if (st.get(i).contains(s + "," + s1))
						result = st.get(i);
				}
				String[] Y = new String[3];
				ObjectInputStream oisPage2 = new ObjectInputStream(
						new FileInputStream(new File(strTableName + y[1]
								+ ".ser")));
				Page P2 = (Page) oisPage2.readObject();
				oisPage2.close();
				P2.currentLine = Integer.parseInt(y[2]);

				for (int i = 0; i < tuples.length; i++) {
					tuples[i][P2.currentLine] = null;
				}
				KD1.remove(s, s1);
				ObjectOutputStream oosP3 = new ObjectOutputStream(
						new FileOutputStream(new File(strTableName + y[1]
								+ ".ser")));
				oosP3.writeObject(P2);
				oosP3.close();

				ObjectOutputStream oosKD = new ObjectOutputStream(
						new FileOutputStream(new File(strTableName
								+ this.pageNo + ".ser")));
				oosKD.writeObject(KD1);
				oosKD.close();

			}

		}

		ObjectOutputStream oosTable = new ObjectOutputStream(
				new FileOutputStream(new File(strTableName + ".ser")));
		oosTable.writeObject(T);
		oosTable.close();

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

	public boolean exceedLimit() {
		if (tuples[0].length == 199)
			return true;
		return false;
	}

	public static void main(String[] args) {
		// Page p = new Page("0", 4,"hi","",);
		Hashtable<String, String> h = new Hashtable<String, String>();
		h.put("id", "1");
		h.put("department", "csen");
		h.put("gpa", "0.95");
		Enumeration<String> en = h.elements();
		while (en.hasMoreElements()) {
			System.out.println(en.nextElement());
		}
	}
}
