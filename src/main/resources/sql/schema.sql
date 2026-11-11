-- ===================================================================
-- TalentFlow Enterprise Database Schema (MySQL & SQL Compatible)
-- ===================================================================

CREATE DATABASE IF NOT EXISTS talentflow_db;
USE talentflow_db;

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL,
    department VARCHAR(255),
    status VARCHAR(50) NOT NULL DEFAULT 'Active',
    joined_date VARCHAR(50),
    avatar VARCHAR(500),
    title VARCHAR(255),
    phone VARCHAR(50),
    location VARCHAR(255),
    bio VARCHAR(1000)
);

-- 2. Jobs Table
CREATE TABLE IF NOT EXISTS jobs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    type VARCHAR(50),
    salary VARCHAR(100),
    status VARCHAR(50) NOT NULL DEFAULT 'Open',
    applicants_count INT DEFAULT 0,
    posted_date VARCHAR(50),
    description VARCHAR(2500)
);

-- 3. Job Requirements (Normalized child table)
CREATE TABLE IF NOT EXISTS job_requirements (
    job_id BIGINT NOT NULL,
    requirement VARCHAR(255),
    CONSTRAINT fk_job_requirements_job FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE
);

-- 4. Applications Table
CREATE TABLE IF NOT EXISTS applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT,
    job_title VARCHAR(255) NOT NULL,
    company VARCHAR(255),
    applicant_name VARCHAR(255) NOT NULL,
    applicant_email VARCHAR(255) NOT NULL,
    applied_date VARCHAR(50),
    status VARCHAR(50) NOT NULL DEFAULT 'Under Review',
    stage_number INT DEFAULT 1,
    match_score VARCHAR(50),
    notes VARCHAR(1000),
    cover_note VARCHAR(2000),
    CONSTRAINT fk_applications_job FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE SET NULL
);

-- 5. Interviews Table
CREATE TABLE IF NOT EXISTS interviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    candidate_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    interviewer VARCHAR(255),
    date VARCHAR(50),
    time VARCHAR(50),
    type VARCHAR(100),
    link VARCHAR(500),
    status VARCHAR(50) NOT NULL DEFAULT 'Scheduled'
);

-- ===================================================================
-- Seed Initial Data
-- ===================================================================

INSERT INTO users (name, email, role, department, status, joined_date, avatar, title)
VALUES
('Sarah Connor', 'sarah.admin@enterprise.io', 'ADMIN', 'Platform Engineering', 'Active', 'Jan 15, 2024', 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=150', 'Super Administrator'),
('Michael Scott', 'm.scott@enterprise.io', 'HR', 'Talent Acquisition', 'Active', 'Feb 01, 2024', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150', 'Senior HR Manager'),
('Alex Rivera', 'alex.rivera@gmail.com', 'CANDIDATE', 'Engineering', 'Active', 'Mar 10, 2024', 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150', 'Frontend Engineer'),
('Jessica Pearson', 'jessica.hr@enterprise.io', 'HR', 'Human Resources', 'Active', 'Dec 05, 2023', 'https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=150', 'Head of Talent');

INSERT INTO jobs (title, department, location, type, salary, status, applicants_count, posted_date, description)
VALUES
('Senior Frontend Engineer', 'Engineering', 'Remote (US/EU)', 'Full-time', '$130k - $160k', 'Open', 42, '2 days ago', 'We are seeking an experienced React/TypeScript engineer to scale our core web application architecture.'),
('Full Stack Developer', 'Product', 'San Francisco, CA', 'Full-time', '$120k - $150k', 'Open', 29, '5 days ago', 'Join our cross-functional team building next-generation productivity tools with React, Node.js, and Postgres.'),
('Product Designer (UI/UX)', 'Design', 'Remote', 'Full-time', '$110k - $140k', 'Open', 37, '1 week ago', 'Lead design systems, user journey wireframes, and high-fidelity prototypes for enterprise portals.');
