#!/bin/sh

set -e

# java env
export JAVA_HOME=/data/jdk1.8.0_212
export JRE_HOME=$JAVA_HOME/jre
# system env
export dptms=test
export dptmsSecret={密钥}

model=$1
machineIp={服务器出口ip}

if [ -z ${model} ]; then
    echo "ERROR model is empty"
    exit
fi

xmx=256
xms=128

case "$model" in
    dptms-admin|dptms-config|dptms-eureka)
        xmx=128
        xms=64
        ;;
    *)
        xmx=256
        xms=128
        ;;
esac

SERVICE_DIR=${model}

cd ${SERVICE_DIR}

JAR_NAME=${model}\.jar

case "$2" in

    start)
        nohup java -Xms${xms}m -Xmx${xmx}m -jar -Dfile.encoding=utf-8 ${JAR_NAME} --eureka.instance.hostname=${machineIp} > /data/dptms/${SERVICE_DIR}/logs/${model}_jar.log 2>&1 &
        echo "java -Xms${xms}m -Xmx${xmx}m -jar -Dfile.encoding=utf-8 ${JAR_NAME} --eureka.instance.hostname=${machineIp} > /data/dptms/${SERVICE_DIR}/logs/${model}_jar.log 2>&1 &"
        ;;

    stop)
        P_ID=$(ps -ef | grep "$JAR_NAME" | grep -v "grep" | awk '{print $2}')
        kill ${P_ID}

        sleep 5
        P_ID=$(ps -ef | grep "$JAR_NAME" | grep -v "grep" | awk '{print $2}')
        if [ "${P_ID}" == "" ]; then
            echo "=== $model process not exists or stop success"
        else
            echo "=== $model process pid is:$P_ID"
            echo "=== begin kill $model process, pid is:$P_ID"
            kill -9 ${P_ID}
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "=== restart $model"
        ;;

    *)
        ## restart
        $0 stop
        sleep 2
        $0 start
        ;;
esac
exit 0
