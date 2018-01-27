
public class DVD{
	private int id ;
	private String dname;
	private int dcount;
	private int status;
	
	public DVD(int id,String dname,int dcount,int status){
		super();
		this.id=id;
		this.dname=dname;
		this.dcount=dcount;
		this.status=status;
	}
	public DVD(String dname,int dcount,int status){
		super();
		this.dname=dname;
		this.dcount=dcount;
		this.status=status;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setDname(String dname){
		this.dname=dname;
	}
	public String getDname(){
		return dname;
	}
	public void setDcount(int dcount){
		this.dcount=dcount;
	}
	public int getDcount(){
		return dcount;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public int getStatus(){
		return status;
	}
}