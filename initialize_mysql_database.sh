# initialize_mysql_database.sh

mysql -u root -p $MYSQL_ROOT_PASSWORD;
CREATE DATABASE $MYSQL_DATABASE;
CREATE DATABASE $DB_DATABASE;
GRANT ALL PRIVILEGES ON $MYSQL_DATABASE.* TO 'root'@'%' WITH GRANT OPTION;
CREATE DATABASE keycloak;
CREATE USER 'keycloak'@'localhost' IDENTIFIED BY 'Keycloak123$';
GRANT ALL PRIVILEGES ON keycloak.* TO 'keycloak'@'localhost';
FLUSH privileges;

