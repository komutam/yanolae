package accom;

public class accomDTO {
	//accominfo Table
	private String accom_name; //숙박 업소 이름
	private String accom_code; //숙박 업소 코드
	private String accom_addr; //숙박 업소 주소
	private String accom_location; //숙박 업소 지역
	private String accom_number; //숙박 업소 전화번호
	private String accom_resertime; //숙박 업소 예약 시간
	private String accom_kind; //숙박 업소 종류(ex. H:호텔, M:모텔, P:펜션, Q:게스트하우스)
	private String accom_image; //숙박 업소 이미지



	public String getAccom_addr() {
		return accom_addr;
	}
	public void setAccom_addr(String accom_addr) {
		this.accom_addr = accom_addr;
	}
	public String getAccom_name() {
		return accom_name;
	}
	public void setAccom_name(String accom_name) {
		this.accom_name = accom_name;
	}
	public String getAccom_code() {
		return accom_code;
	}
	public void setAccom_code(String accom_code) {
		this.accom_code = accom_code;
	}
	public String getAccom_location() {
		return accom_location;
	}
	public void setAccom_location(String accom_location) {
		this.accom_location = accom_location;
	}
	public String getAccom_number() {
		return accom_number;
	}
	public void setAccom_number(String accom_number) {
		this.accom_number = accom_number;
	}
	public String getAccom_resertime() {
		return accom_resertime;
	}
	public void setAccom_resertime(String accom_resertime) {
		this.accom_resertime = accom_resertime;
	}
	public String getAccom_kind() {
		return accom_kind;
	}
	public void setAccom_kind(String accom_kind) {
		this.accom_kind = accom_kind;
	}
	public String getAccom_image() {
		return accom_image;
	}
	public void setAccom_image(String accom_image) {
		this.accom_image = accom_image;
	}
	
	
}//class
