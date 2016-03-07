#!/bin/bash

MYSQL_CMD="mysql"
MYSQL_PASSWORD=""
MYSQL_INSTANCE="iotserver"
CREATE_DATABASE_SQL="../ddl/create_database.sql"
CREATE_TABLE_SQL="../ddl/create_table.sql"
CREATE_MASTER_SQL="../ddl/create_master.sql"

if [ ! -z ${MYSQL_PASSWORD} ]; then
  PASSWORD_OPTION="-p${MYSQL_PASSWORD}"
else
  PASSWORD_OPTION=""
fi

$MYSQL_CMD -u root ${PASSWORD_OPTION} < ${CREATE_DATABASE_SQL}
$MYSQL_CMD -u root ${PASSWORD_OPTION} ${MYSQL_INSTANCE} < ${CREATE_TABLE_SQL}


