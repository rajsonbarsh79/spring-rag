# 📘 Spring Boot AI Project – Production Guidelines

## 🎯 Purpose
This document defines rules, constraints, and best practices for generating a production-ready Spring Boot AI project.

---

# 🧠 Core Principles
- Production-first mindset
- Performance optimized
- Thread-safe and scalable
- Memory-efficient
- Clean, maintainable, and testable
- Secure by default
- Minimal latency & high throughput

---

# 🏗️ Project Structure Standard
com.projectname
│── config
│── controller
│── service
│── repository
│── dto
│── entity
│── mapper
│── exception
│── util
│── security
│── ai

---

# ⚙️ Code Generation Rules

## Clean Code
- Follow SOLID principles
- Avoid God classes
- Keep methods < 40 lines
- No hardcoded values

## Thread Safety
- Avoid shared mutable state
- Use stateless services
- Configure thread pools properly

## Memory Management
- Use try-with-resources
- Avoid large in-memory collections
- Use pagination

## Performance
- Use connection pooling
- Avoid blocking calls
- Enable compression

## Logging
- Use structured logging
- Never log sensitive data

## Exception Handling
- Use @ControllerAdvice
- Standard error response

## API Design
- RESTful APIs
- Proper HTTP status codes
- Input validation

## Database
- Avoid eager fetching
- Use transactions
- Prevent N+1 queries

## AI Integration
- Isolate AI logic
- Use timeouts and retries

## Security
- Use Spring Security
- Prevent SQL Injection, XSS

## Testing
- Unit + Integration tests
- Minimum 70% coverage

## Deployment
- Docker support
- Graceful shutdown
- External configs

---

# 🚫 Anti-Patterns
- Blocking I/O in controllers
- Hardcoded credentials
- Unbounded thread pools

---

# ✅ Final Checklist
- Thread-safe
- No memory leaks
- Optimized queries
- Secure
- Production-ready
