# Spring Security JWT Auth0 Module


[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![GitHub Issues](https://img.shields.io/github/issues/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/issues)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/pulls)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/commits)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)
---
This project is a ready-to-use starter module for Auth0 JWT-based authentication and authorization using Spring Boot 3 and Spring Security 6. It avoids deprecated methods, ensuring compatibility and security.

## Prerequisite
- JDK 21
- Spring Boot >= 3.3.1
- Gradle (Groovy)
- Base64 Encoded Secret Key (generate at [Base64Encode.org](https://www.base64encode.org/))

## Features
* Registration
* Login
* API method security with roles

## RESTful API Details
### Registration
- `POST /api/auth/register`
- `POST /api/auth/admin/register`

### Login
- `POST /api/auth/login`

### Authorization
- `GET /api/user/`
- `GET /api/admin/`