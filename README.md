# <div align="center">Spring Security JWT Auth0 Module</div>
<div align="center">

[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![GitHub Issues](https://img.shields.io/github/issues/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/issues)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/pulls)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/commits)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---
<p align="center">
This is a ready-to-use project starter module, fully configured with JWT authentication and authorization using Spring Boot 3 and Spring Security 6. No deprecated methods have been used in this project.
</p>

## Prerequisite
* JDK 21
* Spring Boot >= 3.3.1
* Gradle - Groovy
* <a href="https://www.base64encode.org/">Base64 Encoded Secret Key</a>

## Features
* Registration 
* Login
* API method security with roles

## RESTful API Details
### Registration
1. `POST /api/auth/register`
2. `POST /api/auth/admin/register`

### Login
1. `POST /api/auth/login`

### Authorization
1. `GET /api/user/`
2. `GET /api/admin/`