# File-Type-Analyzer (Monorepo)
[![Build](https://github.com/psv73/File-Type-Analyzer/actions/workflows/ci.yml/badge.svg)](https://github.com/psv73/File-Type-Analyzer/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Multi-project: common logic in `core`, two applications: `app-cli` (CLI) and `app-rest` (REST).

## Modules
- `core` — loads `patterns.db`, signature detection logic (`SignatureDetector`).
- `app-cli` — command-line tool for local file analysis.
- `app-rest` — Spring Boot REST API for batch file analysis.

## Quick start
```bash
# CLI
./gradlew :app-cli:run --args="path/to/files"
# REST
./gradlew :app-rest:bootRun
```

## Tests
```bash
./gradlew test
# or per module
./gradlew :app-rest:test
```

## Branches
- `main` — stable version (CLI).
- `feature/rest-api` — REST development, merged into `main` after review.

## Requirements
Java 21/24, Gradle 8.x.
