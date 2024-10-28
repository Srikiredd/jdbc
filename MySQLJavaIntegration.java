import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MySQLJavaIntegration{
    //Database crenditials
    String URL = "jdbc:mysql://localhost:3306/movies";
     private static final String USER="root";
     private static final String PASSWORD="2006";
     //Establishes a connection to the database
     public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
     }
     //fetching employee details
     public void fetchEmployees(){
        String query=" employee_id,name,salary,mother,father,account_id";
        try(Connection conn=getConnection();
           PreparedStatement pstmt=conn.prepareStatement(query);
           ResultSet rs=pstmt.executeQuery(query)){
             while(rs.next()){
                int employee_id=rs.getInt("employee_id");
                String name=rs.getString("name");
                int salary=rs.getInt("salary");
                String mother=rs.getString("name");
                String father=rs.getString("name");
                int account_id=rs.getInt("salary");
                System.out.printf("employee_id:%d,name:%s,salary:%d,mother:%s,father:%s,account_id:%d",employee_id,name,salary,mother,father,account_id);
             }

           }catch(SQLException e){
            e.printStackTrace();
           }
     }
     // Adds a new employee
     public void addEmployee(int employee_id,String name,int salary,String mother,String father,int account_id){
        String query="INSERT INTO employees(employee_id,name,salary,mother,father,account_id) VALUES(?,?,?,?,?,?)";
        try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1,employee_id);
            pstmt.setString(2,name);
            pstmt.setInt(3,salary);
            pstmt.setString(4,mother);
            pstmt.setString(5,father);
            pstmt.setInt(6,account_id);
              int rowsAffected=pstmt.executeUpdate();//manam chesina update ni execute cheyamani cheppadaniki upato ginche line laga manam anukovacchu//
              System.out.println(rowsAffected+"row(s) inserted");

        }catch (SQLException e){
            e.printStackTrace();
        }
     }
     //updating an employees salary
     public void updateEmployeesalary(int employee_id,int newsalary){
        String query="UPDATE employees SET salary= ? WHERE employee_id=?";
        try(Connection conn=getConnection();
        PreparedStatement pstmt=conn.prepareStatement(query)){
            pstmt.setInt(1,newsalary);
            pstmt.setInt(2,employee_id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        }catch(SQLException e){
            e.printStackTrace();
        }
     
     }
     //delete an employee detail
     public void deleteEmployee(int employee_id){
        String query="DELETE FROM employee WHERE employee_id=?";
        try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1,employee_id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch(SQLException e){
            e.printStackTrace();
        }
     }
     public static void main(String[] args) {
        MySQLJavaIntegration app = new MySQLJavaIntegration();
        //Fetching all employee details
        System.out.println("Fetching all employees details");
        app.fetchEmployees();
        //Add a new employee
        System.out.println("\nAdding a new employee:");
        app.addEmployee(7,"susmitha",40000,"susila","kumar",256975);
        //fetch all employees after insertion//
        System.out.println("\nFetching all employees after insertion:");
        app.fetchEmployees();
        //update an employee salary
        System.out.println("\nUpdating employee with ID 2:");
        app.updateEmployeesalary(2, 90000);
        //Fetch all employee after update
        System.out.println("\nFetching all employees after the update");
        app.fetchEmployees();
        //Deleting  an employee
        System.out.println("\nDeleting employee with ID 5:");
        app.deleteEmployee(5);
        //fetching all employees after deletion
        System.out.println("\nFetching all employees after deletions");
        app.fetchEmployees();
     }
    
    }
