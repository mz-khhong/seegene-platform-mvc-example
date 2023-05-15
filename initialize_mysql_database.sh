# initialize_mysql_database.sh

mysql -u root -p $MYSQL_ROOT_PASSWORD;
CREATE DATABASE $MYSQL_DATABASE;
GRANT ALL PRIVILEGES ON $MYSQL_DATABASE.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH privileges;

