#!/bin/sh

# 当前目录
APPDIR=`pwd`
# pid文件位置
PIDFILE=$APPDIR/jx-wp.pid
# 选择目录中最新jar包
LAST_JAR=`ls -t |grep jx-wp-.*.jar |head -n 1`
# 如进程中已有pid,则退出命令
if [ -f "$PIDFILE" ] && kill -0 $(cat "$PIDFILE"); then
        echo "$LAST_JAR is already running..."
        exit 1
fi
# 不输出控制台信息,应用在生产环境中
nohup java -Dloader.path=/home/trs/jx-wp-libs -jar $LAST_JAR --spring.profiles.active=production >/dev/null 2>&1 &
# 输出控制台信息,文件持续膨胀仅在调试时用
# nohup java -Dloader.path=/home/trs/jx-wp-libs -jar $LAST_JAR --spring.profiles.active=production >all.log 2>&1 &
# 输出pid到pid文件
echo $! > $PIDFILE
echo "start $LAST_JAR success..."