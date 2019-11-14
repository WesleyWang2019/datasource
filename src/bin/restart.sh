#! /bin/bash

# 项目名称
APPLICATION="data-source"

# 项目启动jar包名称
APPLICATION_JAR="data-source.jar"

# 停服
echo stop ${APPLICATION} Application...
sh /opt/demo/datasource/bin/stop.sh

# 启动服务
echo start ${APPLICATION} Application...
sh  /opt/demo/datasource/bin/start.sh