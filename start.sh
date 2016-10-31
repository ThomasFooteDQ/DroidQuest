#/bin/sh

RUNPRG="java"
if ! type -p "${RUNPRG}" > /dev/null;
then
  if [ -n "${JAVA_HOME}" ];
  then
    RUNPRG="${JAVA_HOME}/bin/java"
    if [ ! -x "${RUNPRG}" ];
    then
      echo "JAVA_HOME set, but unable to find java.  Please check your java installation."
      exit 1
    fi
  else
    echo "Java 1.6 or newer needed for DQ.  Must be in PATH or JAVA_HOME set."
    exit 1
  fi
fi


if [ -f "target/dq-1.2.jar" ];
then
  echo "Starting DQ."
  ${RUNPRG} -jar "target/dq-1.2.jar"
else
  echo "No jar found, building DQ before running."
  MVNPRG="mvn"
  if ! type -p "${MVNPRG}" > /dev/null;
  then
    if [ -n "${MAVEN_HOME}" ];
    then
      MVNPRG="${MAVEN_HOME}/bin/mvn"
      if [ ! -x "${MVNPRG}" ];
      then
        echo "MAVEN_HOME set, but unable to find mvn executable. Please check your maven installation."
        exit 1
      fi
    else
      echo "Maven required to build DQ.  See maven.apache.org to download."
      exit 1
    fi
  fi
  ${MVNPRG} package
  ${MVNPRG} exec:java -Dexec.mainClass=com.droidquest.DQ
fi
