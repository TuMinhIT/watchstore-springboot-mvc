# Watchstore Spring Boot MVC

Đây là dự án web bán đồng hồ trực tuyến, xây dựng với Java Spring Boot theo mô hình MVC.

## Tính năng chính

- Quản lý sản phẩm, thương hiệu
- Giỏ hàng, đặt hàng, lịch sử mua hàng
- Đăng ký, đăng nhập, phân quyền người dùng (Spring Security)
- Quản trị viên quản lý tài khoản, đơn hàng, sản phẩm

## Công nghệ sử dụng

- Java 21
- Spring Boot 3.3.4 (Web, Data JPA, Security, Thymeleaf)
- PostgreSQL
- Maven
- Lombok

## Yêu cầu hệ thống

- JDK 17 trở lên (khuyến nghị Java 21)
- PostgreSQL (mặc định cổng 3308, database: `ecommerce`)
- Maven hoặc sử dụng Maven Wrapper (`mvnw`, `mvnw.cmd`)

## Hướng dẫn cài đặt & chạy dự án

1. **Clone dự án:**
   ```
   git clone https://github.com/TuMinhIT/watchstore-springboot-mvc.git
   ```
2. **Tạo database:**
   - Tạo database tên `ecommerce` trên PostgreSQL, cổng 3308 (hoặc chỉnh lại trong `src/main/resources/application.properties`).
   - Tài khoản mặc định: `postgres` / `123456` (có thể thay đổi trong file cấu hình).
3. **Cài đặt dependencies:**
   - Nếu đã cài Maven:
     ```
     mvn clean install
     ```
   - Hoặc dùng Maven Wrapper (không cần cài Maven):
     ```
     .\mvnw.cmd clean install
     ```
4. **Chạy ứng dụng:**
   - Nếu đã cài Maven:
     ```
     mvn spring-boot:run
     ```
   - Hoặc dùng Maven Wrapper:
     ```
     .\mvnw.cmd spring-boot:run
     ```
5. **Truy cập website:**
   - Mở trình duyệt và truy cập: [http://localhost:8080](http://localhost:8080)

## Tài khoản mặc định

- Admin: `admin` / `123456`

## Cấu hình kết nối database (application.properties)

```properties
spring.datasource.url=jdbc:postgresql://localhost:3308/ecommerce
spring.datasource.username=postgres
spring.datasource.password=123456
```

## Thư mục chính

- `src/main/java/com/spring/ecomerces/` - Mã nguồn Java
- `src/main/resources/templates/` - Giao diện Thymeleaf
- `src/main/resources/static/` - Tài nguyên tĩnh (CSS, JS, ảnh)

**Tác giả:** TuMinhIT
