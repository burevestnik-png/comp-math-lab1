del gauss.jar
del solution-result.json
cd ..
gradlew clean jar && move .\build\libs\gauss.jar .\test\ && cd .\test
