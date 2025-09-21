# File-Type-Analyzer CLI

Command-line application for file type detection using binary signatures.

## Usage
```
./gradlew :app-cli:run --args="path/to/files"
```
### Example
```
./gradlew :app-cli:run --args="test_files/sample.pdf"
```
### Output
```
sample.pdf: PDF document
```
## Requirements
Java 21+, Gradle 8.x
