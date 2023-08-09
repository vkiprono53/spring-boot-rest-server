--**************************************************************************--
--EMPLOYEE
CREATE TABLE employees(
                         employee_id int,
                          pno character varying(255),
                         first_name character varying(255),
                         last_name character varying(255),
                         email character varying(100),
                         active character varying(1),
                         department_id int,
                         created_by int,
                         created_date TIMESTAMP WITHOUT TIME ZONE,
                         updated_by  int,
                         updated_date TIMESTAMP WITHOUT TIME ZONE,
                         PRIMARY KEY(employee_id)
);

--Employees sequence
CREATE SEQUENCE employees_seq
    START 1
INCREMENT 1
MAXVALUE 9999999999
OWNED BY employees.employee_id;

--Employee index
CREATE INDEX idx_employee_employee_id
    ON employees(employee_id);

--**************************************************************************--
--ADDRESS TABLE
CREATE TABLE addresses(
                          address_id int,
                          employee_id int,
                          address character varying(100),
                          phone character varying(15),
                          postal_code character varying(20),
                          created_by int,
                          created_date TIMESTAMP WITHOUT TIME ZONE,
                          updated_by  int,
                          updated_date TIMESTAMP WITHOUT TIME ZONE,
                          PRIMARY KEY(address_id),
                              FOREIGN KEY(employee_id) REFERENCES employees(employee_id)
);
---addresses sequence:
CREATE SEQUENCE addresses_seq
START 1
INCREMENT 1
MAXVALUE 9999999999
OWNED BY addresses.address_id;

--Address index
CREATE INDEX idx_address_address_id
    ON addresses(address_id);

--**************************************************************************--

--DEPARTMENT
CREATE TABLE departments(
                           department_id int,
                           name character varying(100),
                           code character varying(100),
                           description character varying(255),
                           created_by int,
                           created_date TIMESTAMP WITHOUT TIME ZONE,
                           updated_by  int,
                           updated_date TIMESTAMP WITHOUT TIME ZONE,
                           PRIMARY KEY(department_id)
);
--Department sequence
CREATE SEQUENCE departments_seq
    START 1
INCREMENT 1
MAXVALUE 9999999999
OWNED BY departments.department_id;

--Department index
CREATE INDEX idx_department_department_id
    ON departments(department_id);

--**************************************************************************--

--DROP TABLE user_dtls
--user_dtls table
CREATE TABLE user_dtls(
                          user_id INTEGER ,
                          first_name character varying(100),
                          last_name character varying(100),
                          email character varying(100),
                          password character varying(100),
                          role character varying(50),
                          PRIMARY KEY(user_id));

--user_dtls sequence
CREATE SEQUENCE user_dtls_seq
    START 1
INCREMENT 1
MAXVALUE 9999999999
OWNED BY user_dtls.user_id;

--user_dtls index
CREATE INDEX idx_user_dtls_user_id
    ON user_dtls(user_id);

--**************************************************************************--
