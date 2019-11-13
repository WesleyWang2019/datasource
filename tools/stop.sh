#!/bin/sh

# 当前目录
APPDIR=`pwd`
# pid文件位置
PIDFILE=$APPDIR/jx-wp.pid
# 如进程中没有pid则不处理,如有pid则杀进程,删除pid文件
if [ ! -f "$PIDFILE" ] || ! kill -0 "$(cat "$PIDFILE")"; then
        echo "jx-wp not running..."
else
        echo "stopping jx-wp..."
        PID="$(cat "$PIDFILE")"
        kill -9 $PID
        rm "$PIDFILE"
        echo "...jx-wp stopped"
fi