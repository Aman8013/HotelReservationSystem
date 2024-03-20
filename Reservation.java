import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Reservation {
    private final Connection connection;
    private final Scanner scanner;
    Reservation(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    void reserve(){
         try{
             System.out.println("Enter Name");
             String name=scanner.next();
             System.out.println("Enter Room no.");
             int room=scanner.nextInt();
             System.out.println("Enter Contact Number");
             String contact=scanner.next();
             String query="INSERT INTO reservation(guest_name,room_number,contact_number)" +
                      "VALUES('"+ name + "',"+ room+  ",'"+contact+"')";
             try(Statement statement=connection.createStatement()) {
                 int rows = statement.executeUpdate(query);
                 if (rows > 0) {
                     System.out.println("Room reserved Successfully");
                 } else {
                     System.out.println("Failed to Reserve");
                 }
             }catch (Exception e){
                 System.out.println(e.getMessage());
             }
         }
         catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
    void viewReservation(){
         String query="Select * from reservation";
         try(Statement statement=connection.createStatement()){
             ResultSet resultSet=statement.executeQuery(query);
             System.out.println("ID"+"    "+"Name"+"      "+"Room No."+"      "+"Contact No."+"      "+"Reservation Date");
             while(resultSet.next()){
                 int id=resultSet.getInt("reservation_id");
                 String name=resultSet.getString("guest_name");
                 int roomNo=resultSet.getInt("room_number");
                 String contact=resultSet.getString("contact_number");
                 String date=resultSet.getDate("reservation_date").toString();
                 System.out.println(id+"    "+name+"      "+roomNo+"          "+contact+"        "+date);
             }
             System.out.println();
             System.out.println();
             System.out.println();
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
    void getRoom() {
        System.out.println("Enter the ID");
        int id = scanner.nextInt();
        String query = "Select * from reservation where reservation_id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("ID" + "    " + "Name" + "    " + "Room No." + "    " + "Contact No." + "    " + "Reservation Date");
                String name = resultSet.getString("guest_name");
                int roomNo = resultSet.getInt("room_number");
                String contact = resultSet.getString("contact_number");
                String date = resultSet.getDate("reservation_date").toString();
                System.out.println(id + "    " + name + "    " + roomNo + "        " + contact + "       " + date);
            } else {
                System.out.println("Invalid ID Number");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void deleteReservation(){
        try {
            System.out.println("Enter the ID");
            int id = scanner.nextInt();
            if(!reservationExist(connection,id)){
                System.out.println("Reservation not found");
                return;
            }
            String query = "Delete from reservation where reservation_id=" + id;
            try (Statement statement = connection.createStatement()) {
                int rows = statement.executeUpdate(query);
                if (rows > 0) {
                    System.out.println("Reservation Deleted Successfully");
                } else {
                    System.out.println("Failed to Delete Reservation");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
    }
    static boolean reservationExist(Connection connection,int id){
        String query = "Select * from reservation where reservation_id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
