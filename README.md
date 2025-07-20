<h1 align="center">🧑‍💼 Employee Management System</h1>
<h3 align="center"><code>(Java + Swing + MySQL)</code></h3>

<p align="center">
  A fully functional <strong>Employee Management System</strong> built using Java Swing for GUI and MySQL for database operations.  
</p>

---

## 📸 Project Screenshots

> All images are stored in the projectEMSImg/ folder:

### 1. 🏠 Home Screen  
![Home Screen](https://github.com/Bhumikahm/Employee_Management_System/blob/a9ae04119fe947d741870eddd8d80cb467e962c4/home.png)

---

### 2. ➕ Onboard Employee  
![Onboard Employee](https://github.com/Bhumikahm/Employee_Management_System/blob/a9ae04119fe947d741870eddd8d80cb467e962c4/ADDEMP.png)

---

### 3. 📋 View All Employees  
![View All](https://github.com/Bhumikahm/Employee_Management_System/blob/a9ae04119fe947d741870eddd8d80cb467e962c4/viewAll.png)

---

### 4. 🔍 Search Employee  
![Search](https://github.com/Bhumikahm/Employee_Management_System/blob/a9ae04119fe947d741870eddd8d80cb467e962c4/searchEmployee.png)

---

### 5. ✏ Update Employee  
![Update Step 1](https://github.com/Bhumikahm/Employee_Management_System/blob/a9ae04119fe947d741870eddd8d80cb467e962c4/UPDATEEMP.png)

---

### 6. ❌ De-board Employee  
![DeBoard](https://github.com/Bhumikahm/Employee_Management_System/blob/a9ae04119fe947d741870eddd8d80cb467e962c4/DeleteEMP.png)

---

## 🚀 Features

✅ Add New Employee  
✅ View All Employees  
✅ Search by Employee ID  
✅ Update Employee Record  
✅ Delete/De-board Employee  
✅ User-friendly GUI with Java Swing  
✅ Database-backed operations using MySQL  

---

## 🛠 Tech Stack

| Technology | Role |
|------------|------|
| Java | Core Application Logic |
| Java Swing | User Interface |
| MySQL | Data Persistence |
| JDBC | Database Connectivity |

---

## 📦 How to Run Locally

bash
# 1. Clone the repository
git clone https://github.com/Bhumikahm/Employee_Management_System.git
cd EmployeeManagementSystem

# 2. Open in your Java IDE (IntelliJ / Eclipse)
# 3. Ensure MySQL is running and create the database:

CREATE DATABASE emsdb;

# 4. Update database credentials in DBConnection.java:

String url = "jdbc:mysql://localhost:3306/emsdb";
String user = "root";
String password = "yourpassword";

# 5. Run Home.java to launch the system


---

## 📁 Project Structure


EmployeeManagementSystem/
├── src/
│   ├── Home.java
│   ├── OnboardEmployee.java
│   ├── DisplayAll.java
│   ├── SingleEmployee.java
│   ├── UpdateEmployee.java
│   ├── DeBoardEmployee.java
│   └── DBConnection.java
├── db.sql (optional)
└── README.md


---

## 🙋‍♂ Author

*Bhumika HM*  
🔗 [LinkedIn](http://www.linkedin.com/in/bhumika-h-m)  
🌐 [Portfolio](https://bhumika-protfolio.vercel.app/)  
📧 Email: bhumikahm2003@gmail.com

---

> ⭐ *Star* this repository to support the project and help others discover it!
