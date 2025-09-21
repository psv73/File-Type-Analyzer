# File-Type-Analyzer REST API

Spring Boot REST service for file type detection using binary signatures.

## Endpoints
- `POST /api/analyze` — analyze one or more uploaded files
- `GET /api/health` — health check

## Usage
Start the server:
```bash
./gradlew :app-rest:bootRun
```
### Send request:
```
curl -F "files=@test_files/sample.pdf" \
     -F "files=@test_files/my_jpeg.jpg" \
     http://localhost:8080/api/analyze
```
### Response:
```
[
  { "file": "sample.pdf", "type": "PDF document" },
  { "file": "my_jpeg.jpg", "type": "JPEG image" }
]
```
## Tests
```
./gradlew :app-rest:test
```
## Requirements
Java 21+, Gradle 8.x