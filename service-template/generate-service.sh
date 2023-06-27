#!/usr/bin/env sh


echo "Enter group id"
read GROUP_ID
echo ""
echo "Enter artifactId"
read ARTIFACT_ID

mvn clean install -U -DskipTests

mvn archetype:create-from-project  -Dinteractive=false -DkeepParent=true -DpackageName="$GROUP_ID" -DgroupId="$GROUP_ID" -DartifactId="$ARTIFACT_ID" -DarchetypeVersion=1.0.0-SNAPSHOT -Darchetype.filteredExtentions="txt"


cd ./target/generated-sources/archetype

mvn -B -U clean install

cd ../../../../

mvn archetype:generate -Dinteractive=false -DkeepParent=true -DarchetypeGroupId="com.modern.app" -DarchetypeArtifactId="service-template-archetype" -DgroupId="$GROUP_ID" -DartifactId="$ARTIFACT_ID" -DpackageName="$GROUP_ID" -Dversion=1.0.0-SNAPSHOT -DarchetypeVersion=0.0.1-SNAPSHOT

cd ./service-template
mvn clean




cd .././"$ARTIFACT_ID"
cp README.txt README.md
rm README.txt
rm generate-service.sh

mvn clean install -U -DskipTests

