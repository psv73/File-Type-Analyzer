# File-Type-Analyzer (Monorepo)
[![Build](https://github.com/psv73/File-Type-Analyzer/actions/workflows/ci.yml/badge.svg)](https://github.com/psv73/File-Type-Analyzer/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Multi-project: common logic in `core`, two apps: `app-cli` (CLI) and `app-rest` (REST).

## Features
- Binary signature detection (`SignatureAnalyzer`)
- CLI tool & Spring Boot REST API
- Multi-file upload (`/api/analyze`)
- Validation, global error handling, CI, tests

## Modules
- `core` — loads `patterns.db`, signature detection logic.
- `app-cli` — command-line tool for local file analysis.
- `app-rest` — Spring Boot REST API for batch analysis.

## Quick start
### CLI
```bash
./gradlew :app-cli:run --args="path/to/files"
```

### REST
```bash
./gradlew :app-rest:bootRun
```
### optional:
```bash
./gradlew :app-rest:bootRun --args="--server.port=9090 --readLimit=560"
```
### API
```bash
curl -F "files=@test_files/sample.pdf" \
     -F "files=@test_files/my_jpeg.jpg" \
     http://localhost:8080/api/detect
```
### Test
``` bash
./gradlew test
```
or per module
```bash
./gradlew :app-rest:test
```
## Requirements
Java 21+, Gradle 8.x