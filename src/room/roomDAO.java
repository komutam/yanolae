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

	//Ŀ�ؼ� Ǯ ���
	public Connection getCon() throws Exception {
		Context ct = new InitialContext();
		DataSource ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();

	}

	
	
	//���� ���� ��޺� �� ����Ʈ ��������
	public List getRoomDetailList(String accom_code, String room_type, int diff) throws Exception {
		List<roomDTO> list = new ArrayList<roomDTO>();
		
		try {
			con = getCon();
			sql = "select * from roominfo where accom_code=? and room_type=?";
			//�� ������ ���� ���� �ڵ忡 �ش��ϴ� roominfo ���̺��� �� ������ ������
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
				roomDTO.setAccom_price(rs.getInt("accom_price") * diff); //diff��ŭ ���� ������ ������(diff = ���� ������ - ���� ������)
				roomDTO.setRoom_capa(rs.getInt("room_capa"));
				roomDTO.setRoom_count(rs.getInt("room_count"));
				roomDTO.setRoom_image(rs.getString("room_image"));

				list.add(roomDTO);
			}//while end

		} catch (Exception ex1) {
			System.out.println("getRoomDetailList() ���� : " + ex1);

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
	
	
	
	// ���� ���� �� ����Ʈ ��������(�˻� ��¥�� ���� �� ���� ó��)
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
			//���� ���� �ڵ忡 �ش��ϴ� roominfo ���̺��� �� ������ ��� ������
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);

			rs1 = pstmt.executeQuery();
			while (rs1.next()) { //roominfo ���̺��� �� ������ ��� ���� ���� �ݺ�
				roomDTO roomDTO = new roomDTO();

				for (int i = 0; i < diff; i++) { //diff(���������� - ���������)��ŭ �ݺ��� ����
					int room_cnt = 0; //���� : room_reservation ���̺�(�� ������ ���ϱ� ���� ���̺�)���� room_count �÷�(���� �� ����)�� ���ϱ� ���ؼ�

					sdate = dateformat.parse(startdate);
						
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(sdate);
					cal1.add(Calendar.DATE, i);

					String room_startdate = dateformat.format(cal1.getTime());

					String room_name = rs1.getString("room_name"); //rs1�� �ִ� room_name(�� �̸�)�� room_name ������ ����
					int room_count = rs1.getInt("room_count"); //rs1�� �ִ� room_count(���� �� ����)�� room_count ������ ����

					sql = "select * from room_reservation where accom_code=? and room_name=? and reser_startdate=?";
					//room_reservation ���̺��� accom_code(���� ���� �ڵ�), room_name(�� �̸�), reser_startdate(���� ������) ���ǿ� �����ϴ� ����� ������
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, accom_code);
					pstmt.setString(2, room_name);
					pstmt.setString(3, room_startdate);

					rs2 = pstmt.executeQuery();
					
					while (rs2.next()) { //rs2�� ������� ���� ��ŭ �ݺ�
						room_cnt++; //�������ŭ room_cnt ������ ������
					} //while end

					if (room_cnt >= room_count) { //���� room_cnt(����� ���� ����)�� room_count(���� �� ����)���� ũ�ų� ������
						roomDTO.setRoom_count(0); //room_count(���� �� ����)�� 0�̴�.
						break; //for�� ����
					} else { //���� room_cnt(����� ���� ����)�� room_count(���� �� ����)���� ������
						roomDTO.setRoom_count(room_count); //room_count(���� �� ����)�� �״���̴�.
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
			System.out.println("getRoomList() ���� : " + ex1);

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
	
	
	//������ �� ���� ����Ʈ ��������
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
			System.out.println("getAdminRoomList() ���� : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return list;
		
	} //getAdminRoomList()	
	
	
	//������ �� ���� �����ϱ� ��
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
				System.out.println("adminRoomUpdateForm() ���� : " + ex1);
			
			} finally {
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception ex2) {}
				
			}
			
			return list;
		} //adminRoomUpdateForm()	
	
	
	
	
	//������ �� ���� �����ϱ� ����
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
					//�̹��� ������ ������
					//���� �̹��� ������ �����Ѵ�(ready.gif ����)
					String im = multi.getParameter("accom_code");
					String im2 = multi.getParameter("room_type");
					String sql2 = "select room_image from roominfo where accom_code='"+im+"' and room_type='" + im2 + "'";
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql2);
					
					if(rs.next()){ //�̹����� �����ϸ�
						File f = new File(upload_Dir+rs.getString("room_image"));
						if(f.isFile()){ //������ �����ϸ� ����
							f.delete();
						}//if end
					}//if end
					stmt.close();
					rs.close();
					// �̹��� ���� ���� ��
					
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
			System.out.println("adminRoomUpdatePro() ���� : " + ex);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return code;
		
	} //adminRoomUpdatePro()	
	
	
	
	//������ �� ���� �����ϱ� ����
	public void adminRoomDelete(HttpServletRequest req, String accom_code, String room_type) throws Exception{
		
		try {
			con = getCon(); // Ŀ�ؼ� ���
			
			// �̹��� ���� ���� �ϱ�
			String sql2 = "select room_image from roominfo where accom_code='" + accom_code +"' and room_type='" + room_type + "'"; 
			String real_path = req.getServletContext().getRealPath("/");
			String upload_Dir = real_path + "/img/";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2); //����� ���ڰ� ��
			
			if(rs.next()){
				File f = new File(upload_Dir + rs.getString("room_image"));
				if(f.isFile()){
					f.delete(); //�̹��� ���� ����
				} //if end
			} //if end
			
			rs.close();
			stmt.close();
			//�̹��� ���� ���� ��
			
			//DB roominfo ���̺� ���ڵ� ����
			sql = "delete from roominfo where accom_code=? and room_type=?";
			pstmt = con.prepareStatement(sql); //������ ���ڰ� ��
			pstmt.setString(1, accom_code);
			pstmt.setString(2, room_type);
			
			pstmt.executeUpdate(); // ���� ����
			
			
		} catch (Exception ex) {
			System.out.println("adminRoomDelete() ���� : " + ex);
		} finally {
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}// finally end
		
	}//adminRoomDelete() end	
	
	
	//������ �� ���� �߰��ϱ� ����
	public int adminRoomInsert(HttpServletRequest req, String accom_code) throws Exception{
		int count = -100;
		
		try {
			con = getCon();
			String real_path = req.getServletContext().getRealPath("/"); //���� ���
			String upload_Dir = real_path + "/img/"; //��ǰ ����Ҷ� �̹��� ���ε� ���
			
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
				
				pstmt = con.prepareStatement(sql); //������ ���� ��
				
				pstmt.setString(1, accom_code);
				pstmt.setString(2, multi.getParameter("room_name"));
				pstmt.setString(3, multi.getParameter("room_type"));
				pstmt.setString(4, multi.getParameter("rentable_price"));
				pstmt.setString(5, multi.getParameter("accom_price"));
				pstmt.setString(6, multi.getParameter("room_capa"));
				pstmt.setString(7, multi.getParameter("room_count"));
				
				if(multi.getFilesystemName("room_image")!=null){ //�̹��� ������ ������
					pstmt.setString(8, multi.getFilesystemName("room_image"));
				} else { //�̹��� ������ ������
					pstmt.setString(8, "ready.gif");
				} //else end
				
				pstmt.executeUpdate(); //���� ����
			}
			
		} catch (Exception ex) {
			System.out.println("adminRoomInsert() ���� : " + ex);
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
