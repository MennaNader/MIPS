package table;

public class Table {
	
	private int rows;
	private String name;
	public Object columnNamesTypes;
	public Object columnNamesRefs;

	public Table(){
	}
	
	public Table(String name){
		this.setName(name);
		//this.rows =rows;
	}
	
   public int getRows(){
	   return rows;
   }
   
   public void setRows(int newRows){
	   this.rows = newRows;
   }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}
