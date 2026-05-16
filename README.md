# Employee Management System (EMS)

This is a complete **Java Spring Boot Employee Management System**.
This codebase is beautifully structured for freshers/new grads and features a modern backend integrated with a fluid frontend.

## Core Technologies
- **Java 17** & **Spring Boot 3.2+** 
- **Spring MVC** (Controller-Service-Repository flow)
- **Thymeleaf + Bootstrap 5** (Frontend Views)
- **Spring Data JPA / Hibernate** (Database ORM)
- **Spring Security** (Authentication & Role Based Authorization)
- **MySQL DB / H2 Database** 

---

## 🚀 Setup & Run Locally

### Approach 1: Run the Spring Boot App
Navigate to the `java-backend` folder to run the backend:

1. Open a terminal inside the `java-backend/` folder.
2. Ensure you have Java 17 and Maven installed.
3. Start the application by running:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at **http://localhost:8080**
5. Default Users (configured via in-memory DB or active spring security setup):
   * **Admin Login:** `admin` / `admin123`
   * **User Login:** `user` / `user123`

---

## ☁️ Deployment

You can deploy the Spring Boot app to platforms like Render, Railway.app, or Heroku. Ensure you provide the appropriate `DB_URL`, `DB_USER`, and `DB_PASS` environment variables based on your SQL instance.

## 🧪 Interview Topics Covered Here

✅ **MVC Pattern:** Used to separate logic into `EmployeeController`, `EmployeeService`, and `Employee`.
✅ **REST APIs:** Full JSON API integration done locally in `EmployeeRestController`.
✅ **Dependency Injection (DI):** `@Autowired` pulls in Services seamlessly.
✅ **JPA/Hibernate:** We avoid writing complex native SQL statements by leveraging ORM entities & interfaces.
✅ **Spring Security:** Passwords are hashed automatically using `BCryptPasswordEncoder`.

Good luck exploring this!
