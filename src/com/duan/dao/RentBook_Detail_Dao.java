package com.duan.dao;

public class RentBook_Detail_Dao 
{
//    public ArrayList<RentBookDetail> getAll()
//    {
//        ArrayList<RentBookDetail> list = new ArrayList<>();
//        ResultSet rs = JdbcHelper.executeQuery("SELECT * FROM RENTBOOK_DETAIL");
//        try {
//            while (rs.next())
//            {
//                int rentbookId  = rs.getInt(1);
//                String bookId = rs.getString(2);
//                int mount = rs.getInt(3);
//                double price = rs.getDouble(4);
//                
//                RentBookDetail rbdetail = new RentBookDetail(rentbookId, bookId, mount, price);
//                list.add(rbdetail);
//            }
//        } 
//        catch (SQLException ex) 
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//    
//    public boolean insert(RentBookDetail rb)
//    {
//        String sql = "INSERT INTO RENTBOOK_DETAIL Values(?,?,?,?)";
//        try 
//        {
//            PreparedStatement pre = JdbcHelper.preparedStatement(sql, rb.getRentbookId(), 
//                                                        rb.getBookId(),rb.getAmount(),rb.getPrice());
//            int count = pre.executeUpdate();
//            return count > 0;
//            
//        } catch (SQLException ex) 
//        {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    
  
}
    

