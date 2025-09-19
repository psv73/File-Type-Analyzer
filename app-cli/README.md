# App-CLI

CLI version of File-Type-Analyzer — quick file type detection from the console.

## Run
```bash
./gradlew :app-cli:run --args="path/to/files"
```

## Example
```bash
./gradlew :app-cli:run --args="test_files"
```
Output: one line per file, format `filename: TYPE`.

## Notes
- Uses `core` and `patterns.db` from the classpath.
