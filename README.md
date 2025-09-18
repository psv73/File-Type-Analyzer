# File-Type-Analyzer

Educational project to detect file types based on signatures.

## Structure
- `core`
    - `model/PatternDBRecord.java` — signature definition
    - `service/SignatureDetector.java` — file type detection
- `app-cli` — console application (analyzes a single file or a whole folder)
- `app-rest` — REST API (under development)

## Features
- Reads the first 560 bytes of a file (constant in CLI).
- `patterns.db` with signatures stored in `core/resources`.
- CLI accepts both file and directory path:
  ```bash
  # analyze single file
  gradlew :app-cli:run --args="test_files/sample.pdf"

  # analyze folder
  gradlew :app-cli:run --args="test_files"
  ```

## Roadmap
- Move parameters into `application.properties` (for REST).
- Extend signatures database.
- Add flexible output formats (CSV/JSON).

## Tech stack
- Java 24
- Gradle 8
- Spring Boot 3.5 (for REST module)
