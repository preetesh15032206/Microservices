# Java Employee Management System (EMS)

This is a complete, beginner-friendly **Java Spring Boot Employee Management System**.
This codebase is perfectly suited for freshers / new grads applying to companies like Cognizant (Digital Nurture / FSE).

## Core Technologies
- **Java 17** & **Spring Boot 3.2+** 
- **Spring MVC** (Controller-Service-Repository flow)
- **Thymeleaf + Bootstrap 5** (Frontend Views)
- **Spring Data JPA / Hibernate** (Database ORM)
- **Spring Security** (Authentication & Role Based Authorization)
- **MySQL DB / JDBC** 

---

## 🚀 Step 1: Run Locally (Development Setup)

### Prerequisites:
1. **Java 17+**
2. **Maven 3.8+**
3. **MySQL Server** (Running locally on default port 3306)

### Local Database Setup:
Open your MySQL Workbench or command line and run:
`CREATE DATABASE ems_db;`

*(JPA will automatically create all tables and schema when you run the app).*

### Start the Application:
1. Open a terminal inside this `java-backend/` folder.
2. Run standard maven command:
   `mvn spring-boot:run`
3. Application available at: **http://localhost:8080**
4. The DB will automatically be seeded with 2 users:
   * **Admin Login:** `admin` / `admin123`
   * **User Login:** `user` / `user123`

---

## ☁️ Step 2: Push to GitHub

1. Ensure Git is installed:
   ```bash
   git init
   git add .
   git commit -m "Initial commit for EMS FSE Project"
   git branch -M main
   git remote add origin YOUR_GITHUB_REPOSITORY_URL
   git push -u origin main
   ```

---

## 🚂 Step 3: Setup Railway (Free MySQL Database)

1. Go to [Railway.app](https://railway.app/) and signup with GitHub.
2. Click **New Project** -> **Provision PostgreSQL/MySQL** (Choose MySQL).
3. Once created, click on the **MySQL instance** -> Go to the **Connect** tab.
4. Copy the raw connection URL (starts with `mysql://`).

---

## 🚀 Step 4: Deploy on Render (Free Java/Docker hosting)

1. Go to [Render.com](https://render.com) and link your GitHub.
2. Click **New +** -> **Web Service**.
3. Select your GitHub repository containing this exact code folder structure.
4. Render will automatically detect the **Dockerfile**.
5. Scroll down to **Environment Variables** and add the variables from Railway!

### Environment Variables to add in Render:
- `DB_URL`: The JDBC URL! *Format carefully:* `jdbc:mysql://[RAILWAY-HOST]:[PORT]/[DB-NAME]`
- `DB_USER`: (from Railway) e.g., root
- `DB_PASS`: (from Railway database password)

6. Click **Deploy**. Render will build the container using Maven and deploy it live to a `https://xyz.onrender.com` URL!

## 🧪 Interview Topics Covered Here

✅ **MVC Pattern:** Used to separate logic into `EmployeeController`, `EmployeeService`, and `Employee`.
✅ **REST APIs:** Full JSON API integration done locally in `EmployeeRestController`.
✅ **Dependency Injection (DI):** `@Autowired` pulls in Services seamlessly.
✅ **JPA/Hibernate:** We avoid writing complex native SQL statements by leveraging ORM entities & interfaces.
✅ **Spring Security:** Passwords are hashed automatically using `BCryptPasswordEncoder`.

Good luck!
