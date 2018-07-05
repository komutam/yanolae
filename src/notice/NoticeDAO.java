package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class NoticeDAO {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	private static NoticeDAO dao= new NoticeDAO();//  싱글톤 만들기
	
	public static NoticeDAO getmemberDao(){
		return dao;
	}
	private NoticeDAO(){}
	
	private Connection getCon() throws Exception {
		Context ct = new InitialContext();
		DataSource ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}
	
	////////////
	/// -- 공지사항 정보 불러오기 ////////
	public List getNoticeListN(int start, int cnt) throws Exception {
		List<NoticeDTO> list = null;
		try {
			con = getCon();
			sql = "select * from notice order by noti_num desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<NoticeDTO>();
				do {
					NoticeDTO dto = new NoticeDTO();
					dto.setNoti_num(rs.getInt("noti_num"));
					dto.setNoti_subject(rs.getString("noti_subject"));
					dto.setNoti_content(rs.getString("noti_content"));
					dto.setNoti_date(rs.getDate("noti_date"));
					list.add(dto);
				} while (rs.next());
			}
		} catch (Exception ex) {
			System.out.println("getList() ::" + ex);
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
		}// finally
		return list;
	}// getList() end--
	
	//----------------------------------------------
	// 글 갯수 
	//---------------------------------------------
	
	public int getnoticeCount() throws Exception {
		int x = 0;

		try {
			con = getCon();
			pstmt = con.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);// 글 개수
			}
		} catch (Exception ex) {
			System.out.println("getnoticeCount ::" + ex);
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
		}// / finally
		return x;
	}// getArticle_end
	
	// ---------------------------------
		// 글내용보기
		// ----------------------------------
		public NoticeDTO getNotice(int num) throws Exception {
			NoticeDTO dto = null;
			try {
				con = getCon();
				
				// 글내용 보기
				pstmt = con.prepareStatement("select * from notice where noti_num=?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					dto = new NoticeDTO();

					dto.setNoti_num(rs.getInt("noti_num"));
					dto.setNoti_content(rs.getString("noti_content"));
					dto.setNoti_subject(rs.getString("noti_subject"));
					dto.setNoti_date(rs.getDate("noti_date"));
					
				}
			} catch (Exception e) {
				System.out.println("getNotice::" + e);
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
			}
			return dto;
		}// getArticle end--
		
		//---------------------------------
		// 글 삭제 
		//------------------------------------
		public int noticedelete(int num) throws Exception{
			int x = -100;
			try{
				con = getCon();
						pstmt=con.prepareStatement("delete from notice where noti_num="+num);
						pstmt.executeUpdate();//쿼리실행
						x=1;
			}catch(Exception ex){
				System.out.println("deleteArticle::"+ex);
			}finally{
				try{
					if(rs != null){rs.close();}
					if(pstmt !=null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception e){
					
				}
			}
			return x;
				
				//글삭제				
			
		}
		
		//--------------------------
		// 글 수정 
		//---------------------------
		public NoticeDTO updateGETnotice(int num) throws Exception{
			NoticeDTO dto = null;
			try{
				con = getCon();
				pstmt=con.prepareStatement("select * from notice where noti_num=?");
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				if(rs.next()){
					dto = new NoticeDTO();
					
					dto.setNoti_num(rs.getInt("noti_num"));
					dto.setNoti_subject(rs.getString("noti_subject"));
					dto.setNoti_date(rs.getDate("noti_date"));
					dto.setNoti_content(rs.getString("noti_content"));
				}
							
			}catch(Exception e){
				System.out.println("updateGETnotice:"+e);
			}finally{
				try{
					if(rs != null){rs.close();}
					if(pstmt !=null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception e){
					
				}
			}			
			return dto;
		}// update
		
		//---------------------------------
		//  DB에 업데이트 
		//----------------------------------
		
		public int updateNotice(NoticeDTO dto) throws Exception{
			int x=-100;
			try{
				con = getCon();
				String sql="update notice set noti_subject=?,noti_content=? where noti_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getNoti_subject());
				pstmt.setString(2, dto.getNoti_content());
				pstmt.setInt(3, dto.getNoti_num());
				
				pstmt.executeUpdate();
				
				x=1;				
				
			}catch(Exception e){
				System.out.println("updateNotice::"+e);
			}finally{
				try{					
					if(pstmt !=null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception e){
					
				}
			}
			
			
			return x;
		}
	//--------------------------------------
		//  DB에 글쓰기
		//----------------------------
	public void insertnotice(NoticeDTO dto) throws Exception{
		try{
			con=getCon();
			String sql="insert into notice (noti_subject,noti_content,noti_date)"
					+ " values(?,?,NOW())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,dto.getNoti_subject());
			pstmt.setString(2, dto.getNoti_content());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("insertnotice예외="+e);
		}finally{
			try {
				
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
			}
		}// / finally
		
	}// insert
	
	// 공지사항 글 전체보기
	// ----------------------------------
	public List getTotalNotice() throws Exception {
		ArrayList<NoticeDTO> list = null;
		NoticeDTO dto = null;
		try {
			con = getCon();

			// 글내용 보기
			pstmt = con.prepareStatement("select * from notice order by noti_num desc");
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeDTO>();
			while(rs.next()) {
				
				dto = new NoticeDTO();				
				dto.setNoti_num(rs.getInt("noti_num"));
				dto.setNoti_content(rs.getString("noti_content"));
				dto.setNoti_subject(rs.getString("noti_subject"));
				dto.setNoti_date(rs.getDate("noti_date"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getNotice::" + e);
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
	}// getArticle end--
	
}//class NoticeDAO
