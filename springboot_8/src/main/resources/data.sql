-- =========================
-- INSTITUTIONS
-- =========================

INSERT INTO institution (name, website)
VALUES
('Code Academy', 'https://codeacademy.com'),

('TechVerse Institute', 'https://techverse.com'),

('DevMaster School', 'https://devmaster.com'),

('CloudNest Academy', 'https://cloudnest.com'),

('AI Future Labs', 'https://aifuturelabs.com');



-- =========================
-- INSTRUCTORS
-- =========================

INSERT INTO instructor (name, email, institution_id)
VALUES

('Om Patil', 'om@olp.com', 1),

('Rahul Sharma', 'rahul@olp.com', 1),

('Sneha Joshi', 'sneha@olp.com', 2),

('Amit Kulkarni', 'amit@olp.com', 2),

('Priya Mehta', 'priya@olp.com', 3),

('Rohan Desai', 'rohan@olp.com', 3),

('Neha Patil', 'neha@olp.com', 4),

('Karan Verma', 'karan@olp.com', 5);



-- =========================
-- STUDENTS
-- PASSWORD = 123456
-- BCrypt encoded
-- =========================

INSERT INTO student (name, email, password, role)
VALUES

(
'Aditya Shah',
'aditya@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Riya Patel',
'riya@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Kunal Mehra',
'kunal@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Anjali Verma',
'anjali@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Harsh Gupta',
'harsh@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Simran Kaur',
'simran@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Vivek Joshi',
'vivek@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Pooja Sharma',
'pooja@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Rohit Jain',
'rohit@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
),

(
'Meera Patil',
'meera@gmail.com',
'$2a$10$DowJonesDowJonesDowJonesu3V9n8n0nN0u',
'STUDENT'
);



-- =========================
-- COURSES
-- =========================

INSERT INTO course
(name, description, category, instructor_id, institution_id)
VALUES

(
'Spring Boot Masterclass',
'Complete backend development using Spring Boot.',
'PROGRAMMING',
1,
1
),

(
'React Frontend Development',
'Modern frontend applications using React.',
'WEB_DEVELOPMENT',
2,
1
),

(
'Machine Learning Basics',
'Introduction to machine learning concepts.',
'AI_ML',
5,
3
),

(
'AWS Cloud Essentials',
'Learn AWS cloud computing fundamentals.',
'CLOUD_COMPUTING',
7,
4
),

(
'Cyber Security Fundamentals',
'Introduction to cyber security and ethical hacking.',
'CYBER_SECURITY',
8,
5
),

(
'Docker & Kubernetes',
'Containerization and orchestration using Docker and Kubernetes.',
'DEVOPS',
4,
2
),

(
'SQL Database Mastery',
'Master relational databases and PostgreSQL.',
'DATABASE',
3,
2
),

(
'UI UX Design Principles',
'Learn UI UX fundamentals and Figma.',
'UI_UX',
6,
3
);



-- =========================
-- INDIVIDUAL INSTRUCTOR COURSES
-- =========================

INSERT INTO course
(name, description, category, instructor_id, institution_id)
VALUES

(
'Java Interview Preparation',
'Complete Java interview preparation including OOP and collections.',
'PROGRAMMING',
1,
NULL
),

(
'Freelance React Development',
'Build freelance-ready frontend projects using React.',
'WEB_DEVELOPMENT',
2,
NULL
),

(
'Machine Learning Crash Course',
'Practical machine learning using Python.',
'AI_ML',
6,
NULL
),

(
'Docker for Beginners',
'Learn Docker fundamentals and deployment basics.',
'DEVOPS',
4,
NULL
);