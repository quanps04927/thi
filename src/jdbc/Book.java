/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Quan
 */
public class Book {

    int Id;
    String Title;
    float Price;
    int ret;
    String url = "jdbc:sqlserver://quantm1.database.windows.net:1433;database=Book;user=quantm1@quantm1;password=Quanminh1";
    Scanner sc = new Scanner(System.in);

    public void addBook() {
        do {
            System.out.print("Title: ");
            Title = sc.next();
        } while (Title.equals(""));
        boolean isNum;
        do {
            isNum = true;
            try {
                System.out.print("Price: ");
                Price = sc.nextInt();
            } catch (Exception e) {
                System.err.println("Invaild number");
                sc.nextLine();
                isNum = false;
            }
        } while (!isNum || Price <= 0);
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "insert into Book (Title,Price) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setFloat(2, Price);
            ret = ps.executeUpdate();
            if (ret == 1) {
                System.out.println("Done!");
            } else {
                System.out.println("Failed");
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateBook() {
        Connection conn = null;
        PreparedStatement ps = null;
        System.out.print("ID: ");
        int i = sc.nextInt();
        int ii;
        String x = "";
        do {
            System.out.println("---------------");
            System.out.println("1.Update Title");
            System.out.println("2.Update Price");
            System.out.println("3.Update both");
            System.out.println("4.Exit");
            System.out.println("---------------");
            System.out.print("Your choice:");
            ii = sc.nextInt();
            switch (ii) {
                case 1:

                    System.out.print("New Title: ");
                    String newtitle = sc.next();
                    try {
                        conn = DriverManager.getConnection(url);
                        String sql = "update Book set Title=? where ID=?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, newtitle);
                        ps.setInt(2, i);
                        ret = ps.executeUpdate();
                        if (ret == 1) {
                            System.out.println("Done!");
                        } else {
                            System.out.println("Failed");
                        }
                        conn.close();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("New Price: ");
                    float newprice = sc.nextFloat();
                    try {
                        conn = DriverManager.getConnection(url);
                        String sql = "update Book set Price=? where ID=?";
                        ps = conn.prepareStatement(sql);
                        ps.setFloat(1, newprice);
                        ps.setInt(2, i);
                        ret = ps.executeUpdate();
                        if (ret == 1) {
                            System.out.println("Done!");
                        } else {
                            System.out.println("Failed");
                        }
                        conn.close();

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("New Title: ");
                    sc.nextLine();
                    String newtitle1 = sc.nextLine();
                    System.out.print("New Price: ");
                    float newprice1 = sc.nextFloat();
                    try {
                        conn = DriverManager.getConnection(url);
                        String sql = "update Book set Title=?,Price=? where ID=?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, newtitle1);
                        ps.setFloat(2, newprice1);
                        ps.setInt(3, i);
                        ret = ps.executeUpdate();
                        if (ret == 1) {
                            System.out.println("Done!");
                        } else {
                            System.out.println("Failed");
                        }
                        conn.close();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Are you sure (y/n)");
                    x = sc.next();
                    break;
                default:
                    System.out.println("Invalid function. Please choose again!");
            }
        } while (!x.equals("y"));
    }

    public void deleteBook() {
        System.out.print("ID: ");
        int i = sc.nextInt();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "delete from Book where ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ps.executeUpdate();
            ret = ps.executeUpdate();
            if (ret == 1) {
                System.out.println("Done!");
            } else {
                System.out.println("Fail");
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void dab() {
        try {
//            1.Tạo kết nối
            Connection conn = DriverManager.getConnection(url);
//            2.Tạo đối tượng thực thi câu lệnh sql
            String sql = "select * from Book";
            PreparedStatement ps = conn.prepareStatement(sql);
//            3.Thực thi
            ResultSet rs = ps.executeQuery();
//            4.Xử lý kq
            if (rs.isBeforeFirst() == false) {
                System.out.println("Không có sách");
            }
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") + " - Title:" + rs.getString("Title")
                        + " - Price:" + rs.getFloat("Price"));
            }
//            5.Đóng kết nôi
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbbn() {
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "select * from Book where Title like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap Title:");
            String a = sc.nextLine();
            ps.setString(1, "%"+a+"%");
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                System.out.println("Không có sách");
            }
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") + " - Title:" + rs.getString("Title")
                        + " - Price:" + rs.getFloat("Price"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbbp() {
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "select * from Book where Price = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap Price:");
            float a = sc.nextFloat();
            ps.setFloat(1, a);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                System.out.println("Không có sách");
            }
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") + " - Title:" + rs.getString("Title")
                        + " - Price:" + rs.getFloat("Price"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbipr() {
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "select * from Book where Price >=? and Price <=? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap khoang dau:");
            float a = sc.nextFloat();
            System.out.print("Nhap khoang cuoi:");
            float b = sc.nextFloat();
            ps.setFloat(1, a);
            ps.setFloat(2, b);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                System.out.println("Không có sách");
            }
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") + " - Title:" + rs.getString("Title")
                        + " - Price:" + rs.getFloat("Price"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dabbp() {
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "select * from Book order by Price";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                System.out.println("Không có sách");
            }
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") + " - Title:" + rs.getString("Title")
                        + " - Price:" + rs.getFloat("Price"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ddobp() {
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "select * from Book order by Price desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                System.out.println("Không có sách");
            }
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") + " - Title:" + rs.getString("Title")
                        + " - Price:" + rs.getFloat("Price"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
