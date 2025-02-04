USE todo;

CREATE TABLE IF NOT EXISTS todo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255) NOT NULL,
    done BOOLEAN NOT NULL DEFAULT FALSE
);

GRANT ALL PRIVILEGES ON todo.* TO 'tester'@'%';
FLUSH PRIVILEGES;
