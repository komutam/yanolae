package room;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import accom.accomDTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import room.roomDTO;

public class roomDAO {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private static roomDAO dao = new roomDAO();

	public static roomDAO getDAO() {
		return dao;
	}

	//커넥션 풀 얻기
	public Connection getCon() throws Exception {
		Context ct = new InitialContext();
		DataSource ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();

	}

	
	
	//숙박 업소 등급별 방 리스트 가져오기
	public List getRoomDetailList(String accom_code, String room_type, int diff) throws Exception {
		List<roomDTO> list = new ArrayList<roomDTO>();
		
		try {
			con = getCon();
			sql = "select * from roominfo where accom_code=? and room_type=?";
			//방 종류와 숙박 업소 코드에 해당하는 roominfo 테이블의 방 정보를 가져옴
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			pstmt.setString(2, room_type);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomDTO roomDTO = new roomDTO();
				roomDTO.setAccom_code(rs.getString("accom_code"));
				roomDTO.setRoom_type(rs.getString("room_type"));
				roomDTO.setRoom_name(rs.getString("room_name"));
				roomDTO.setRentable_price(rs.getInt("rentable_price"));
				roomDTO.setAccom_price(rs.getInt("accom_price") * diff); //diff만큼 예약 가격을 곱해줌(diff = 예약 종료일 - 예약 시작일)
				roomDTO.setRoom_capa(rs.getInt("room_capa"));
				roomDTO.setRoom_count(rs.getInt("room_count"));
				roomDTO.setRoom_image(rs.getString("room_image"));

				list.add(roomDTO);
			}//while end

		} catch (Exception ex1) {
			System.out.println("getRoomDetailList() 예외 : " + ex1);

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
			} catch (Exception ex2) {}

		}//finally end

		return list;
		
	}//getRoomDetailList() end
	
	
	
	// 숙박 업소 방 리스트 가져오기(검색 날짜에 따른 방 매진 처리)
	public List getRoomList(String accom_code, int diff, String startdate)
			throws Exception {
		List<roomDTO> list = new ArrayList<roomDTO>();

		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			con = getCon();
			sql = "select * from roominfo where accom_code=?";
			//숙박 업소 코드에 해당하는 roominfo 테이블의 방 정보를 모두 가져옴
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);

			rs1 = pstmt.executeQuery();
			while (rs1.next()) { //roominfo 테이블의 방 정보가 계속 있을 동안 반복
				roomDTO roomDTO = new roomDTO();

				for (int i = 0; i < diff; i++) { //diff(예약종료일 - 예약시작일)만큼 반복문 실행
					int room_cnt = 0; //변수 : room_reservation 테이블(방 매진을 구하기 위한 테이블)에서 room_count 컬럼(남은 방 갯수)과 비교하기 위해서

					sdate = dateformat.parse(startdate);
						
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(sdate);
					cal1.add(Calendar.DATE, i);

					String room_startdate = dateformat.format(cal1.getTime());

					String room_name = rs1.getString("room_name"); //rs1에 있는 room_name(방 이름)을 room_name 변수에 저장
					int room_count = rs1.getInt("room_count"); //rs1에 있는 room_count(남은 방 갯수)을 room_count 변수에 저장

					sql = "select * from room_reservation where accom_code=? and room_name=? and reser_startdate=?";
					//room_reservation 테이블에서 accom_code(숙박 업소 코드), room_name(방 이름), reser_startdate(예약 시작일) 조건에 만족하는 결과를 가져옴
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, accom_code);
					pstmt.setString(2, room_name);
					pstmt.setString(3, room_startdate);

					rs2 = pstmt.executeQuery();
					
					while (rs2.next()) { //rs2의 결과값이 나온 만큼 반복
						room_cnt++; //결과값만큼 room_cnt 변수가 증가됨
					} //while end

					if (room_cnt >= room_count) { //만약 room_cnt(예약된 방의 갯수)가 room_count(남은 방 갯수)보다 크거나 같으면
						roomDTO.setRoom_count(0); //room_count(남은 방 갯수)는 0이다.
						break; //for문 종료
					} else { //만약 room_cnt(예약된 방의 갯수)가 room_count(남은 방 갯수)보다 작으면
						roomDTO.setRoom_count(room_count); //room_count(남은 방 갯수)는 그대로이다.
					}//else end

				}//for end

				roomDTO.setAccom_code(rs1.getString("accom_code"));
				roomDTO.setRoom_type(rs1.getString("room_type"));
				roomDTO.setRoom_name(rs1.getString("room_name"));
				roomDTO.setRentable_price(rs1.getInt("rentable_price"));
				roomDTO.setAccom_price(rs1.getInt("accom_price"));
				roomDTO.setRoom_capa(rs1.getInt("room_capa"));
				roomDTO.setRoom_image(rs1.getString("room_image"));

				list.add(roomDTO);
			}//while end

		} catch (Exception ex1) {
			System.out.println("getRoomList() 예외 : " + ex1);

		} finally {
			try {
				if (rs2 != null) {
					rs2.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {}

		}//finally end

		return list;
	}//getRoomList() end
	
	
	//관리자 방 정보 리스트 가져오기
	public List getAdminRoomList(String accom_code) throws Exception{
		List<roomDTO> list = new ArrayList<roomDTO>();
		
		try{
			con = dao.getCon();
			sql = "select * from roominfo where accom_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				roomDTO roomDto = new roomDTO();
				
				roomDto.setAccom_code(rs.getString("accom_code"));
				roomDto.setRoom_type(rs.getString("room_type"));
				roomDto.setRoom_name(rs.getString("room_name"));
				roomDto.setRentable_price(rs.getInt("rentable_price"));
				roomDto.setAccom_price(rs.getInt("accom_price"));
				roomDto.setRoom_capa(rs.getInt("room_capa"));
				roomDto.setRoom_count(rs.getInt("room_count"));
				roomDto.setRoom_image(rs.getString("room_image"));
				
				list.add(roomDto);
			}
			
		} catch(Exception ex){
			System.out.println("getAdminRoomList() 예외 : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return list;
		
	} //getAdminRoomList()	
	
	
	//관리자 방 정보 수정하기 폼
		public List adminRoomUpdateForm(String accom_code, String room_type) throws Exception{
			List<roomDTO> list = new ArrayList<roomDTO>();
			
			try {
				con = getCon();
				sql = "select * from roominfo where accom_code=? and room_type=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, accom_code);
				pstmt.setString(2, room_type);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					roomDTO roomDto = new roomDTO();
					
					roomDto.setAccom_code(rs.getString("accom_code"));
					roomDto.setRoom_type(rs.getString("room_type"));
					roomDto.setRoom_name(rs.getString("room_name"));
					roomDto.setRentable_price(rs.getInt("rentable_price"));
					roomDto.setAccom_price(rs.getInt("accom_price"));
					roomDto.setRoom_capa(rs.getInt("room_capa"));
					roomDto.setRoom_count(rs.getInt("room_count"));
					roomDto.setRoom_image(rs.getString("room_image"));
					
					list.add(roomDto);
				}
				
				
			} catch (Exception ex1) {
				System.out.println("adminRoomUpdateForm() 예외 : " + ex1);
			
			} finally {
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception ex2) {}
				
			}
			
			return list;
		} //adminRoomUpdateForm()	
	
	
	
	
	//관리자 방 정보 수정하기 로직
	public String adminRoomUpdatePro(HttpServletRequest req) throws Exception{
		String code = "";
		int count = -100;
		
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			int size = 5 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(req, upload_Dir, size, "utf-8", new DefaultFileRenamePolicy());
			
			
			sql = "select room_name from roominfo where accom_code=? and room_type=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("accom_code"));
			pstmt.setString(2, multi.getParameter("room_type"));
			
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				count = 1;
			} else {
				count = -1;
				if(multi.getFilesystemName("room_image")==null){
					sql = "update roominfo set room_name=?,rentable_price=?,accom_price=?,room_capa=?,"
							+ "room_count=? where accom_code=? and room_type=?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, multi.getParameter("room_name"));
					pstmt.setString(2, multi.getParameter("rentable_price"));
					pstmt.setString(3, multi.getParameter("accom_price"));
					pstmt.setString(4, multi.getParameter("room_capa"));
					pstmt.setString(5, multi.getParameter("room_count"));
					pstmt.setString(6, multi.getParameter("accom_code"));
					pstmt.setString(7, multi.getParameter("room_type"));
					
				} else {
					//이미지 파일이 있으면
					//먼저 이미지 파일을 삭제한다(ready.gif 파일)
					String im = multi.getParameter("accom_code");
					String im2 = multi.getParameter("room_type");
					String sql2 = "select room_image from roominfo where accom_code='"+im+"' and room_type='" + im2 + "'";
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql2);
					
					if(rs.next()){ //이미지가 존재하면
						File f = new File(upload_Dir+rs.getString("room_image"));
						if(f.isFile()){ //파일이 존재하면 삭제
							f.delete();
						}//if end
					}//if end
					stmt.close();
					rs.close();
					// 이미지 파일 삭제 끝
					
					//update
					sql = "update roominfo set room_name=?,rentable_price=?,accom_price=?,room_capa=?,"
							+ "room_count=?, room_image=? where accom_code=? and room_type=?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, multi.getParameter("room_name"));
					pstmt.setString(2, multi.getParameter("rentable_price"));
					pstmt.setString(3, multi.getParameter("accom_price"));
					pstmt.setString(4, multi.getParameter("room_capa"));
					pstmt.setString(5, multi.getParameter("room_count"));
					pstmt.setString(6, multi.getFilesystemName("room_image"));
					pstmt.setString(7, multi.getParameter("accom_code"));
					pstmt.setString(8, multi.getParameter("room_type"));
					
				}
				
				code = multi.getParameter("accom_code");
				pstmt.executeUpdate();
			}
			
			
		} catch(Exception ex){
			System.out.println("adminRoomUpdatePro() 예외 : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return code;
		
	} //adminRoomUpdatePro()	
	
	
	
	//관리자 방 정보 삭제하기 로직
	public void adminRoomDelete(HttpServletRequest req, String accom_code, String room_type) throws Exception{
		
		try {
			con = getCon(); // 커넥션 얻기
			
			// 이미지 파일 삭제 하기
			String sql2 = "select room_image from roominfo where accom_code='" + accom_code +"' and room_type='" + room_type + "'"; 
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2); //실행시 인자가 들어감
			
			if(rs.next()){
				File f = new File(upload_Dir + rs.getString("room_image"));
				if(f.isFile()){
					f.delete(); //이미지 파일 삭제
				} //if end
			} //if end
			
			rs.close();
			stmt.close();
			//이미지 파일 삭제 끝
			
			//DB roominfo 테이블 레코드 삭제
			sql = "delete from roominfo where accom_code=? and room_type=?";
			pstmt = con.prepareStatement(sql); //생성시 인자가 들어감
			pstmt.setString(1, accom_code);
			pstmt.setString(2, room_type);
			
			pstmt.executeUpdate(); // 쿼리 실행
			
			
		} catch (Exception ex) {
			System.out.println("adminRoomDelete() 예외 : " + ex);
		} finally {
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}// finally end
		
	}//adminRoomDelete() end	
	
	
	//관리자 방 정보 추가하기 로직
	public int adminRoomInsert(HttpServletRequest req, String accom_code) throws Exception{
		int count = -100;
		
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/"); //실제 경로
			String upload_Dir = real_path + "/img/"; //상품 등록할때 이미지 업로드 경로
			
			MultipartRequest multi = new MultipartRequest(req, upload_Dir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			String sql = "";
			
			sql = "select room_name from roominfo where accom_code=? and room_type=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			pstmt.setString(2, multi.getParameter("room_type"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = 1;
			} else {
				count = -1;
				
				sql = "insert into roominfo(accom_code,room_name,room_type,rentable_price,accom_price,room_capa,"
						+ "room_count,room_image) values(?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql); //생성시 인자 들어감
				
				pstmt.setString(1, accom_code);
				pstmt.setString(2, multi.getParameter("room_name"));
				pstmt.setString(3, multi.getParameter("room_type"));
				pstmt.setString(4, multi.getParameter("rentable_price"));
				pstmt.setString(5, multi.getParameter("accom_price"));
				pstmt.setString(6, multi.getParameter("room_capa"));
				pstmt.setString(7, multi.getParameter("room_count"));
				
				if(multi.getFilesystemName("room_image")!=null){ //이미지 파일이 있을때
					pstmt.setString(8, multi.getFilesystemName("room_image"));
				} else { //이미지 파일이 없을때
					pstmt.setString(8, "ready.gif");
				} //else end
				
				pstmt.executeUpdate(); //쿼리 실행
			}
			
		} catch (Exception ex) {
			System.out.println("adminRoomInsert() 예외 : " + ex);
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
			} catch (Exception ex2) {}
			
		}// finally end
		
		return count;
		
	}//adminRoomInsert() end	
	
	
}
