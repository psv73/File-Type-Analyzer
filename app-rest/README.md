# App-REST

Spring Boot REST API for file type detection. Supports multiple file uploads.

## Run
```bash
./gradlew :app-rest:bootRun
# optional properties:
./gradlew :app-rest:bootRun --args="--server.port=9090 --readLimit=560"
```

## API
`POST /api/analyze` — `multipart/form-data` with parameter `files` (can be repeated).

```bash
curl -F "files=@test_files/sample.pdf" -F "files=@test_files/my_jpeg.jpg" http://localhost:8080/api/analyze
```
Response:
```json
{ "sample.pdf": "PDF", "my_jpeg.jpg": "JPEG" }
```

### Errors
- `400` `{ "error": "File is empty" }`
- `500` `{ "error": "Internal error" }`

## Tests
Integration tests using `MockMvc`:
```bash
./gradlew :app-rest:test
```
