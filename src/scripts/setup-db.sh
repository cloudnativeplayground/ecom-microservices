#!/bin/bash

# Ensure the script is run with sudo if necessary
if [ "$(id -u)" -ne 0 ]; then
  echo "This script must be run as root or with sudo"
  exit 1
fi

# Database credentials
DB_NAME="ecommerce_db"
DB_USER="ecommerce_user"
DB_PASSWORD="ecommerce_password"

# MySQL installation check and install
echo "Checking if MySQL is installed..."

if ! command -v mysql &> /dev/null
then
    echo "MySQL not found. Installing MySQL..."
    apt-get update
    apt-get install -y mysql-server
    service mysql start
else
    echo "MySQL is already installed."
fi

# Secure MySQL installation (optional but recommended)
echo "Running MySQL secure installation..."
mysql_secure_installation

# Create the MySQL database and user
echo "Creating database and user..."

mysql -u root -e "CREATE DATABASE IF NOT EXISTS $DB_NAME;"
mysql -u root -e "CREATE USER IF NOT EXISTS '$DB_USER'@'localhost' IDENTIFIED BY '$DB_PASSWORD';"
mysql -u root -e "GRANT ALL PRIVILEGES ON $DB_NAME.* TO '$DB_USER'@'localhost';"
mysql -u root -e "FLUSH PRIVILEGES;"

# Set up schema for the application (optional: you can remove this if you are using JPA to create schema)
echo "Setting up schema for the ecommerce database..."

mysql -u root -e "USE $DB_NAME; CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);"

# Insert sample data
echo "Inserting sample data into the products table..."

mysql -u root -e "USE $DB_NAME; INSERT INTO products (name, description, price) VALUES
    ('Product 1', 'Description for product 1', 100.00),
    ('Product 2', 'Description for product 2', 150.00),
    ('Product 3', 'Description for product 3', 200.00);"

# Check if the database is running and accessible
echo "Checking database connectivity..."

MYSQL_CONNECTION=$(mysql -u $DB_USER -p$DB_PASSWORD -e "USE $DB_NAME; SELECT 1;" 2>&1)

if [[ "$MYSQL_CONNECTION" == *"1"* ]]; then
    echo "Database setup successful and connection is working!"
else
    echo "Database setup failed. Please check for errors above."
    exit 1
fi

# Display database connection details
echo "Database setup complete."
echo "Database Name: $DB_NAME"
echo "Database User: $DB_USER"
echo "Database Password: $DB_PASSWORD"

# Success message
echo "Database and initial setup are ready for use!"
# End of script