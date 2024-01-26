& ".\mvnw.cmd" clean -f ".\pom.xml"
& ".\mvnw.cmd" install -f ".\pom.xml"
docker build -t befrined/befriend . --no-cache
docker run --rm -it -e DATABASE_URL="jdbc:postgresql://host.docker.internal:5432/befriend-local" -e DATABASE_USERNAME=$env:DATABASE_USERNAME -e DATABASE_PASSWORD=$env:DATABASE_PASSWORD befrined/befriend