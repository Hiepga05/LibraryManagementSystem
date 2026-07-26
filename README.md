# Library Management System

Ứng dụng quản lý thư viện được xây dựng bằng **Java Swing**, **Maven** và **SQLite**.

## Giới thiệu

Library Management System là phần mềm hỗ trợ quản lý các hoạt động của thư viện như quản lý sách, độc giả và quá trình mượn trả sách. Chương trình được xây dựng theo mô hình phân tầng (**Model – Repository – Service – Controller – View**) giúp mã nguồn rõ ràng, dễ bảo trì và mở rộng.

## Chức năng

- Đăng nhập hệ thống.
- Quản lý sách.
- Quản lý tác giả.
- Quản lý thể loại.
- Quản lý nhà xuất bản.
- Quản lý độc giả.
- Quản lý mượn và trả sách.
- Quản lý phiếu phạt.
- Quản lý tài khoản người dùng.
- Tìm kiếm, thêm, sửa và xóa dữ liệu.

## Cấu trúc package

```
src
└── main
    └── java
        └── com.library
            ├── controller
            ├── database
            ├── exception
            ├── main
            ├── model
            ├── repository
            ├── service
            ├── util
            └── view
```

## Công nghệ sử dụng

- Java 21
- Java Swing
- Maven
- SQLite
- JDBC
- NetBeans IDE

## Hướng dẫn chạy

### Yêu cầu

- JDK 21 hoặc mới hơn.
- NetBeans IDE (hoặc IDE hỗ trợ Maven).

### Chạy chương trình

1. Clone project:

```bash
git clone https://github.com/Hiepga05/LibraryManagementSystem.git
```

Hoặc tải **Code → Download ZIP**.

2. Mở project bằng NetBeans.

3. Chọn **Clean and Build**.

4. Chọn **Run** để chạy chương trình.

## Cơ sở dữ liệu

Project sử dụng **SQLite**, dữ liệu được lưu trong file:

```
library.db
```

Không cần cài đặt MySQL hoặc SQL Server.

## Tài khoản mặc định

```
Username: admin
Password: admin123
```

## Tác giả

**Trịnh Hoàng Hiệp**

Sinh viên Học viện Công nghệ Bưu chính Viễn thông (PTIT)

GitHub: https://github.com/Hiepga05
