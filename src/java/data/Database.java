/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import bisiness.Account;
import bisiness.Ledger;
import bisiness.Measurement;
import bisiness.Product;
import bisiness.SubAccount;
import bisiness.SubLedger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author BUDGET003
 */
public class Database {
        private static final String url = "jdbc:mysql://localhost:3306/jscorporation?useUnicode=true&characterEncoding=UTF-8";
        private static final String user = "root";
        private static final String pass = "ariba";
        
        private static Connection connection=null;
        private static PreparedStatement ps=null;
        private static String query;
        private static ResultSet rs=null;



    public static String insertIntoUser(String name, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {

        String message = "";

        try
        {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Insert into user (name, password) values (?, ?)";
            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, password);
            ps.executeUpdate();
            
            message = "Saved Successfully!";
            return message;

      }
       catch(SQLException e)
       {
           message = "Please insert unique name and password.";
           return message;
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }

    public static boolean login(String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {


        try
        {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from user where name = ? and password = ?";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();


            return  (rs.next());


        }
        catch(SQLException sql)
        {
            return false;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static void insertIntoAccount(Account account) throws SQLException
    {


       try
       {
               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Insert into Account (AccountName, AccountType) values (?, ?)";
               ps = (PreparedStatement) connection.prepareStatement(query);

               ps.setString(1, account.getAccountName());
               ps.setString(2, account.getAccountType());
               ps.executeUpdate();

      }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static ArrayList<Account> selectAllAccountName()
    {
           ArrayList<Account> list = new ArrayList();

        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from Account order by AccountName";
            ps = (PreparedStatement) connection.prepareStatement(query);
            rs = ps.executeQuery();


            while(rs.next())
            {
                Account account= new Account();


                account.setId(rs.getInt("id"));
                account.setAccountName(rs.getString("accountName"));
                account.setAccountType(rs.getString("accountType"));

                list.add(account);


            }
            return list;

        }
        catch(SQLException sql)
        {
            return list;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static void insertIntoLedger(Ledger ledger) throws SQLException
    {


       try
       {
               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Insert into Ledger (Date, BaseAccountName, AccountName, DebitAmount, CreditAmount, Balance, Particular) values (CURDATE(), ?, ?, ?, ?, ?, ?)";
               ps = (PreparedStatement) connection.prepareStatement(query);

               ps.setString(1, ledger.getBaseAccountName());
               ps.setString(2, ledger.getAccountName());
               ps.setDouble(3, ledger.getDebitAmount());
               ps.setDouble(4, ledger.getCreditAmount());
               ps.setDouble(5, ledger.getBalance());
               ps.setString(6, ledger.getParticular());
               ps.executeUpdate();

      }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static double getBalance(String accountName) throws SQLException
    {
        double balance = 0.00;


        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select sum(balance)from Ledger where BaseAccountName =?";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            rs = ps.executeQuery();
            rs.next();
            balance = rs.getDouble(1);
            return balance;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return balance;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
    }
    public static ArrayList<Ledger> getLedger(String accountName)
    {


        ArrayList<Ledger> list = new ArrayList<Ledger>();

        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from Ledger where BaseAccountName = ? order by date ,id ";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            rs = ps.executeQuery();



            while(rs.next())
            {
                Ledger ledger = new Ledger();

                ledger.setId(rs.getInt("id"));
                ledger.setDate(rs.getDate("Date"));
                ledger.setBaseAccountName(rs.getString("BaseAccountName"));
                ledger.setAccountName(rs.getString("AccountName"));
                ledger.setDebitAmount(rs.getDouble("DebitAmount"));
                ledger.setCreditAmount(rs.getDouble("CreditAmount"));
                ledger.setBalance(rs.getDouble("balance"));
                ledger.setParticular(rs.getString("Particular"));
                list.add(ledger);
            }
            return list;
        }
        catch(SQLException sql)
        {
            sql.printStackTrace();
            return list;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void insertIntoSubAccount(SubAccount subAccount) throws SQLException
    {


       try
       {
               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Insert into SubAccount (mainAccountName, subAccountName, address, phoneno) values (?, ?, ?, ?)";
               ps = (PreparedStatement) connection.prepareStatement(query);

               ps.setString(1, subAccount.getMainAccountName());
               ps.setString(2, subAccount.getSubAccountName());
               ps.setString(3, subAccount.getAddress());
               ps.setString(4, subAccount.getPhoneNo());
               ps.executeUpdate();

      }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static ArrayList<SubAccount> selectSubAccountName(String accountName)
    {

            ArrayList<SubAccount> list = new ArrayList();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from SubAccount where mainAccountName=? order by subAccountName";


            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            rs = ps.executeQuery();


            while(rs.next())
            {
                SubAccount subAccount = new SubAccount();
                subAccount.setMainAccountName(rs.getString("mainAccountName"));
                subAccount.setSubAaccountName(rs.getString("subAccountName"));



                list.add(subAccount);


            }
            return list;

        }
        catch(SQLException sql)
        {
            return list;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static ArrayList<SubAccount> selectSubAccountName()
    {

            ArrayList<SubAccount> list = new ArrayList();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from SubAccount";

            ps = (PreparedStatement) connection.prepareStatement(query);

            rs = ps.executeQuery();


            while(rs.next())
            {
                SubAccount subAccount = new SubAccount();
                subAccount.setId(rs.getInt("id"));
                subAccount.setMainAccountName(rs.getString("mainAccountName"));
                subAccount.setSubAaccountName(rs.getString("subAccountName"));

                list.add(subAccount);


            }
            return list;

        }
        catch(SQLException sql)
        {
            return list;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static ArrayList<String> selectMainAccountName()
    {

            ArrayList<String> list = new ArrayList();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select distinct mainAccountName from SubAccount order by mainAccountName";

            ps = (PreparedStatement) connection.prepareStatement(query);

            rs = ps.executeQuery();


            while(rs.next())
            {
                String subAccount = rs.getString(1);

                list.add(subAccount);


            }
            return list;

        }
        catch(SQLException sql)
        {
            return list;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static void insertIntoSubLedger(SubLedger ledger) throws SQLException
    {


       try
       {
               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Insert into SubLedger (Date, debitId, creditId, mainAccount, baseAccountName, accountName, particular, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal) values (CURDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
               ps = (PreparedStatement) connection.prepareStatement(query);

               ps.setInt(1, ledger.getDebitId());
               ps.setInt(2, ledger.getCreditId());
               ps.setString(3, ledger.getMainAccount());
               
               
               ps.setString(4, ledger.getBaseAccountName());
               ps.setString(5, ledger.getAccountName());
               ps.setString(6, ledger.getParticular());

               ps.setDouble(7, ledger.getDrUnit());
               ps.setString(8, ledger.getDrMeasurement());
               ps.setDouble(9, ledger.getDrRate());
               ps.setDouble(10, ledger.getDrTotal());

               ps.setDouble(11, ledger.getCrUnit());
               ps.setString(12, ledger.getCrMeasurement());
               ps.setDouble(13, ledger.getCrRate());
               ps.setDouble(14, ledger.getCrTotal());

               ps.setDouble(15, ledger.getBlUnit());
               ps.setString(16, ledger.getBlMeasurement());
               ps.setDouble(17, ledger.getBlRate());
               ps.setDouble(18, ledger.getBlTotal());

               ps.executeUpdate();

      }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static double getSubLedgerBalance(String accountName) throws SQLException
    {
        double balance = 0.00;


        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select sum(blTotal)from subLedger where BaseAccountName =?";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            rs = ps.executeQuery();
            rs.next();
            balance = rs.getDouble(1);
            return balance;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return balance;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
    }
    
    
    public static double getSubLedgerBalance(String accountName, int debitId, int creditId) throws SQLException
    {
        double balance = 0.00;


        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select sum(blTotal)from subLedger where mainAccount =? and (debitId=? or creditId=?)";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            ps.setInt(2, debitId);
            ps.setInt(3, creditId);
            rs = ps.executeQuery();
            rs.next();
            balance = rs.getDouble(1);
            return balance;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return balance;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
    }
    
    
    public static ArrayList<String> selectEachSubAccountName(String accountName)
    {

            ArrayList<String> list = new ArrayList();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select distinct baseAccountName from SubLedger where mainAccount=? order by baseAccountName";


            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            rs = ps.executeQuery();


            while(rs.next())
            {
                String subAccount = rs.getString(1);

                list.add(subAccount);


            }
            return list;

        }
        catch(SQLException sql)
        {
            return list;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static int numberOfRows() throws SQLException
    {
        int numberOfRows=0;

       try
       {

               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Select * from Ledger";
               ps = (PreparedStatement) connection.prepareStatement(query);


               rs=ps.executeQuery();
               rs.last();
               return numberOfRows=rs.getInt("id");


      }
       catch(SQLException e)
       {
           e.printStackTrace();
           return numberOfRows;
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    
    public static void updateLedgerDebit(int transectionIdDebit, double balance) throws SQLException
    {
        try
        {
            
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Update Ledger set DebitAmount=?, Balance=? where id=?";
            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setDouble(1, balance);
            ps.setDouble(2, balance);
            ps.setInt(3, transectionIdDebit);

            
            ps.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ps.close();

        }

    }
    public static void updateLedgerCredit(int transectionId, double balance) throws SQLException
    {
        try
        {
            
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Update Ledger set CreditAmount=?, Balance= ? where id=?";
            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setDouble(1, balance);
            ps.setDouble(2, balance*-1);
            ps.setInt(3, transectionId);

            ps.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ps.close();

        }

    }
    public static SubAccount selectSubAccountNameBySub(String subAccountName)
    {

            SubAccount subAccount = new SubAccount();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from SubAccount where subAccountName=?";


            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, subAccountName);
            rs = ps.executeQuery();


            while(rs.next())
            {
                subAccount.setMainAccountName(rs.getString("mainAccountName"));
                subAccount.setSubAaccountName(rs.getString("subAccountName"));
                subAccount.setAddress(rs.getString("address"));
                subAccount.setPhoneNo(rs.getString("phoneNo"));
            }
            return subAccount;

        }
        catch(SQLException sql)
        {
            return subAccount;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    public static void insertIntoProduct(Product product) throws SQLException
    {


       try
       {
               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Insert into Product (productName, measurementUnit) values (?, ?)";
               ps = (PreparedStatement) connection.prepareStatement(query);

               ps.setString(1, product.getProductName());
               ps.setString(2, product.getMeasurementUnit());
               ps.executeUpdate();

      }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static ArrayList<Product> selectAllProductName() throws SQLException
    {
           ArrayList<Product> list = new ArrayList();

        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from Product order by productName";
            ps = (PreparedStatement) connection.prepareStatement(query);
            rs = ps.executeQuery();


            while(rs.next())
            {
                Product product= new Product();


                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("productName"));
                product.setMeasurementUnit(rs.getString("measurementUnit"));

                list.add(product);


            }
            return list;

        }
        catch(SQLException e)
       {
           e.printStackTrace();
           return list;
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static void insertIntoMeasurement(Measurement measurement) throws SQLException
    {


       try
       {
               connection = (Connection) DriverManager.getConnection(url, user, pass);
               query = "Insert into Measurement (measurementUnit) values (?)";
               ps = (PreparedStatement) connection.prepareStatement(query);

               ps.setString(1, measurement.getMeasurement());
               ps.executeUpdate();

      }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static ArrayList<Measurement> selectAllMeasurementUnit() throws SQLException
    {
           ArrayList<Measurement> list = new ArrayList();

        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from Measurement order by measurementUnit";
            ps = (PreparedStatement) connection.prepareStatement(query);
            rs = ps.executeQuery();


            while(rs.next())
            {
                Measurement measurement= new Measurement();


                measurement.setId(rs.getInt("id"));
                measurement.setMeasurement(rs.getString("measurementUnit"));
                

                list.add(measurement);


            }
            return list;

        }
        catch(SQLException e)
       {
           e.printStackTrace();
           return list;
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    
    public static Product selectMeasurementByProduct(String productName) throws SQLException
    {

            Product product = new Product();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from product where productName=?";


            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, productName);
            rs = ps.executeQuery();


            while(rs.next())
            {
                product.setProductName(rs.getString("productName"));
                product.setMeasurementUnit(rs.getString("measurementUnit"));
                
            }
            return product;

        }
        catch(SQLException sql)
        {
            return product;
        }
        finally
        {
            try
            {   rs.close();
                ps.close();
                connection.close();
            }
            catch(SQLException e)
            {
            }
        }
    }
    
    
public static double getProfitAndLoss() throws SQLException
{   
            ArrayList<Account> accountList=Database.selectAllAccountName();
            ArrayList<Account> revenueAccoutList=new ArrayList();
            Iterator iterator = accountList.listIterator();
            while(iterator.hasNext())
            {
                Account revenueAccount = (Account) iterator.next();
                if(revenueAccount.getAccountType().equalsIgnoreCase("Revenue"))
                {
                    revenueAccoutList.add(revenueAccount);
                }
            }

            
        
        Iterator iterator1 = revenueAccoutList.listIterator();
        Iterator iterator2 = revenueAccoutList.listIterator();
        Iterator iterator3 = revenueAccoutList.listIterator();
        Iterator iterator4 = revenueAccoutList.listIterator();



        double incomeBalance=0.00;
        double openingInventoryBalance=0.00;
        double purchaseBalance=0.00;
        double totalPurchaseAndInventory=0.00;
        double closingInventoryBalance=InventoryData.getClosignTotalInventory();
        double costOfGoodsSold=0.00;
        double grossProfit=0.00;
        String grossPofitOrLoss="";
        double totalOfficeExpenses=0.00;
        String netPofitOrLoss="";
        double netProfitandLoss=0.00;

        while(iterator1.hasNext())
        {

            Account account = (Account) iterator1.next();
            String accountName = account.getAccountName();
            double balance= Database.getBalance(accountName);
            if(balance<0)
            {
                if(accountName.equalsIgnoreCase("Inventory A/C"))
                    continue;
                incomeBalance=incomeBalance+balance*-1;
                
            }
        }
        
        while(iterator2.hasNext())
        {
            Account account =  (Account) iterator2.next();
            String accountName = account.getAccountName();
            
            
            if(accountName.equalsIgnoreCase("Purchase A/C"))
            {
                
                    openingInventoryBalance = Database.getBalance("Inventory A/C");
                
                    purchaseBalance = Database.getBalance(accountName);
            
            }
            }

        totalPurchaseAndInventory=openingInventoryBalance+ purchaseBalance;
        
        costOfGoodsSold=totalPurchaseAndInventory- closingInventoryBalance;
        

        if(incomeBalance>=costOfGoodsSold)
           grossPofitOrLoss = "Gross Profit";
        else
            grossPofitOrLoss = "Gross Loss";

        grossProfit=incomeBalance-costOfGoodsSold;

        
        while(iterator4.hasNext())
        {
            Account account = (Account) iterator4.next();
            String accountName = account.getAccountName();
            double balance= Database.getBalance(accountName);
            
            if(balance>0)
            {
            if(accountName.equalsIgnoreCase("Purchase A/C")||accountName.equalsIgnoreCase("Inventory A/C")||accountName.equalsIgnoreCase("Capital A/C"))
            continue;
                
                totalOfficeExpenses = totalOfficeExpenses+balance;
    
          }
          
            }

        if(grossProfit>totalOfficeExpenses)
                    netPofitOrLoss="Net Profit";
                else
                    netPofitOrLoss="Net Loss";
        netProfitandLoss= grossProfit-totalOfficeExpenses;

        
        
        return netProfitandLoss;
       
    }
public static ArrayList<SubLedger> getSubLedger(String accountName) throws SQLException
    {
        ArrayList<SubLedger> subLedgerList = new ArrayList();


        try
        {
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from subLedger where BaseAccountName =?";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, accountName);
            rs = ps.executeQuery();
            while(rs.next())
            {
                SubLedger subLedger = new SubLedger();
                
                subLedger.setId(rs.getInt("id"));
                subLedger.setId(rs.getInt("debitId"));
                subLedger.setId(rs.getInt("creditId"));
                subLedger.setDate(rs.getDate("Date"));
                subLedger.setMainAccount(rs.getString("mainAccount"));
                subLedger.setBaseAccountName(rs.getString("baseAccountName"));
                subLedger.setAccountName(rs.getString("accountName"));
                subLedger.setParticular(rs.getString("particular"));
                
                subLedger.setDrUnit(rs.getDouble("drUnit"));
                subLedger.setDrMeasurement(rs.getString("drMeasurement"));
                subLedger.setDrRate(rs.getDouble("drRate"));
                subLedger.setDrTotal(rs.getDouble("drTotal"));
                
                subLedger.setCrUnit(rs.getDouble("crUnit"));
                subLedger.setCrMeasurement(rs.getString("crMeasurement"));
                subLedger.setCrRate(rs.getDouble("crRate"));
                subLedger.setCrTotal(rs.getDouble("crTotal"));
                
                
                subLedger.setBlUnit(rs.getDouble("blUnit"));
                subLedger.setBlMeasurement(rs.getString("blMeasurement"));
                subLedger.setBlRate(rs.getDouble("blRate"));
                subLedger.setBlTotal(rs.getDouble("blTotal"));
                
                subLedgerList.add(subLedger);
            }
            
            
            return subLedgerList;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return subLedgerList;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
    }
}
