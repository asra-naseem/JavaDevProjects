import java.sql.*;
import java.util.Scanner;

public class HandleTransactions {
    //load drivers
    private String userName = "root";
    private String password ="asranaseem";
    private String url ="jdbc:mysql://localhost:3306/AccountInfo";
    private String driverClass = "com.mysql.cj.jdbc.Driver";

   public void HandleTransactions() {

           try {
               Class.forName(driverClass);
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }

       }

//
public void startTransaction() throws SQLException {
    try (Connection conn = DriverManager.getConnection(url, userName, password);
         PreparedStatement debitPS = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
         PreparedStatement creditPS = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
         Scanner sc = new Scanner(System.in)) {

        conn.setAutoCommit(false);

        System.out.println("\nEnter amount and account id from which Debit Transaction should take place");
        double debitAmount = sc.nextDouble();
        int debitId = sc.nextInt();

        System.out.println("\nEnter amount and account id to which Credit Transaction should take place");
        double creditAmount = sc.nextDouble();
        int creditId = sc.nextInt();

        if (isSufficientBalance(conn, debitId, debitAmount)) {
            debitPS.setDouble(1, debitAmount);
            debitPS.setInt(2, debitId);
            debitPS.executeUpdate();

            creditPS.setDouble(1, creditAmount);
            creditPS.setInt(2, creditId);
            creditPS.executeUpdate();

            conn.commit();
            System.out.println("Transaction Successful");
        } else {
            conn.rollback();
            System.out.println("Transaction Failed: Insufficient Balance");
        }
    } catch (SQLException e) {
        System.out.println("Transaction Failed: " + e.getMessage());
    }
}

    public boolean isSufficientBalance(Connection conn,int accountNumber , double amount){
       String Query = "SELECT balance from accounts where id=?";
           try {
               PreparedStatement preparedStatement = conn.prepareStatement(Query);
               preparedStatement.setInt(1,accountNumber);
               ResultSet rs = preparedStatement.executeQuery();
               while(rs.next()){
                   double availableBalance= rs.getDouble("balance");
                   if(availableBalance>=amount)
                       return true;
                   else
                       return false;

               }

               preparedStatement.close();

           }


           catch (SQLException e) {
               throw new RuntimeException(e);
           }


           return false;
       }
   }

