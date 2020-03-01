#!/bin/bash

repo=/mnt/c/Users/tayal/.m2/repository/
dir=/mnt/d/kulfiKaam/DevelopmentCode/extractor/src/main/resources

name=pixelmed-codec
version=20141206
mvn install:install-file \
    -Dfile=${dir}/pixelmed_codec.jar \
    -DgroupId=com.pixelmed \
    -DartifactId=pixelmed-codec \
    -Dname=pixelmed_codec.jar \
    -Dversion=${version} \
    -Dpackaging=jar \
    -DperformRelease=true \
    -DcreateChecksum=true \
    -DgeneratePom=true \
    -DlocalRepositoryPath=${repo}

name=pixelmed-aiviewer
version=20150512
mvn install:install-file \
    -Dfile=${dir}/aiviewer.jar \
    -DgroupId=com.pixelmed \
    -DartifactId=pixelmed-aiviewer \
    -Dname=aiviewer.jar \
    -Dversion=${version} \
    -Dpackaging=jar \
    -DperformRelease=true \
    -DcreateChecksum=true \
    -DgeneratePom=true \
    -DlocalRepositoryPath=${repo}