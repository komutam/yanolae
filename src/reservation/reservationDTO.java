package reservation;

import java.util.Date;

public class reservationDTO {
	private String accom_code; //���� ���� �ڵ�
	private String room_name; //�� �̸�
	private String id; //���̵�
	private Date regdate; //�����û�� ��¥
	private String reser_type; //���� ����(ex. ���� or ���)
	private String reser_startdate; //���� ������
	private String reser_enddate; //���� ������
	private int reser_price; //�� ���� �ݾ�
	private int reser_number; //���� ��ȣ
	
	private String accom_name; //���� ���� �̸�
	private String accom_addr; //���� ���� �ּ�
	private String accom_number; //���� ���� ��ȭ��ȣ
	
	private String name;  //����� �̸�
	private String phone; //����� ��ȭ��ȣ
	
	
	
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
