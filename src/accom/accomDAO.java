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
	
	
	//Ŀ�ؼ� Ǯ ���
	public Connection getCon() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
		
	}
	
	
	//���� ���� ���� �˻��ؼ� ��������
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
			System.out.println("getAccomList() ���� : " + ex1);
		
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
			
		}
		
		return list;
	}
	
	
	//�˻��� ���� ������ ���� ��������
	public int getAccomCount(String accom_kind, String accom_location) throws Exception{
		int count = 0;
		try {
			con = getCon();
			pstmt = con.prepareStatement("select count(*) from accominfo where accom_kind=? and accom_location=?");
			//�˻� â���� ������ ���� ���� ����, ���� ���� ������ �ش��ϴ� accominfo ���̺��� ���� ������ ������ ������
			pstmt.setString(1, accom_kind);
			pstmt.setString(2, accom_location);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (Exception ex) {
			System.out.println("getAccomCount() ���� : " + ex);
		} finally {
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(con!=null){con.close();}
		}//finally
		
		return count;
	}
	
	
	//�ش� �ϴ� ���� ���� ���� ���� ��������
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
			System.out.println("getAccomDetailList() ���� : " + ex1);

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
	
	
	
	//������ ���� ���� ���� ��������
		public int getAdminAccomCount() throws Exception{
			int count = 0;
			try {
				con = getCon();
				pstmt = con.prepareStatement("select count(*) from accominfo");
				//accominfo ���̺��� ���� ������ ������ ������
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt(1);
				}
				
			} catch (Exception ex) {
				System.out.println("getAdminAccomCount() ���� : " + ex);
			} finally {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}//finally
			
			return count;
		}//getAdminAccomCount() 
	
	
	
	
	
	//������ ���� ���� ���� ��������
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
				System.out.println("getAdminAccomList() ���� : " + ex1);
			
			} finally {
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception ex2) {}
				
			}
			
			return list;
		} //getAdminAccomList()
	
		
	//������ ���� ���� ���� �����ϱ� ��
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
			System.out.println("adminAccomUpdateForm() ���� : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return list;
		
	} //adminAccomUpdate()
	
	

	
	//������ ���� ���� ���� �����ϱ� ����
	public void adminAccomUpdate(HttpServletRequest req) throws Exception{
		
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			int size = 5 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(req, upload_Dir, size, "utf-8", new DefaultFileRenamePolicy());

			
			if (multi.getFilesystemName("accom_image") == null) {
				// �̹��� ������ ������
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
				// �̹��� ������ ������
				// ���� �̹��� ������ �����Ѵ�(ready.gif ����)
				String im = multi.getParameter("accom_code");
				String sql2 = "select accom_image from accominfo where accom_code='"
						+ im + "'";

				stmt = con.createStatement();
				rs = stmt.executeQuery(sql2);

				if (rs.next()) { // �̹����� �����ϸ�
					File f = new File(upload_Dir + rs.getString("accom_image"));
					if (f.isFile()) { // ������ �����ϸ� ����
						f.delete();
					}// if end
				}// if end
				stmt.close();
				rs.close();
				// �̹��� ���� ���� ��

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

			pstmt.executeUpdate(); // ���� ����
				
			
		} catch (Exception ex) {
			System.out.println("adminAccomUpdate() ���� : " + ex);
		} finally {
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}// finally end
		
		
	}//adminAccomUpdate() end
	
	
	
	//������ ���� ���� ���� �����ϱ� ����
	public void adminAccomDelete(HttpServletRequest req, String accom_code) throws Exception{
		
		try {
			con = getCon(); // Ŀ�ؼ� ���
			
			// �̹��� ���� ���� �ϱ�
			String sql2 = "select accom_image from accominfo where accom_code='" + accom_code +"'";
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2); //����� ���ڰ� ��
			
			if(rs.next()){
				File f = new File(upload_Dir + rs.getString("accom_image"));
				if(f.isFile()){
					f.delete(); //�̹��� ���� ����
				} //if end
			} //if end
			
			rs.close();
			stmt.close();
			//accominfo ���̺� �̹��� ���� ���� ��
			sql = "select room_image from roominfo where accom_code='" + accom_code + "'";
			real_path = req.getServletContext().getRealPath("/");
			upload_Dir = real_path + "/img/";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql); //����� ���ڰ� ��
			
			while(rs.next()){
				File f = new File(upload_Dir + rs.getString("room_image"));
				if(f.isFile()){
					f.delete(); //�̹��� ���� ����
				} //if end
			}//while end
			
			rs.close();
			stmt.close();
			//roominfo ���̺� �̹��� ���� ���� ��
			
			sql = "delete from roominfo where accom_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			//DB accominfo ���̺� ���ڵ� ����
			sql = "delete from accominfo where accom_code=?";
			pstmt = con.prepareStatement(sql); //������ ���ڰ� ��
			pstmt.setString(1, accom_code);
			
			pstmt.executeUpdate(); // ���� ����
			
		} catch (Exception ex) {
			System.out.println("adminAccomDelete() ���� : " + ex);
		} finally {
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}// finally end
		
	}//adminAccomDelete() end
	
	
	
	//������ ���� ���� ���� �߰��ϱ� ����
	public int adminAccomInsert(HttpServletRequest req) throws Exception{
		int count = -100;
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/"); //���� ���
			String upload_Dir = real_path + "/img/"; //��ǰ ����Ҷ� �̹��� ���ε� ���
			
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
				
				pstmt = con.prepareStatement(sql); //������ ���� ��
				
				pstmt.setString(1, multi.getParameter("accom_name"));
				pstmt.setString(2, multi.getParameter("accom_code"));
				pstmt.setString(3, multi.getParameter("accom_addr"));
				pstmt.setString(4, multi.getParameter("accom_location"));
				pstmt.setString(5, multi.getParameter("accom_number"));
				pstmt.setString(6, multi.getParameter("accom_resertime"));
				pstmt.setString(7, multi.getParameter("accom_kind"));
				
				
				if(multi.getFilesystemName("accom_image")!=null){ //�̹��� ������ ������
					pstmt.setString(8, multi.getFilesystemName("accom_image"));
				} else { //�̹��� ������ ������
					pstmt.setString(8, "ready.gif");
				} //else end
				
				pstmt.executeUpdate(); //���� ����
			}
		} catch (Exception ex) {
			System.out.println("adminAccomInsert() ���� : " + ex);
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
	
	
	//������ �� ���� �����ϱ� ��
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
			System.out.println("adminRoomUpdateForm() ���� : " + ex);
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
