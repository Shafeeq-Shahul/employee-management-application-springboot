# employee-management-application-springboot
A simple CRUD (Create,  Read, Update, Delete) based springboot application. It enables to know about ORM - Spring data JPA, REST APIs and basics of Spring security.

### EmployeeController API Endpoints

| HTTP Method | Endpoint Path | Description |
| --- | --- | --- |
| POST | `/emp/save` | Save a single employee |
| POST | `/emp/saveAll` | Save multiple employees |
| PUT | `/emp/update` | Update an existing employee |
| GET | `/emp/getAll` | Retrieve all employees |
| GET | `/emp/get?id={id}` | Retrieve employee by ID (query param) |
| GET | `/emp/get/{email}` | Retrieve employee by email (path variable) |
| DELETE | `/emp/delete?id={id}` | Delete employee by ID (query param) |
| DELETE | `/emp/deleteAll` | Delete all employees |

## Clone the Repository

```bash
git clone https://github.com/Shafeeq-Shahul/employee-management-application-springboot.git
cd employee-management-application-springboot
