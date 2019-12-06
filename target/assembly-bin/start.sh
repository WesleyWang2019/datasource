#!/bin/bash
# 项目名称
APPLICATION="datasource"
# 项目启动jar包名称
APPLICATION_JAR="datasource.jar"


BIN_PATH=$(cd `dirname $0`; pwd)

cd `dirname $0`

cd ..

`pwd`

BASE_PATH=`pwd`

CONFIG_DIR=${BASE_PATH}"/config/"

LOG_DIR="/var/log/demo/datasource/"

LOG_FILE="${APPLICATION}.log"

LOG_PATH="${LOG_DIR}/${LOG_FILE}"

LOG_BACK_DIR="${LOG_DIR}/back/"

LOG_STARTUP_PATH="${LOG_DIR}/startup.log"



# 当前时间
NOW=`date +'%Y-%m-%m-%H-%M-%S'`
NOW_PRETTY=`date +'%Y-%m-%m %H:%M:%S'`

# 启动日志
STARTUP_LOG="================================================ ${NOW_PRETTY} ================================================\n"

if [ ! -d "${LOG_DIR}" ]; then
  mkdir -p "${LOG_DIR}"
fi

if [ ! -d "${LOG_BACK_DIR}" ]; then
  mkdir -p "${LOG_BACK_DIR}"
fi

# 如果项目运行日志存在,则重命名备份
if [ -f "${LOG_PATH}" ]; then
	mv ${LOG_PATH} "${LOG_BACK_DIR}/${APPLICATION}_back_${NOW}.log"
fi

# 创建新的项目运行日志
echo "" > ${LOG_PATH}

# 如果项目启动日志不存在,则创建,否则追加
#echo "${STARTUP_LOG}" >> ${LOG_STARTUP_PATH}


JAVA_OPT="-server -Xmn1024m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=256m  -verbose:gc -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+DisableExplicitGC"

STARTUP_LOG="${STARTUP_LOG}application name: ${APPLICATION}\n"

STARTUP_LOG="${STARTUP_LOG}application jar  name: ${APPLICATION_JAR}\n"

STARTUP_LOG="${STARTUP_LOG}application root path: ${BASE_PATH}\n"

STARTUP_BIN="${STARTUP_LOG}application root path: ${BIN_DIR}\n"

STARTUP_LOG="${STARTUP_LOG}application bin  path: ${BIN_PATH}\n"

STARTUP_LOG="${STARTUP_LOG}application log  path: ${LOG_PATH}\n"

STARTUP_LOG="${STARTUP_LOG}application JAVA_OPT : ${JAVA_OPT}\n"

STARTUP_LOG="${STARTUP_LOG}application CONFIG_OPT : ${CONFIG_OPT}\n"


# 打印启动命令
STARTUP_LOG="${STARTUP_LOG}application startup command: nohup java  ${JAVA_OPT}  -jar ${BASE_PATH}/boot/${APPLICATION_JAR} > ${LOG_PATH} 2>&1 &\n"

nohup java  ${JAVA_OPT}  -jar ${BASE_PATH}/boot/${APPLICATION_JAR}  > ${LOG_PATH} 2>&1 &


# 进程ID
PID=$(ps -ef | grep "${APPLICATION_JAR}" | grep -v grep | awk '{ print $2 }')
STARTUP_LOG="${STARTUP_LOG}application pid: ${PID}\n"

# 启动日志追加到启动日志文件中
echo -e ${STARTUP_LOG} >> ${LOG_STARTUP_PATH}
# 打印启动日志
echo -e ${STARTUP_LOG}

