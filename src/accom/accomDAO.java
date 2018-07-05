package accom;

import java.io.File;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.swing.ListModel;

import room.roomDTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class accomDAO {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	
	
	private static accomDAO dao = new accomDAO();
	
	
	public static accomDAO getDAO(){
		return dao;
	}
	
	
	//커넥션 풀 얻기
	public Connection getCon() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
		
	}
	
	
	//숙박 업소 정보 검색해서 가져오기
	public List getAccomList(String accom_kind, String accom_location, int start, int cnt) throws Exception{
		List<accomDTO> list = new ArrayList<accomDTO>();
		
		try {
			con = getCon();
			sql = "select * from accominfo where accom_kind=? and accom_location=? limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_kind);
			pstmt.setString(2, accom_location);
			pstmt.setInt(3, start-1);
			pstmt.setInt(4, cnt);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				accomDTO accomDTO = new accomDTO();
				accomDTO.setAccom_name(rs.getString("accom_name"));
				accomDTO.setAccom_code(rs.getString("accom_code"));
				accomDTO.setAccom_addr(rs.getString("accom_addr"));
				accomDTO.setAccom_location(rs.getString("accom_location"));
				accomDTO.setAccom_number(rs.getString("accom_number"));
				accomDTO.setAccom_resertime(rs.getString("accom_resertime"));
				accomDTO.setAccom_kind(rs.getString("accom_kind"));
				accomDTO.setAccom_image(rs.getString("accom_image"));
				
				list.add(accomDTO);
			}
			
			
		} catch (Exception ex1) {
			System.out.println("getAccomList() 예외 : " + ex1);
		
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
			
		}
		
		return list;
	}
	
	
	//검색한 숙박 업소의 갯수 가져오기
	public int getAccomCount(String accom_kind, String accom_location) throws Exception{
		int count = 0;
		try {
			con = getCon();
			pstmt = con.prepareStatement("select count(*) from accominfo where accom_kind=? and accom_location=?");
			//검색 창으로 선택한 숙박 업소 종류, 숙박 업소 지역에 해당하는 accominfo 테이블의 숙박 업소의 갯수를 가져옴
			pstmt.setString(1, accom_kind);
			pstmt.setString(2, accom_location);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (Exception ex) {
			System.out.println("getAccomCount() 예외 : " + ex);
		} finally {
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(con!=null){con.close();}
		}//finally
		
		return count;
	}
	
	
	//해당 하는 숙박 업소 정보 전부 가져오기
	public List getAccomDetailList(String accom_code) throws Exception {
		List<accomDTO> list = new ArrayList<accomDTO>();

		try {
			con = getCon();
			sql = "select * from accominfo where accom_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				accomDTO accomDTO = new accomDTO();
				accomDTO.setAccom_name(rs.getString("accom_name"));
				accomDTO.setAccom_code(rs.getString("accom_code"));
				accomDTO.setAccom_addr(rs.getString("accom_addr"));
				accomDTO.setAccom_location(rs.getString("accom_location"));
				accomDTO.setAccom_number(rs.getString("accom_number"));
				accomDTO.setAccom_resertime(rs.getString("accom_resertime"));
				accomDTO.setAccom_kind(rs.getString("accom_kind"));
				accomDTO.setAccom_image(rs.getString("accom_image"));

				list.add(accomDTO);
			}

		} catch (Exception ex1) {
			System.out.println("getAccomDetailList() 예외 : " + ex1);

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

		}

		return list;
	}
	
	
	
	//관리자 숙박 업소 갯수 가져오기
		public int getAdminAccomCount() throws Exception{
			int count = 0;
			try {
				con = getCon();
				pstmt = con.prepareStatement("select count(*) from accominfo");
				//accominfo 테이블의 숙박 업소의 갯수를 가져옴
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt(1);
				}
				
			} catch (Exception ex) {
				System.out.println("getAdminAccomCount() 예외 : " + ex);
			} finally {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}//finally
			
			return count;
		}//getAdminAccomCount() 
	
	
	
	
	
	//관리자 숙박 업소 정보 가져오기
		public List getAdminAccomList(int start, int cnt) throws Exception{
			List<accomDTO> list = new ArrayList<accomDTO>();
			
			try {
				con = getCon();
				sql = "select * from accominfo order by accom_code limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, cnt);
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					accomDTO accomDTO = new accomDTO();
					accomDTO.setAccom_name(rs.getString("accom_name"));
					accomDTO.setAccom_code(rs.getString("accom_code"));
					accomDTO.setAccom_addr(rs.getString("accom_addr"));
					accomDTO.setAccom_location(rs.getString("accom_location"));
					accomDTO.setAccom_number(rs.getString("accom_number"));
					accomDTO.setAccom_resertime(rs.getString("accom_resertime"));
					accomDTO.setAccom_kind(rs.getString("accom_kind"));
					accomDTO.setAccom_image(rs.getString("accom_image"));
					
					list.add(accomDTO);
				}
				
				
			} catch (Exception ex1) {
				System.out.println("getAdminAccomList() 예외 : " + ex1);
			
			} finally {
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception ex2) {}
				
			}
			
			return list;
		} //getAdminAccomList()
	
		
	//관리자 숙박 업소 정보 수정하기 폼
	public List adminAccomUpdateForm(String accom_code) throws Exception{
		List<accomDTO> list = new ArrayList<accomDTO>();
		
		try{
			con = dao.getCon();
			sql = "select * from accominfo where accom_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				accomDTO accomDto = new accomDTO();
				
				accomDto.setAccom_name(rs.getString("accom_name"));
				accomDto.setAccom_code(rs.getString("accom_code"));
				accomDto.setAccom_addr(rs.getString("accom_addr"));
				accomDto.setAccom_location(rs.getString("accom_location"));
				accomDto.setAccom_number(rs.getString("accom_number"));
				accomDto.setAccom_resertime(rs.getString("accom_resertime"));
				accomDto.setAccom_kind(rs.getString("accom_kind"));
				accomDto.setAccom_image(rs.getString("accom_image"));
				
				list.add(accomDto);
			}
			
		} catch(Exception ex){
			System.out.println("adminAccomUpdateForm() 예외 : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return list;
		
	} //adminAccomUpdate()
	
	

	
	//관리자 숙박 업소 정보 수정하기 로직
	public void adminAccomUpdate(HttpServletRequest req) throws Exception{
		
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			int size = 5 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(req, upload_Dir, size, "utf-8", new DefaultFileRenamePolicy());

			
			if (multi.getFilesystemName("accom_image") == null) {
				// 이미지 파일이 없으면
				sql = "update accominfo set accom_name=?,accom_addr=?,"
						+ "accom_location=?,accom_number=?,accom_resertime=?,accom_kind=? where accom_code=?";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, multi.getParameter("accom_name"));
				pstmt.setString(2, multi.getParameter("accom_addr"));
				pstmt.setString(3, multi.getParameter("accom_location"));
				pstmt.setString(4, multi.getParameter("accom_number"));
				pstmt.setString(5, multi.getParameter("accom_resertime"));
				pstmt.setString(6, multi.getParameter("accom_kind"));
				pstmt.setString(7, multi.getParameter("accom_code"));

			} else {
				// 이미지 파일이 있으면
				// 먼저 이미지 파일을 삭제한다(ready.gif 파일)
				String im = multi.getParameter("accom_code");
				String sql2 = "select accom_image from accominfo where accom_code='"
						+ im + "'";

				stmt = con.createStatement();
				rs = stmt.executeQuery(sql2);

				if (rs.next()) { // 이미지가 존재하면
					File f = new File(upload_Dir + rs.getString("accom_image"));
					if (f.isFile()) { // 파일이 존재하면 삭제
						f.delete();
					}// if end
				}// if end
				stmt.close();
				rs.close();
				// 이미지 파일 삭제 끝

				// update

				sql = "update accominfo set accom_name=?,accom_addr=?,"
						+ "accom_location=?,accom_number=?,accom_resertime=?,accom_kind=?,accom_image=? where accom_code=?";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, multi.getParameter("accom_name"));
				pstmt.setString(2, multi.getParameter("accom_addr"));
				pstmt.setString(3, multi.getParameter("accom_location"));
				pstmt.setString(4, multi.getParameter("accom_number"));
				pstmt.setString(5, multi.getParameter("accom_resertime"));
				pstmt.setString(6, multi.getParameter("accom_kind"));
				pstmt.setString(7, multi.getFilesystemName("accom_image"));
				pstmt.setString(8, multi.getParameter("accom_code"));

			}// else end

			pstmt.executeUpdate(); // 쿼리 실행
				
			
		} catch (Exception ex) {
			System.out.println("adminAccomUpdate() 예외 : " + ex);
		} finally {
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}// finally end
		
		
	}//adminAccomUpdate() end
	
	
	
	//관리자 숙박 업소 정보 삭제하기 로직
	public void adminAccomDelete(HttpServletRequest req, String accom_code) throws Exception{
		
		try {
			con = getCon(); // 커넥션 얻기
			
			// 이미지 파일 삭제 하기
			String sql2 = "select accom_image from accominfo where accom_code='" + accom_code +"'";
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2); //실행시 인자가 들어감
			
			if(rs.next()){
				File f = new File(upload_Dir + rs.getString("accom_image"));
				if(f.isFile()){
					f.delete(); //이미지 파일 삭제
				} //if end
			} //if end
			
			rs.close();
			stmt.close();
			//accominfo 테이블 이미지 파일 삭제 끝
			sql = "select room_image from roominfo where accom_code='" + accom_code + "'";
			real_path = req.getServletContext().getRealPath("/");
			upload_Dir = real_path + "/img/";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql); //실행시 인자가 들어감
			
			while(rs.next()){
				File f = new File(upload_Dir + rs.getString("room_image"));
				if(f.isFile()){
					f.delete(); //이미지 파일 삭제
				} //if end
			}//while end
			
			rs.close();
			stmt.close();
			//roominfo 테이블 이미지 파일 삭제 끝
			
			sql = "delete from roominfo where accom_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			//DB accominfo 테이블 레코드 삭제
			sql = "delete from accominfo where accom_code=?";
			pstmt = con.prepareStatement(sql); //생성시 인자가 들어감
			pstmt.setString(1, accom_code);
			
			pstmt.executeUpdate(); // 쿼리 실행
			
		} catch (Exception ex) {
			System.out.println("adminAccomDelete() 예외 : " + ex);
		} finally {
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}// finally end
		
	}//adminAccomDelete() end
	
	
	
	//관리자 숙박 업소 정보 추가하기 로직
	public int adminAccomInsert(HttpServletRequest req) throws Exception{
		int count = -100;
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/"); //실제 경로
			String upload_Dir = real_path + "/img/"; //상품 등록할때 이미지 업로드 경로
			
			MultipartRequest multi = new MultipartRequest(req, upload_Dir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			String sql = "";
			
			sql = "select accom_name from accominfo where accom_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("accom_code"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = 1;
			} else {
				count = -1;
				sql = "insert into accominfo(accom_name,accom_code,accom_addr,accom_location,accom_number,accom_resertime,"
						+ "accom_kind,accom_image) values(?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql); //생성시 인자 들어감
				
				pstmt.setString(1, multi.getParameter("accom_name"));
				pstmt.setString(2, multi.getParameter("accom_code"));
				pstmt.setString(3, multi.getParameter("accom_addr"));
				pstmt.setString(4, multi.getParameter("accom_location"));
				pstmt.setString(5, multi.getParameter("accom_number"));
				pstmt.setString(6, multi.getParameter("accom_resertime"));
				pstmt.setString(7, multi.getParameter("accom_kind"));
				
				
				if(multi.getFilesystemName("accom_image")!=null){ //이미지 파일이 있을때
					pstmt.setString(8, multi.getFilesystemName("accom_image"));
				} else { //이미지 파일이 없을때
					pstmt.setString(8, "ready.gif");
				} //else end
				
				pstmt.executeUpdate(); //쿼리 실행
			}
		} catch (Exception ex) {
			System.out.println("adminAccomInsert() 예외 : " + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {}
			
		}// finally end
		
		return count;
		
	}//adminAccomInsert() end
	
	
	//관리자 방 정보 수정하기 폼
	public List adminRoomUpdateForm(String accom_code) throws Exception{
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
			System.out.println("adminRoomUpdateForm() 예외 : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return list;
		
	} //adminRoomUpdateForm()
	
	
	
}
