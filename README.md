# Spring Security JWT Auth0 Module

[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![GitHub Issues](https://img.shields.io/github/issues/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/issues)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/pulls)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/nadimnesar/spring-security-jwt-authentication-module.svg)](https://github.com/nadimnesar/spring-security-jwt-authentication-module/commits)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)
---
This project is a ready-to-use starter module for Auth0 authentication and authorization (JSON Web Token-based) using Spring Boot and Spring Security. It avoids deprecated methods, ensuring compatibility and security.

## Prerequisite

- JDK 21
- Spring Boot >= 3.3.1
- Spring Security >= 6.3.1
- H2 Database
- Gradle (Groovy)
- Base64 Encoded Secret Key (generate at [Base64Encode.org](https://www.base64encode.org/))

## Features

* Registration
* Login
* API method security with roles

## RESTful API Details

### Registration

- `POST /api/auth/register`
  <img src="/src/main/resources/static/img/auth0-postman-1.png" alt="postman">
- `POST /api/auth/admin/register`
  <img src="/src/main/resources/static/img/auth0-postman-2.png" alt="postman">

### Login

- `POST /api/auth/login`
  <img src="/src/main/resources/static/img/auth0-postman-3.png" alt="postman">

### Authorization

- `GET /api/user/`
  <img src="/src/main/resources/static/img/auth0-postman-4.png" alt="postman">
- `GET /api/admin/`
  <img src="/src/main/resources/static/img/auth0-postman-5.png" alt="postman">
