package ra.bussiness;

import ra.entity.Book;
import ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness implements IBussiness<Book,Integer>{
    @Override
    public List<Book> findAll() {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Book> listBook = null;
        try {
            callSt = conn.prepareCall("{call get_all_book()}");
            ResultSet rs = callSt.executeQuery();
            listBook = new ArrayList<>();
            while (rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("id"));
                book.setBookName(rs.getString("name"));
                book.setPrice(rs.getFloat("price"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("book_status"));
                listBook.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return listBook;
    }

    @Override
    public boolean create(Book book) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call create_book(?,?,?,?,?)}");
            //set giá trị cho các tham số vào
            callSt.setString(1,book.getBookName());
            callSt.setFloat(2,book.getPrice());
            callSt.setString(3,book.getTitle());
            callSt.setString(4,book.getAuthor());
            callSt.setBoolean(5,book.isStatus());
            //Đăng ký kiểu dữ liệu cho các tham số ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            conn.commit();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean update(Book book) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            //1. Kiểm tra bookId có tồn tại không
            callSt1 = conn.prepareCall("{call get_book_by_id(?,?)}");
            callSt1.setInt(1,book.getBookId());
            //Đăng ký tham số trả ra
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            //Lấy giá trị tham số trả ra
            int cnt_book = callSt1.getInt(2);
            if (cnt_book>0) {
                //2. Thực hiện cập nhật
                callSt2 = conn.prepareCall("{call update_book(?,?,?,?,?,?)}");
                //set giá trị cho các tham số vào
                callSt2.setInt(1, book.getBookId());
                callSt2.setString(2, book.getBookName());
                callSt2.setFloat(3, book.getPrice());
                callSt2.setString(4, book.getTitle());
                callSt2.setString(5, book.getAuthor());
                callSt2.setBoolean(6, book.isStatus());
                //Đăng ký kiểu dữ liệu cho các tham số ra
                //Thực hiện gọi procedure
                callSt2.executeUpdate();
            }else{
                result = false;
            }
            conn.commit();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean delete(Integer bookId) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            //1. Kiểm tra bookId có tồn tại không
            callSt1 = conn.prepareCall("{call get_book_by_id(?,?)}");
            callSt1.setInt(1,bookId);
            //Đăng ký tham số trả ra
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            //Lấy giá trị tham số trả ra
            int cnt_book = callSt1.getInt(2);
            if (cnt_book>0) {
                //2. Thực hiện cập nhật
                callSt2 = conn.prepareCall("{call delete_book(?)}");
                //set giá trị cho các tham số vào
                callSt2.setInt(1, bookId);
                //Đăng ký kiểu dữ liệu cho các tham số ra
                //Thực hiện gọi procedure
                callSt2.executeUpdate();
            }else{
                result = false;
            }
            conn.commit();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public Book findById(Integer integer) {
        return null;
    }
}
