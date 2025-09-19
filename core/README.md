# Core

Shared logic for detecting file type based on signatures from `patterns.db`.

## Components
- `SignatureDetector` — reads `patterns.db` from classpath and detects file type from bytes.
- Constructors:
  - `SignatureDetector()`
  - `SignatureDetector(int readLimit)` — defines the maximum prefix length (bytes) read from a file.

## Resources
`patterns.db` is located in `core/src/main/resources` and included in the classpath for all modules (CLI, REST).

## Usage
```java
var detector = new SignatureDetector();          // or new SignatureDetector(560);
String type = detector.detect(fileBytes);
```
