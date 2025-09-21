# Core

Shared logic for detecting file type based on signatures from `patterns.db`.

## Components
- `SignatureAnalyzer` — reads `patterns.db` from classpath and detects file type from bytes.
- Constructors:
  - `SignatureAnalyzer()`
  - `SignatureAnalyzer(int readLimit)` — defines the maximum prefix length (bytes) read from a file.

## Resources
`patterns.db` is located in `core/src/main/resources` and included in the classpath for all modules (CLI, REST).

## Usage
```java
var analyzer = new SignatureDetector();          // or new SignatureDetector(560);
String type = analyzer.analyze(fileBytes);
```
