package reservation;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import reservation.reservationDTO;

public class reservationDAO {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	
	
	private static reservationDAO dao = new reservationDAO();
	
	
	public static reservationDAO getDAO(){
		return dao;
	}
	
	//커넥션 풀 얻기
	public Connection getCon() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
		
	}
	
	
	
	
	
	// 고객이 보는 예약 정보 테이블 저장하기
	public int insertReservation(reservationDTO reserDto){
		int num=0;
		try {
			
			con = getCon();
			sql = "insert into reservation(accom_code, room_name, id, regdate, reser_type, reser_startdate, reser_enddate, reser_price) values(?,?,?,NOW(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reserDto.getAccom_code());
			pstmt.setString(2, reserDto.getRoom_name());
			pstmt.setString(3, reserDto.getId());
			pstmt.setString(4, reserDto.getReser_type());
			pstmt.setString(5, reserDto.getReser_startdate());
			pstmt.setString(6, reserDto.getReser_enddate());
			pstmt.setInt(7, reserDto.getReser_price());
			pstmt.executeUpdate();
			
			sql = "select reser_number from reservation where accom_code=? and room_name=? and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reserDto.getAccom_code());
			pstmt.setString(2, reserDto.getRoom_name());
			pstmt.setString(3, reserDto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("reser_number");
			}
			
			
		} catch (Exception ex1) {
			System.out.println("insertRoomReservation() 예외 : " + ex1);
		
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
			
		}//finally end
		
		return num;
		
	}//insertReservation() end
	
	
	
	
	// 방 매진 구하는 예약 정보 테이블 저장하기
	public void insertRoomReservation(reservationDTO reserDto, int diff, int num) {

		try {
		
			con = getCon();
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = null;
			Date edate = null;
			
			for(int i=0; i<diff; i++){ //diff(예약 종료일 - 예약 시작일) 차이만큼 반복문 실행

				sdate = dateformat.parse(reserDto.getReser_startdate()); //예약 시작일을 Date 타입인 sdate 변수에 저장
				Calendar cal1 = Calendar.getInstance(); //Calendar 객체 생성
				cal1.setTime(sdate);  //Calendar 객체에 sdate을 넣음
				cal1.add(Calendar.DATE, i); //Calendar 객체의 날짜를 i만큼 증가시킴
				
				String reser_startdate = dateformat.format(cal1.getTime()); //증가시킨 Calendar 객체의 날짜를 reser_startdate 변수에 저장
				edate = dateformat.parse(reser_startdate); //reser_startdate 변수를 Date 타입의 edate에 저장
				
				
				Calendar cal2 = Calendar.getInstance(); //Calendar 객체 생성
				cal2.setTime(edate); //Calendar 객체에 edate을 넣음
				cal2.add(Calendar.DATE, 1); //Calendar 객체의 날짜를 1 증가시킴
				
				String reser_enddate = dateformat.format(cal2.getTime());//증가시킨 Calendar 객체의 날짜를 reser_enddate 변수에 저장
				//reser_enddate = reser_startdate + 1(날짜)
				
				sql = "insert into room_reservation(accom_code, room_name, id, regdate, reser_type, reser_startdate, reser_enddate, reser_number) values(?,?,?,NOW(),?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, reserDto.getAccom_code());
				pstmt.setString(2, reserDto.getRoom_name());
				pstmt.setString(3, reserDto.getId());
				pstmt.setString(4, reserDto.getReser_type());
				pstmt.setString(5, reser_startdate);
				pstmt.setString(6, reser_enddate);
				pstmt.setInt(7, num);
				pstmt.executeUpdate();
				
				
			}//for end
			
		} catch (Exception ex1) {
			System.out.println("insertRoomReservation() 예외 : " + ex1);

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {}

		}//finally end

	}//insertRoomReservation() end
	
	// 고객이 보는 예약 정보 리스트 가져오기
		public List getListReservation(String memId){
			List<reservationDTO> list = new ArrayList<reservationDTO>();
			
			try {
				con = dao.getCon();
				sql = "select * from reservation inner join accominfo where reservation.accom_code=accominfo.accom_code and id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					reservationDTO reserDto = new reservationDTO();
					
					reserDto.setAccom_code(rs.getString("accom_code"));
					reserDto.setRoom_name(rs.getString("room_name"));
					reserDto.setId(rs.getString("id"));
					reserDto.setRegdate(rs.getDate("regdate"));
					reserDto.setReser_type(rs.getString("reser_type"));
					reserDto.setReser_startdate(rs.getString("reser_startdate"));
					reserDto.setReser_enddate(rs.getString("reser_enddate"));
					reserDto.setReser_price(rs.getInt("reser_price"));
					reserDto.setReser_number(rs.getInt("reser_number"));
					reserDto.setAccom_name(rs.getString("accom_name"));
					reserDto.setAccom_addr(rs.getString("accom_addr"));
					reserDto.setAccom_number(rs.getString("accom_number"));
					
					list.add(reserDto);
				}
				
				
			} catch(Exception ex) {
				System.out.println("getListReservation() 예외 : " + ex);
			} finally {
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception ex2) {}
				
			}//finally end
			
			return list;
		}//getListReservation() 
		
		
	//고객이 보는 예약 정보 삭제하기
	public void deleteReservation(int reser_number){
		
		try{
			con = dao.getCon();
			sql = "delete from reservation where reser_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reser_number);
			pstmt.executeUpdate();
			
			
		} catch(Exception ex){
			System.out.println("deleteReservation() 예외 : " + ex);
		} finally {
			try {
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
			
		}//finally end
		
	}//deleteReservation()
	
	// 고객이 보는 예약 정보 리스트 가져오기
	public List getAdminListReservation(int start, int end) {
		List<reservationDTO> list = new ArrayList<reservationDTO>();

		try {
			con = dao.getCon();
			sql = "select * from reservation inner join accominfo on reservation.accom_code=accominfo.accom_code "
					+ "inner join memberinfo m on m.id = reservation.id limit ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationDTO reserDto = new reservationDTO();

				reserDto.setAccom_code(rs.getString("accom_code"));
				reserDto.setRoom_name(rs.getString("room_name"));
				reserDto.setId(rs.getString("id"));
				reserDto.setRegdate(rs.getDate("regdate"));
				reserDto.setReser_type(rs.getString("reser_type"));
				reserDto.setReser_startdate(rs.getString("reser_startdate"));
				reserDto.setReser_enddate(rs.getString("reser_enddate"));
				reserDto.setReser_price(rs.getInt("reser_price"));
				reserDto.setReser_number(rs.getInt("reser_number"));
				reserDto.setAccom_name(rs.getString("accom_name"));
				reserDto.setAccom_addr(rs.getString("accom_addr"));
				reserDto.setAccom_number(rs.getString("accom_number"));
				reserDto.setName(rs.getString("name"));
				reserDto.setPhone(rs.getString("phone"));

				list.add(reserDto);
			}

		} catch (Exception ex) {
			System.out.println("getAdminListReservation() 예외 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}

		}// finally end

		return list;
	}// getAdminListReservation()
	
	
	// 예약 현황 페이지 처리
	public int getcountAdminReser() {
		int x = 0;
		try {
			con = getCon();
			sql = "select count(*) from reservation inner join accominfo where reservation.accom_code=accominfo.accom_code";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getcountAdminReser 예외처리" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}

		}// finally end

		return x;
	}// 예약현황 페이지 처리 끝
	
}
