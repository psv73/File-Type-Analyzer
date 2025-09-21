# File-Type-Analyzer Core

Shared logic for detecting file types based on binary signatures from `patterns.db`.

## Components
- `SignatureAnalyzer` — reads `patterns.db` from classpath and detects file type from bytes.
- `PatternDBRecord` — represents a single record from `patterns.db`.
- `AnalyzerException` — common exception type for analyzer errors.

## Resources
`patterns.db` is located in `core/src/main/resources` and included in the classpath for all modules (CLI, REST).

## Usage
```java
var analyzer = new SignatureAnalyzer();          // or new SignatureAnalyzer(560);
String type = analyzer.analyze(fileBytes);
```
## Requirements
Java 21+, Gradle 8.x