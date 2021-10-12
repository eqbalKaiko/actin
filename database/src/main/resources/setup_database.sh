#!/usr/bin/env bash

db_generate_script=$1

os=$(uname)
if [[ ${os} = "Darwin" ]]; then
    script_epoch=$(stat -t '%s' "${db_generate_script}" | cut -d\" -f4)
else
    # Assume Linux with GNU date syntax
    script_epoch=$(date -r "${db_generate_script}" '+%s')
fi

db_epoch=$(mysql --defaults-file=~/actin.login << HERE | sed -n '2p'
SELECT UNIX_TIMESTAMP(MAX(create_time)) db_creation FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = "actin_test";
HERE
)

echo "[INFO]: Script Epoch: ${script_epoch}"
echo "[INFO]: DB Epoch: ${db_epoch}"

if [[ "$db_epoch" = "NULL" || ${script_epoch} -gt ${db_epoch} ]];
then
    echo "[INFO] Rebuilding ACTIN test database based on ${db_generate_script}"
    mysql --defaults-file=~/actin.login < "${db_generate_script}"
fi
