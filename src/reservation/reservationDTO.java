package reservation;

import java.util.Date;

public class reservationDTO {
	private String accom_code; //숙박 업소 코드
	private String room_name; //방 이름
	private String id; //아이디
	private Date regdate; //예약신청한 날짜
	private String reser_type; //예약 종류(ex. 숙박 or 대실)
	private String reser_startdate; //예약 시작일
	private String reser_enddate; //예약 종료일
	private int reser_price; //방 예약 금액
	private int reser_number; //예약 번호
	
	private String accom_name; //숙박 업소 이름
	private String accom_addr; //숙박 업소 주소
	private String accom_number; //숙박 업소 전화번호
	
	private String name;  //사용자 이름
	private String phone; //사용자 전화번호
	
	
	
	public String getReser_type() {
		return reser_type;
	}
	public void setReser_type(String reser_type) {
		this.reser_type = reser_type;
	}
	public String getReser_startdate() {
		return reser_startdate;
	}
	public void setReser_startdate(String reser_startdate) {
		this.reser_startdate = reser_startdate;
	}
	public String getReser_enddate() {
		return reser_enddate;
	}
	public void setReser_enddate(String reser_enddate) {
		this.reser_enddate = reser_enddate;
	}
	public String getAccom_code() {
		return accom_code;
	}
	public void setAccom_code(String accom_code) {
		this.accom_code = accom_code;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getReser_price() {
		return reser_price;
	}
	public void setReser_price(int reser_price) {
		this.reser_price = reser_price;
	}
	public String getAccom_name() {
		return accom_name;
	}
	public void setAccom_name(String accom_name) {
		this.accom_name = accom_name;
	}
	public String getAccom_addr() {
		return accom_addr;
	}
	public void setAccom_addr(String accom_addr) {
		this.accom_addr = accom_addr;
	}
	public String getAccom_number() {
		return accom_number;
	}
	public void setAccom_number(String accom_number) {
		this.accom_number = accom_number;
	}
	public int getReser_number() {
		return reser_number;
	}
	public void setReser_number(int reser_number) {
		this.reser_number = reser_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}	
