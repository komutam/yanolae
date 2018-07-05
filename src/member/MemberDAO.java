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
	//싱글톤 객체사용, 객체를 하나만 사용하도록 한다.
	//메모리 절약이 된다.
	private static MemberDAO instance=new MemberDAO();// 객체생성
		
	Connection con = null;
	PreparedStatement pstmt=null;
	String sql="";
	ResultSet rs=null;
		
	//jsp에서 객체를 얻을때는 : MemberDAO.getInstance()
	public static MemberDAO getDAO() throws Exception{
		return instance;
	}
		
	private MemberDAO(){}//private 생성자 : 외부에서 객체생성 못하게
	
	//커넥션 풀 사용.
	public Connection getCon() throws Exception{
		Context ct = new InitialContext(); //Context를 만들어서 
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql"); //이 InitialContext를 중심으로 리소스를 찾게 만든다.
		//그리고 DataSource로 실제 소스를 발견하게 되면 내 프로젝트에 실제로 접근하게 만든다.
		return ds.getConnection();//context.xml에 설정했던 mysql주소에 접근하게 만들기
	}//getCon() end
	
	
	//회원가입 insert
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
				System.out.println("insertMember() 예외 : "+ex);
			}finally{
				try{
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}//finally end
		}//insert end
	
	
	//유저 아이디가 존재하는지의 여부를 체크하는 함수
		public int insertCheck(String id) throws Exception{
			int x=-100;
			try{
				con=getCon();//컨넥션 얻기
				String sql = "select id from memberinfo where id=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next() ){
					x=1; //이미 존재하는 회원이면 1반환
					System.out.println("id="+id+" x="+x);
				}else{
					x=-1; //가입 가능한 회원은 -1반환
					System.out.println("id="+id+" x="+x);
				}
			}catch(Exception ex){
				System.out.println("insertCheck() 예외"+ex);
			}finally{
				try{
					if(rs != null){rs.close();} //rs가 널이 아니면 닫아주도록 한다.
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}
			return x; //DB오류
		}//insertCheck end
	
//----------------------------------------
//	로그인
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
					if(passwd.equals(dbPasswd)){//결과에 아이디값을 받아서 접속을 시도한 passwd와 동일하다면
						x=1; //1을 반환해서 로그인 성공
					}else{//아닐 경우 0을 반환해서 비번 불일치 알림
						x=0;
					}
				}else{//아닐경우 -1을 반환해서 아이디가 없음을 알린다.
					x=-1;
				}
			}catch(Exception ex){
				System.out.println("memberLogin() 예외 : "+ex);
			}finally{
				try{
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}//finally end
			return x; //데이터베이스 오류
		}//memberLogin end
		//=========================================================================================
		
		//회원 정보 수정을 위한 id로 정보 검색하기  findMemberK ()
		public MemberDTO findMemberK(String id){//회원 정보를 찾는데 id가 primary key이기 때문에 이것을 파라미터 값을 받는다. 
			MemberDTO dto = new MemberDTO() ;// 결과 값을 저장해서 넣어 주려고 한다. 
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
				System.out.println("findMemberK 예외 발생 : " + e);
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
		//회원 정보 수정
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
	//회원 탈퇴 
		
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
	// == 회원정보 가져오기(상범)
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
			System.out.println("memberlist 예외 : "+e);
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
	// 회원정보 갯수 (상범)
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
			System.out.println("getmemberCount 예외"+e);
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
	// 관리자 업데이트 
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
	//  검색//-
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
			System.out.println("getsearchN 예외 : "+e);
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
	//- 검색 갯수  
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
			System.out.println("getmemberCount 예외"+e);
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
