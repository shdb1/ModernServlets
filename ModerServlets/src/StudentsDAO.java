import java.util.*;
import java.sql.*;

public class StudentsDAO {

	public static Connection getConnection(){
		Connection con=null;
		try{
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.postgresql.Driver");
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","password");
			
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Students e){
		int status=0;
		try{
			Connection con=StudentsDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into students(name,password,email,branch) values (?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getBranch());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Students e){
		int status=0;
		try{
			Connection con=StudentsDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("update students set name=?,password=?,email=?,branch=? where id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getBranch());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=StudentsDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from students where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Students getAllStudentsById(int id){
		Students e=new Students();
		
		try{
			Connection con=StudentsDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from students where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setBranch(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Students> getAllStudents(){
		List<Students> list=new ArrayList<Students>();
		
		try{
			Connection con=StudentsDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from students");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Students e=new Students();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setBranch(rs.getString(5));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
