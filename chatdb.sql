CREATE DATABASE IF NOT EXISTS chatapp_db;
USE chatapp_db;

-- Tabel users (CRUD lengkap)
CREATE TABLE IF NOT EXISTS users (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('admin', 'user') DEFAULT 'user',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabel messages (history chat)
CREATE TABLE IF NOT EXISTS messages (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    sender      VARCHAR(50)  NOT NULL,
    content     TEXT         NOT NULL,
    sent_at     DATETIME     DEFAULT CURRENT_TIMESTAMP
);

-- Data awal: akun admin dan beberapa user contoh
INSERT IGNORE INTO users (username, password, role) VALUES
    ('admin',  'admin123',  'admin'),
    ('budi',   'budi123',   'user'),
    ('ani',    'ani123',    'user'),
    ('caca',   'caca123',   'user');
