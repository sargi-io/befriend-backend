# Check if $env:DATABASE_USERNAME is empty or null
if (-not [string]::IsNullOrWhiteSpace($env:DATABASE_USERNAME)) {
    Write-Host "Database Username is set to: $env:DATABASE_USERNAME"
} else {
    # Prompt the user to enter the database username
    $env:DATABASE_USERNAME = Read-Host "Please enter your database username"
    Write-Host "Database Username set to: $env:DATABASE_USERNAME"
}

# Check if $env:DATABASE_PASSWORD is empty or null
if (-not [string]::IsNullOrWhiteSpace($env:DATABASE_PASSWORD)) {
    Write-Host "Database Password is set. (not displayed for security reasons)"
} else {
    # Prompt the user to enter the database password
    $env:DATABASE_PASSWORD = Read-Host "Please enter your database password"
    Write-Host "Database Password set. (not displayed for security reasons)"
}

& ".\mvnw.cmd" clean -f ".\pom.xml"
& ".\mvnw.cmd" install -f ".\pom.xml"
$env:DATABASE_URL="jdbc:postgresql://localhost:5432/befriend-local"
docker build -t befrined/befriend . --no-cache
docker run --rm -it -e DATABASE_URL="jdbc:postgresql://host.docker.internal:5432/befriend-local" -e DATABASE_USERNAME=$env:DATABASE_USERNAME -e DATABASE_PASSWORD=$env:DATABASE_PASSWORD befrined/befriend