#!/bin/bash

TARGET=$1
ROOT=$(pwd)
PROPERTIES_PATH=/home/jenkins/builder/properties

# Functions
if_error()
{
    if [ $1 -ne 0 ]
    then 
        echo -e "\033[31mfailed!error code:$1\033[0m"
        exit $1
    else
        echo -e "\033[32msuccess!\033[0m"
    fi
}
dump()
{
    echo -e "\033[44m$1\033[0m"
}
doSed()
{
	if [ "x$(uname)" == "xDarwin" ]
	then
        	sed -i "" $@
	else
        	sed -i $@
	fi
}
updateProperties()
{
	dump "Update properties"
	# MC
        sed -i "s/^servers=.*$/servers=${MC_HOST}:${MC_PORT}/g" ${ROOT}/yz-crm-api/src/main/resources/membercache.properties
        sed -i "s/^initConn=.*$/initConn=${MC_INIT_CONN}/g" ${ROOT}/yz-crm-api/src/main/resources/membercache.properties
        sed -i "s/^minConn=.*$/minConn=${MC_MIN_CONN}/g" ${ROOT}/yz-crm-api/src/main/resources/membercache.properties
        sed -i "s/^maxConn=.*$/maxConn=${MC_MAX_CONN}/g" ${ROOT}/yz-crm-api/src/main/resources/membercache.properties
        # Redis
        sed -i "s/^redisIp=.*$/redisIp=${REDIS_HOST}/g" ${ROOT}/yz-crm-api/src/main/resources/redisConfig.properties
        sed -i "s/^redisPort=.*$/redisPort=${REDIS_PORT}/g" ${ROOT}/yz-crm-api/src/main/resources/redisConfig.properties
        sed -i "s/^redisDb_index=.*$/redisDb_index=${REDIS_DB}/" ${ROOT}/yz-crm-api/src/main/resources/redisConfig.properties
	# MySQL
	sed -i "s/^datasource_yzcrm_r.url=.*/datasource_yzcrm_r.url=jdbc:mysql:\/\/${MYSQL_READ_HOST}:${MYSQL_READ_PORT}\/yzadmin?useUnicode=true\&amp;characterEncoding=UTF8MB4\&amp;zeroDateTimeBehavior=convertToNull\&amp;autoReconnect=true/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/^datasource_yzcrm_r.username=.*$/datasource_yzcrm_r.username=${MYSQL_READ_USERNAME}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/^datasource_yzcrm_r.password=.*$/datasource_yzcrm_r.password=${MYSQL_READ_PASSWORD}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/^datasource_yzcrm_r.initialPoolSize=.*$/datasource_yzcrm_r.initialPoolSize=${MYSQL_READ_INIT_POOL_SIZE}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/^datasource_yzcrm_r.maxPoolSize=.*$/datasource_yzcrm_r.maxPoolSize=${MYSQL_READ_MAX_POOL_SIZE}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/^datasource_yzcrm_r.minPoolSize=.*$/datasource_yzcrm_r.minPoolSize=${MYSQL_READ_MIN_POOL_SIZE}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties

	sed -i "s/datasource_yzcrm_w.url=.*/datasource_yzcrm_w.url=jdbc:mysql:\/\/${MYSQL_WRITE_HOST}:${MYSQL_WRITE_PORT}\/yzadmin?useUnicode=true\&amp;characterEncoding=UTF8MB4\&amp;zeroDateTimeBehavior=convertToNull\&amp;autoReconnect=true/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/datasource_yzcrm_w.username=.*$/datasource_yzcrm_w.username=${MYSQL_WRITE_USERNAME}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/datasource_yzcrm_w.password=.*$/datasource_yzcrm_w.password=${MYSQL_WRITE_PASSWORD}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/datasource_yzcrm_w.initialPoolSize=.*$/datasource_yzcrm_w.initialPoolSize=${MYSQL_WRITE_INIT_POOL_SIZE}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/datasource_yzcrm_w.maxPoolSize=.*$/datasource_yzcrm_w.maxPoolSize=${MYSQL_WRITE_MAX_POOL_SIZE}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
	sed -i "s/datasource_yzcrm_w.minPoolSize=.*$/datasource_yzcrm_w.minPoolSize=${MYSQL_WRITE_MIN_POOL_SIZE}/" ${ROOT}/yz-crm-api/src/main/resources/yzcrm-db.properties
}


# Main
if [[ -z $TARGET ]]; then
	echo "Usage: build.sh {dev|test|release}"
	exit -1
fi

if [[ -d $PROPERTIES_PATH ]];
        then
                echo "read properties success"
        else
                echo "read properties fail"
                exit -1
fi

case "$TARGET" in
	dev)
		;;
	test)
		. ${PROPERTIES_PATH}/yz-crm/test
		updateProperties
		;;
	release)
		. ${PROPERTIES_PATH}/yz-crm/release
		updateProperties
		;;
	*)
		echo "Usage: build.sh {dev|test|release}"
		exit -1
		;;
esac

dump "Build"
mvn clean install -Dmaven.test.skip=true
if_error $?

dump "Deploy"
mkdir deploy
cp ${ROOT}/yz-crm-api/target/yz-crm-api*.war ./deploy/bk-crm-api-$(date +%Y%m%d%H%M%S).war
cp -f ${ROOT}/yz-crm-api/target/yz-crm-api*.war ./deploy/yz-crm-api.war
if_error $?

