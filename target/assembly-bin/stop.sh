#!/bin/sh

# 项目名称
APPLICATION="data-source"
# 项目启动jar包名称
APPLICATION_JAR="data-source.jar"


ps -ef|grep ${APPLICATION_JAR}|grep -v grep|grep -v kill|awk '{print $2}'|xargs -I '{}' kill -9 {}
echo 'Stop Process...'
