package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.MemberDAO;

public class ReviewDAO {
	// 싱글톤 객체사용, 객체를 하나만 사용하도록 한다.
	// 메모리 절약이 된다.
	private static ReviewDAO instance = new ReviewDAO();// 객체생성

	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = "";
	ResultSet rs = null;

	// jsp에서 객체를 얻을때는 : MemberDAO.getInstance()
	public static ReviewDAO getDAO() throws Exception {
		return instance;
	}

	private ReviewDAO() {
	}// private 생성자 : 외부에서 객체생성 못하게

	// 커넥션 풀 사용.
	public Connection getCon() throws Exception {
		Context ct = new InitialContext(); // Context를 만들어서
		DataSource ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql"); // 이
																			// InitialContext를
																			// 중심으로
																			// 리소스를
																			// 찾게
																			// 만든다.
		// 그리고 DataSource로 실제 소스를 발견하게 되면 내 프로젝트에 실제로 접근하게 만든다.
		return ds.getConnection();// context.xml에 설정했던 mysql주소에 접근하게 만들기
	}// getCon() end

	
	// 댓 글 list 불러오기 
	public List<ReviewDTO> getReview(String accom_code) {
		//리스트 형태로 저장
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		try {
			//커넥션 얻기
			con = getCon();
			sql = "select * from review where accom_code =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accom_code);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReviewDTO dto = new ReviewDTO();
				
				dto.setAccom_code(rs.getString("accom_code"));
				dto.setRe_num(rs.getString("re_num"));
				dto.setRe_content(rs.getString("re_content"));
				dto.setId(rs.getString("id"));
				dto.setUp_date(rs.getDate("up_date"));
				dto.setStarpoint(rs.getFloat("starpoint"));
				
				// 있는동안 리스트 형태로 저장하기 
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println("getReview 예외 발생 리스트 가져오기 실패"+ e);
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
			} catch (Exception e2) {
			}
		}

		return list;//리스트 형태로 저장하기 
	}//getlist end
	
	
	//리뷰업데이트 하기 
	public void reviewUpdate(ReviewDTO dto){
		try {
			con=getCon();
			sql = "update review set accom_code=?,re_content=?,id=?,up_date=NOW(),starpoint=? where re_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getAccom_code());
			pstmt.setString(2, dto.getRe_content());
			pstmt.setString(3, dto.getId());
			pstmt.setFloat(4, dto.getStarpoint());
			pstmt.setString(5, dto.getRe_num());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("reviewUpdate 업데이트 실패 " + e);
		}finally{
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				
			} catch (Exception e2) {
			}
		}
		
	}
	
	
	
	//리뷰 등록 하기
	public void reviewInsert(ReviewDTO dto){
		try {
			con= getCon();
			sql= "insert into review(accom_code,re_content,id,up_date,starpoint) values(?,?,?,NOW(),?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccom_code());
			//번호의 경우에는 자동으로 입력
			pstmt.setString(2, dto.getRe_content());
			pstmt.setString(3, dto.getId());
			//시간의 경우에도 현제 시간 자동으로 입력
			pstmt.setFloat(4, dto.getStarpoint());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("reviewInsert 댓글등록 예외 발생"+ e);
		}finally{
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
			}
		}
	}
	
	
	//리뷰 삭제 
	public void reviewDelet(int re_num){
		try {
			con= getCon();
			sql= "delete from review where re_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("reviewInsert 댓글등록 예외 발생"+ e);
		}finally{
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
			}
		}
	}
	
}// class end

