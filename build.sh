#! /bin/bash
export LANG='en_US.UTF-8'
#/opt/ddp/aiquery_web
ROOT_HOME=$(_pwd=`dirname $0`;cd $_pwd;cd ./;pwd)
echo 'ROOT_HOME=`$ROOT_HOME`'
PACKAGE_NAME=aiquery_web

rm -rf output

echo 'start build'
#编译
npm install
npm run build
echo ' complete build '
echo 'rename'
#重命名
mv  dist ${PACKAGE_NAME}

ENV_NEW=$@
if [ ! -n "$ENV_NEW" ]; then
  echo "customer params IS NULL"
  ENV_LIST=("A" "C"  "F"  "production")
else
  ENV_LIST=${ENV_NEW}
fi

echo "-----------"+${ENV_LIST[@]}+"-----------------"

#分别打各个环境的包
mkdir output
function do_package() {
    rc=$?
    if [[ $rc -ne '0' ]] ; then
      echo 'could not perform tests'; exit $rc
    fi

    #打第一个tar
    tar -cvf ${PACKAGE_NAME}.tar -C ${PACKAGE_NAME}/ .
    #生成生产order
    echo ${PACKAGE_NAME}.tar > order.txt
    #再打包
    mkdir -p ${ROOT_HOME}/output/${1}/${PACKAGE_NAME}/${PACKAGE_NAME}/
    mv ${PACKAGE_NAME}.tar order.txt -t ${ROOT_HOME}/output/${1}/${PACKAGE_NAME}/${PACKAGE_NAME}/
    cd ${ROOT_HOME}/output/${1}/
    tar cvf ${PACKAGE_NAME}.tar ${PACKAGE_NAME}/
    rm -rf ${ROOT_HOME}/output/${1}/${PACKAGE_NAME}
    cd -
    echo 'end package env: '${1}'. path='${ROOT_HOME}'/output/'${1}'/'${PACKAGE_NAME}'.tar'

}

cd ${ROOT_HOME}


echo `pwd`


for ((i=0;i<${#ENV_LIST[@]};i++))
do
    do_package ${ENV_LIST[$i]}
done

#删除
rm -rf ${PACKAGE_NAME}

echo `pwd`


echo 'over'