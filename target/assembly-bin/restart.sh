#! /bin/bash

# 项目名称
APPLICATION="datasource"

# 项目启动jar包名称
APPLICATION_JAR="datasource.jar"

# 停服
echo stop ${APPLICATION} Application...
sh /opt/demo/datasource/bin/stop.sh

# 启动服务
echo start ${APPLICATION} Application...
sh  /opt/demo/datasource/bin/start.sh