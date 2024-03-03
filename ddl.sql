CREATE TABLE type_identification (
    id SERIAL PRIMARY KEY,
    name VARCHAR(10)
);

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    code VARCHAR(100),  
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(10),
    date_of_birth DATE,
	date_of_admission DATE,
    address VARCHAR(255),
	type_identification_id INTEGER REFERENCES type_identification(id),
    FOREIGN KEY (type_identification_id) REFERENCES type_identification(id),
    identification VARCHAR(100)
);


CREATE TABLE student_grades (
    grade_id SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES students(id),
    course_name VARCHAR(100),
    grade DECIMAL(5, 2),
    FOREIGN KEY (student_id) REFERENCES students(id)
);


INSERT INTO type_identification (name) VALUES
    ('CC'),
    ('IT'),
    ('NIT');
