#!/bin/sh

# 项目名称
APPLICATION="datasource"
# 项目启动jar包名称
APPLICATION_JAR="datasource.jar"


ps -ef|grep ${APPLICATION_JAR}|grep -v grep|grep -v kill|awk '{print $2}'|xargs -I '{}' kill -9 {}
echo 'Stop Process...'
