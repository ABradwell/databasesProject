package entities;

public class Bill {
	private String property_Type;
	private String first_name;
	private String last_name;
	private int house_num;
	private String payment_type;
	private String status;
	
	public Bill() {}
	public Bill(String pt, String fn, String ln, int hn, String py, String stat) {
		property_Type = pt;
		first_name = fn;
		last_name = ln;
		house_num = hn;
		payment_type = py;
		status = stat;
	}
	public void set_property_Type (String newvar){
		property_Type = newvar;
	}
	public String get_property_type(){
		return property_Type;
	}
	//
	public void set_first_name (String newvar){
		first_name = newvar;
	}
	public String get_first_name (){
		return first_name;
	}
	//
	public void set_last_name (String newvar){
		last_name = newvar;
	}
	public String get_last_name (){
		return last_name;
	}
	//
	public void set_house_num (int newvar){
		house_num = newvar;
	}
	public int get_house_num (){
		return house_num;
	}
	//
	public void set_payment_type (String newvar){
		payment_type = newvar;
	}
	public String get_payment_type (){
		return payment_type;
	}
	//
	public void set_status (String newvar){
		status = newvar;
	}
	public String get_status (){
		return status;
	}
	//
}
