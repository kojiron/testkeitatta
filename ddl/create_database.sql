GRANT USAGE ON *.* TO 'iot'@'localhost';
DROP USER 'iot'@'localhost' ;
DROP DATABASE IF EXISTS iotserver;
CREATE DATABASE IF NOT EXISTS iotserver;
CREATE USER 'iot'@'localhost' identified by 'iot';
GRANT ALL ON iotserver.* TO 'iot'@'localhost';
