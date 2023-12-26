package ra.ra.presentation;

import ra.bussiness.BookBusiness;
import ra.bussiness.IBussiness;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookPresentation {
    private static IBussiness bookBusiness = new BookBusiness();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************Book Management**********");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Book> listBook = bookBusiness.findAll();
                    listBook.stream().forEach(book -> book.displayData());
                    break;
                case 2:
                    Book book = new Book();
                    book.inputData(scanner);
                    boolean resultCreate = bookBusiness.create(book);
                    if (resultCreate){
                        System.out.println("Thêm mới thành công");
                    }else{
                        System.err.println("Thêm mới thất bại");
                    }
                    break;
                case 3:
                    System.out.println("Nhập vào mã sách cần cập nhật:");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    Book bookUpdate = new Book();
                    bookUpdate.setBookId(bookId);
                    bookUpdate.inputData(scanner);
                    boolean resultUpdate = bookBusiness.update(bookUpdate);
                    if (resultUpdate){
                        System.out.println("Cập nhật thành công");
                    }else {
                        System.err.println("Không tồn tại mã sách hoặc có lỗi trong quá trình thực hiện");
                    }
                    break;
                case 4:
                    System.out.println("Nhập vào mã sách cần xóa:");
                    int bookIdDelete = Integer.parseInt(scanner.nextLine());
                    boolean resultDelete = bookBusiness.delete(bookIdDelete);
                    if (resultDelete){
                        System.out.println("Xóa thành công");
                    }else{
                        System.err.println("Mã sách không tồn tại hoặc có lỗi trong quá trình thực hiện");
                    }
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-5");
            }
        }while (true);
    }
    /*
    * 1. Tạo CSDL ShopManagement
    * 2. Tạo bảng Categories gồm các thông tin: mã danh mục, tên danh mục, trạng thái
    * 3. Tạo bảng Product gồm các thông tin: Mã sản phẩm, tên sản phẩm, giá, ngày tạo,
    * tiêu đề, mã danh mục, trạng thái
    * 4. Tạo ứng dụng Java Console kết nối CSDL và thực hiện các chức năng theo menu:
    * *************SHOP MANAGEMENT****************
    * 1. Quản lý danh mục
    * 2. Quản lý sản phẩm
    * 3. Thoát
    * *************CATEGORIES MANAGEMENT**********
    * 1. Danh sách danh mục
    * 2. Thêm mới danh mục
    * 3. Cập nhật danh mục
    * 4. Xóa danh mục (Chỉ được xóa danh mục chưa có sản phẩm)
    * 5. Tìm danh mục theo tên
    * 6. Thoát
    * **************PRODUCT MANAGEMENT************
    * 1. Danh sách sản phẩm
    * 2. Thêm mới sản phẩm
    * 3. Cập nhật sản phẩm
    * 4. Xóa sản phẩm
    * 5. Tìm kiếm sản phẩm theo tên
    * 6. Hiển thị các sản phẩm theo mã danh mục
    * 7. Thống kê số lượng sản phẩm của từng danh mục
    * 8. Sắp xếp sản phẩm theo giá tăng dần
    * 9. Thoát
    * */
}
