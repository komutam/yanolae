package room;

public class roomDTO {
	
	//roominfo Table
	private String accom_code; //���� ���� �ڵ�
	private String room_type; //���� ����(ex. ���� or ���)
	private String room_name; //�� �̸�
	private int rentable_price; //��� ���(�Ϸ縸 ����)
	private int accom_price; //���� ���(��Ⱓ ����)
	private int room_capa; //�� �����ο�
	private int room_count; //���� �� ����
	private String room_image; //�� �̹���
	
	public String getAccom_code() {
		return accom_code;
	}
	public void setAccom_code(String accom_code) {
		this.accom_code = accom_code;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
	public int getAccom_price() {
		return accom_price;
	}
	public void setAccom_price(int accom_price) {
		this.accom_price = accom_price;
	}
	public int getRoom_capa() {
		return room_capa;
	}
	public void setRoom_capa(int room_capa) {
		this.room_capa = room_capa;
	}
	public int getRoom_count() {
		return room_count;
	}
	public void setRoom_count(int room_count) {
		this.room_count = room_count;
	}
	public String getRoom_image() {
		return room_image;
	}
	public void setRoom_image(String room_image) {
		this.room_image = room_image;
	}
	
	public int getRentable_price() {
		return rentable_price;
	}
	public void setRentable_price(int rentable_price) {
		this.rentable_price = rentable_price;
	}
	
}
