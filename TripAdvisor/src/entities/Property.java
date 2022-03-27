package entities;
public class Property{
	
	private int house_num;
	private String street;
	private String city;
	private String province;
	private String country;
	
	public Property() {
		house_num = 0;
		street = "Street";
		city = "city";
		province = "prov";
		country = "Country";
	}
	public Property(int hn, String str, String cit, String prov, String count) {
		house_num = hn;
		street = str;
		city = cit;
		province = prov;
		country = count;
	}
	public int get_house_num () {
		return house_num;
	}
	public void set_house_num (int hn) {
		house_num = hn;
	}
	public String get_street () {
		return street;
	}
	public void set_street (String st) {
		street = st;
	}
	public String get_city () {
		return city;
	}
	public void set_city (String c) {
		city = c;
	}
	public String get_province () {
		return province;
	}
	public void set_province(String p) {
		province = p;
	}
	public String get_country () {
		return country;
	}
	public void set_country (String c) {
		country = c;
	}
		
}