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
	// �̱��� ��ü���, ��ü�� �ϳ��� ����ϵ��� �Ѵ�.
	// �޸� ������ �ȴ�.
	private static ReviewDAO instance = new ReviewDAO();// ��ü����

	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = "";
	ResultSet rs = null;

	// jsp���� ��ü�� �������� : MemberDAO.getInstance()
	public static ReviewDAO getDAO() throws Exception {
		return instance;
	}

	private ReviewDAO() {
	}// private ������ : �ܺο��� ��ü���� ���ϰ�

	// Ŀ�ؼ� Ǯ ���.
	public Connection getCon() throws Exception {
		Context ct = new InitialContext(); // Context�� ����
		DataSource ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql"); // ��
																			// InitialContext��
																			// �߽�����
																			// ���ҽ���
																			// ã��
																			// �����.
		// �׸��� DataSource�� ���� �ҽ��� �߰��ϰ� �Ǹ� �� ������Ʈ�� ������ �����ϰ� �����.
		return ds.getConnection();// context.xml�� �����ߴ� mysql�ּҿ� �����ϰ� �����
	}// getCon() end

	
	// �� �� list �ҷ����� 
	public List<ReviewDTO> getReview(String accom_code) {
		//����Ʈ ���·� ����
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		try {
			//Ŀ�ؼ� ���
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
				
				// �ִµ��� ����Ʈ ���·� �����ϱ� 
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println("getReview ���� �߻� ����Ʈ �������� ����"+ e);
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

		return list;//����Ʈ ���·� �����ϱ� 
	}//getlist end
	
	
	//���������Ʈ �ϱ� 
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
			System.out.println("reviewUpdate ������Ʈ ���� " + e);
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
	
	
	
	//���� ��� �ϱ�
	public void reviewInsert(ReviewDTO dto){
		try {
			con= getCon();
			sql= "insert into review(accom_code,re_content,id,up_date,starpoint) values(?,?,?,NOW(),?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccom_code());
			//��ȣ�� ��쿡�� �ڵ����� �Է�
			pstmt.setString(2, dto.getRe_content());
			pstmt.setString(3, dto.getId());
			//�ð��� ��쿡�� ���� �ð� �ڵ����� �Է�
			pstmt.setFloat(4, dto.getStarpoint());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("reviewInsert ��۵�� ���� �߻�"+ e);
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
	
	
	//���� ���� 
	public void reviewDelet(int re_num){
		try {
			con= getCon();
			sql= "delete from review where re_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("reviewInsert ��۵�� ���� �߻�"+ e);
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

