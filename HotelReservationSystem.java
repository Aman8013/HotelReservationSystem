import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class HotelReservationSystem {
   private static final String url="jdbc:mysql://localhost:3306/hotel";
   private static final String username="root";
   private static  final String password="Gaya@123";

    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Scanner scanner=new Scanner(System.in);
            Reservation reservation =new Reservation(connection,scanner);
            while (true){
                System.out.println();
                System.out.println();
                System.out.println("HOTEL RESERVATION SYSTEM");
                System.out.println();
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room No.");
                System.out.println("4. Delete Reservation");
                System.out.println("0. Exit");
                int choice=scanner.nextInt();
                switch (choice) {
                    case 1:
                        reservation.reserve();
                        break;
                    case 2:
                        reservation.viewReservation();
                        break;
                    case 3:
                        reservation.getRoom();
                        break;
                    case 4:
                        reservation.deleteReservation();
                        break;
                    case 0:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        return;
                }
            }
        }catch(SQLException | InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
    public static void exit() throws InterruptedException{
        int i=5;
        System.out.print("Exit ");
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println();
    }
}
