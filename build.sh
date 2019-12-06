#! /bin/bash
export LANG='en_US.UTF-8'
ROOT_HOME=$(_pwd=`dirname $0`;cd $_pwd;cd ./;pwd)
echo 'ROOT_HOME=`$ROOT_HOME`'
PROFILE_PATH=src/main/resources/__profile
PACKAGE_NAME=datasource
PACKAGE_TYPE=tar.gz
COMPILE_CLASS_PATH=target
#ENV_LIST=("A"  "C"  "F"  "production")
#ENV_LIST=("A" "C"  "F"  "production")
#ENV_LIST=("C")



ENV_NEW=$@


if [ ! -n "$ENV_NEW" ]; then
  echo "customer params IS NULL"
  ENV_LIST=("A" "C"  "F"  "production")
else
  ENV_LIST=${ENV_NEW}
fi

echo "-----------"+${ENV_LIST[@]}+"-----------------"

rm -rf output
mkdir output

function do_package() {

    echo 'start package env:'${1}
    cd ${ROOT_HOME}/target/
    echo `pwd`

    #更换配置文件
    cp ${ROOT_HOME}/target/classes/__profile/${1}/* ${ROOT_HOME}/target/config/
    tar uvf ${PACKAGE_NAME}.tar config/

#    if [ ${1} = 'production' ]; then
#     mkdir bin/
#     rm -f ${ROOT_HOME}/target/assembly-bin/start.sh
#     mv ${ROOT_HOME}/target/assembly-bin/start.bat ${ROOT_HOME}/target/assembly-bin/start.sh
#     cp ${ROOT_HOME}/target/assembly-bin/* ${ROOT_HOME}/target/bin/
#     tar uvf ${PACKAGE_NAME}.tar bin/
#     echo -----mode
#    fi

    #移出去(query-platform/xxx-a.tar)
    cp ${PACKAGE_NAME}.tar ${ROOT_HOME}/
    cd ..

    #打第一个tar(追加部署脚本)
    tar uvf ${PACKAGE_NAME}.tar build-start.sh  build-stop.sh

    #生成生产order
    echo ${PACKAGE_NAME}.tar > order.txt

    #创建层级
    mkdir -p ${ROOT_HOME}/output/${1}/${PACKAGE_NAME}/${PACKAGE_NAME}/

    #移动到各环境对应的目录
    mv ${PACKAGE_NAME}.tar order.txt -t ${ROOT_HOME}/output/${1}/${PACKAGE_NAME}/${PACKAGE_NAME}/

    #再打包
    cd ${ROOT_HOME}/output/${1}/
    tar cvf ${PACKAGE_NAME}.tar ${PACKAGE_NAME}/

    #删除打包后的源目录
    rm -rf ${ROOT_HOME}/output/${1}/${PACKAGE_NAME}

    echo 'end package env: '${1}'. path='${ROOT_HOME}'/output/'${1}'/'${PACKAGE_NAME}'.tar'
}


cd ${ROOT_HOME}

echo `pwd`

echo 'start package env:'${ENV_LIST[0]}

mvn clean  package -P ${ENV_LIST[0]}
#重命名
cd target/
mv datasource-${ENV_LIST[0]}.tar ${PACKAGE_NAME}.tar

mkdir config/

#替换其他环境的配置文件，并打包
for ((i=0;i<${#ENV_LIST[@]};i++))
do
    do_package ${ENV_LIST[$i]}
done

echo 'over'
