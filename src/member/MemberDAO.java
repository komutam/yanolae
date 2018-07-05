package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.EscapeTokenizer;

public class MemberDAO {
	//�̱��� ��ü���, ��ü�� �ϳ��� ����ϵ��� �Ѵ�.
	//�޸� ������ �ȴ�.
	private static MemberDAO instance=new MemberDAO();// ��ü����
		
	Connection con = null;
	PreparedStatement pstmt=null;
	String sql="";
	ResultSet rs=null;
		
	//jsp���� ��ü�� �������� : MemberDAO.getInstance()
	public static MemberDAO getDAO() throws Exception{
		return instance;
	}
		
	private MemberDAO(){}//private ������ : �ܺο��� ��ü���� ���ϰ�
	
	//Ŀ�ؼ� Ǯ ���.
	public Connection getCon() throws Exception{
		Context ct = new InitialContext(); //Context�� ���� 
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql"); //�� InitialContext�� �߽����� ���ҽ��� ã�� �����.
		//�׸��� DataSource�� ���� �ҽ��� �߰��ϰ� �Ǹ� �� ������Ʈ�� ������ �����ϰ� �����.
		return ds.getConnection();//context.xml�� �����ߴ� mysql�ּҿ� �����ϰ� �����
	}//getCon() end
	
	
	//ȸ������ insert
		public void insertMember(MemberDTO dto){
			try{
				con = getCon();
				sql = "insert into memberinfo values(?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPasswd());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getPhone());
				pstmt.setString(5, dto.getZipcode());
				pstmt.setString(6, dto.getAddr());
				pstmt.setString(7, dto.getBirth());
				pstmt.setString(8, dto.getEmail());
				pstmt.setTimestamp(9, dto.getRegdate());
				pstmt.setInt(10, dto.getMember_level());

				pstmt.executeUpdate();
				
			}catch(Exception ex){
				System.out.println("insertMember() ���� : "+ex);
			}finally{
				try{
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}//finally end
		}//insert end
	
	
	//���� ���̵� �����ϴ����� ���θ� üũ�ϴ� �Լ�
		public int insertCheck(String id) throws Exception{
			int x=-100;
			try{
				con=getCon();//���ؼ� ���
				String sql = "select id from memberinfo where id=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next() ){
					x=1; //�̹� �����ϴ� ȸ���̸� 1��ȯ
					System.out.println("id="+id+" x="+x);
				}else{
					x=-1; //���� ������ ȸ���� -1��ȯ
					System.out.println("id="+id+" x="+x);
				}
			}catch(Exception ex){
				System.out.println("insertCheck() ����"+ex);
			}finally{
				try{
					if(rs != null){rs.close();} //rs�� ���� �ƴϸ� �ݾ��ֵ��� �Ѵ�.
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}
			return x; //DB����
		}//insertCheck end
	
//----------------------------------------
//	�α���
//---------------------------------
		public int memberLogin(String id, String passwd) throws Exception{
			int x=-100;
			String dbPasswd="";
			try{
				con=getCon();
				pstmt=con.prepareStatement("select * from memberinfo where id=?");
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					dbPasswd=rs.getString("passwd");
					if(passwd.equals(dbPasswd)){//����� ���̵��� �޾Ƽ� ������ �õ��� passwd�� �����ϴٸ�
						x=1; //1�� ��ȯ�ؼ� �α��� ����
					}else{//�ƴ� ��� 0�� ��ȯ�ؼ� ��� ����ġ �˸�
						x=0;
					}
				}else{//�ƴҰ�� -1�� ��ȯ�ؼ� ���̵� ������ �˸���.
					x=-1;
				}
			}catch(Exception ex){
				System.out.println("memberLogin() ���� : "+ex);
			}finally{
				try{
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}//finally end
			return x; //�����ͺ��̽� ����
		}//memberLogin end
		//=========================================================================================
		
		//ȸ�� ���� ������ ���� id�� ���� �˻��ϱ�  findMemberK ()
		public MemberDTO findMemberK(String id){//ȸ�� ������ ã�µ� id�� primary key�̱� ������ �̰��� �Ķ���� ���� �޴´�. 
			MemberDTO dto = new MemberDTO() ;// ��� ���� �����ؼ� �־� �ַ��� �Ѵ�. 
			try {
				con = getCon();
				sql="select * from memberinfo where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					dto.setId(rs.getString("id"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					dto.setZipcode(rs.getString("zipcode"));
					dto.setAddr(rs.getString("addr"));
					dto.setBirth(rs.getString("birth"));
					dto.setEmail(rs.getString("email"));
					dto.setMember_level(rs.getInt("member_level"));
				}
				
			} catch (Exception e) {
				System.out.println("findMemberK ���� �߻� : " + e);
			}finally{
				try {
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				} catch (Exception e2) {
					
				}
				
			}
			return dto;
		}
		//---------------------------------------------------------------------
		//ȸ�� ���� ����
		//---------------------------------------------------------------------
		//updateMemberInfoK()
		public void updateMemberInfoK(MemberDTO dto){
			try {
				con=getCon();
				sql="update memberinfo set passwd=?,name=?,phone=?,zipcode=?,addr=?,birth=?,email=? where id=?";
				pstmt= con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getPasswd());
				pstmt.setString(2, dto.getName());
				pstmt.setString(3, dto.getPhone());
				pstmt.setString(4, dto.getZipcode());
				pstmt.setString(5, dto.getAddr());
				pstmt.setString(6, dto.getBirth());
				pstmt.setString(7, dto.getEmail());
				pstmt.setString(8, dto.getId());
				
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
			
			}finally{
				try {
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	//ȸ�� Ż�� 
		
	public void deleteMemeberK(String id){
		try {
			con= getCon();
			sql ="delete from memberinfo where id=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
		}finally{
			try {
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			} catch (Exception e2) {
			}
		}
	}
		
//====================================================================================
	// == ȸ������ ��������(���)
	//================================================================================
	public List memberlist(int start,int cnt) throws Exception{
		List<MemberDTO> list = null;
		try{
			con=getCon();
			sql="select * from memberinfo limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<MemberDTO>();
				do{
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString("id"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					dto.setZipcode(rs.getString("zipcode"));
					dto.setAddr(rs.getString("addr"));
					dto.setBirth(rs.getString("birth"));
					dto.setEmail(rs.getString("email"));
					dto.setRegdate(rs.getTimestamp("regdate"));
					dto.setMember_level(rs.getInt("member_level"));
					
					list.add(dto);
				}while(rs.next());				
			}// if end;			
		}catch(Exception e){
			System.out.println("memberlist ���� : "+e);
		}finally {
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
	}//memberlist end
	
	//=======================================
	// ȸ������ ���� (���)
	//======================================
	public int getmemberCount() throws Exception{
		int x = 0;
		try {
			con=getCon();
			sql="select count(*) from memberinfo";
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1);
			}			
			
		} catch (Exception e) {
			System.out.println("getmemberCount ����"+e);
		}finally {
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
	}/// getmemberCount end
	//---------------------------------------------------------------
	// ������ ������Ʈ 
	//--------------------------------------------------------------
	public void updateAdminMemberInfoN(MemberDTO dto){
		try {
			con=getCon();
			sql="update memberinfo set passwd=?,name=?,phone=?,zipcode=?,addr=?,birth=?,email=?,member_level=? where id=?";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getAddr());
			pstmt.setString(6, dto.getBirth());
			pstmt.setString(7, dto.getEmail());
			pstmt.setInt(8, dto.getMember_level());
			pstmt.setString(9, dto.getId());			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
		
		}finally{
			try {
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//  �˻�//-
	//----------------------------------------------------------------------
	public List getsearchN(String search, int start,int cnt) throws Exception{
		List<MemberDTO> list = null;
		try{
			con=getCon();
			sql="select * from memberinfo where id like ? or name like ? or phone like ? or addr like ? or birth like ? or email like ? limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, '%'+search+'%');
			pstmt.setString(2, '%'+search+'%');
			pstmt.setString(3, '%'+search+'%');
			pstmt.setString(4, '%'+search+'%');
			pstmt.setString(5, '%'+search+'%');
			pstmt.setString(6, '%'+search+'%');			
			pstmt.setInt(7, start - 1);
			pstmt.setInt(8, cnt);
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<MemberDTO>();
				do{
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString("id"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					dto.setZipcode(rs.getString("zipcode"));
					dto.setAddr(rs.getString("addr"));
					dto.setBirth(rs.getString("birth"));
					dto.setEmail(rs.getString("email"));
					dto.setRegdate(rs.getTimestamp("regdate"));
					dto.setMember_level(rs.getInt("member_level"));
					
					list.add(dto);
				}while(rs.next());				
			}// if end;			
		}catch(Exception e){
			System.out.println("getsearchN ���� : "+e);
		}finally {
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
	}
	//------------------------------------------------
	//- �˻� ����  
	//--------------------------------------------------
	public int getmemberCount(String search) throws Exception{
		int x = 0;
		try {
			con=getCon();
			sql="select count(*) from memberinfo where id like ? or name like ? or phone like ? or addr like ? or birth like ? or email like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, '%'+search+'%');
			pstmt.setString(2, '%'+search+'%');
			pstmt.setString(3, '%'+search+'%');
			pstmt.setString(4, '%'+search+'%');
			pstmt.setString(5, '%'+search+'%');
			pstmt.setString(6, '%'+search+'%');	
			rs= pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1);
			}			
			
		} catch (Exception e) {
			System.out.println("getmemberCount ����"+e);
		}finally {
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
	}/// getmemberCount end
	
	
	
}//class end
