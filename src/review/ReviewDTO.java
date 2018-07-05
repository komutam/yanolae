package review;

import java.util.Date;

public class ReviewDTO {
	private String accom_code;
	private String re_num;
	private String re_content;
	private String id;
	private Date up_date;
	private float starpoint;
	public String getAccom_code() {
		return accom_code;
	}
	public void setAccom_code(String accom_code) {
		this.accom_code = accom_code;
	}
	
	public String getRe_num() {
		return re_num;
	}
	public void setRe_num(String re_num) {
		this.re_num = re_num;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
	public float getStarpoint() {
		return starpoint;
	}
	public void setStarpoint(float starpoint) {
		this.starpoint = starpoint;
	}
	
}
