# 🚀 Golang(Fiber) vs Java Spring의 장점
✅ Go는 낮은 메모리 사용량, 간결한 코드 구조, 빠른 실행 속도 및 효율적인 Goroutine 기반 동시성 처리로 최적화된 성능을 제공 🚀

## 1. 성능(Performance)
- **Golang은 네이티브 바이너리로 컴파일되므로 실행 속도가 빠름**  
  → Java Spring은 JVM 위에서 동작하여 **GC(Garbage Collection)** 등의 오버헤드가 발생하지만, **Golang은 단일 바이너리 실행**이므로 더 빠름.  
- **Fiber는 Zero Allocation 기반의 빠른 HTTP 프레임워크**  
  → Java의 Spring Boot는 **Reflection 기반이라 초기 로딩 속도가 느리지만**, **Golang은 런타임 성능이 더 뛰어남**.

## 2. 메모리 사용량 절감
- **Java Spring은 메모리 사용량이 높은 편**  
  → Spring Boot 애플리케이션은 **JVM에서 실행되므로 메모리 사용량이 많고 보통 최소 200~300MB 이상 필요**  
- **Golang(Fiber)은 훨씬 적은 메모리 사용**  
  → Fiber 기반 애플리케이션은 **10~30MB 수준의 낮은 메모리 사용량**을 유지 가능  
  → **컨테이너화(Docker) 및 클라우드 환경에서 운영 비용 절감 가능**  

## 3. 간결한 코드(개발 속도)
- **Golang은 Spring Boot보다 코드가 훨씬 간결함**  
  → Spring Boot에서는 **컨트롤러, 서비스, 리포지토리 계층을 분리해야 하지만**, **Golang에서는 단순한 HTTP 핸들러 함수만으로 API 구현 가능**  
  → 위 코드에서도 `HandleKeywordLog` 등의 함수가 매우 직관적이고 간단함  
- **컴파일 언어지만 빠른 개발 가능**  
  → Spring Boot에서는 **application.yml, Controller, Service, Repository, Entity** 등 설정할 게 많음  
  → **Golang은 단일 파일로 빠르게 API 개발 가능**  

## 4. 배포 및 실행 속도
- **Go는 실행 파일 하나만으로 배포 가능**  
  → `go build` 실행하면 **단일 바이너리 생성**  
  → **Spring Boot는 JAR 파일을 만들고, JVM을 통해 실행해야 해서 실행 속도가 느림**  
- **Spring Boot는 보통 5~10초 이상 걸려야 서버 시작**  
  → **Golang(Fiber)은 거의 즉각적으로 서버 실행 가능**  

## 5. 동시성 처리(Goroutine vs Spring Thread)
- **Golang은 Goroutine을 통해 가벼운 동시성 제공**  
  → 수천 개의 요청이 들어와도 **Goroutine이 효율적으로 관리**  
  → 반면, **Spring Boot는 스레드 기반 처리이므로, 일정 개수를 넘어가면 성능 저하 발생**  
  → **실시간 로그 처리 및 고성능 API 서버에 적합**  

## 6. 컨테이너 및 클라우드 친화적
- **Golang은 작은 바이너리 크기(10~20MB)로 Docker 컨테이너 최적화 가능**  
- **Spring Boot는 최소 JDK 200~300MB 이상 필요**  
  → **컨테이너 배포 시 Golang이 훨씬 가벼워 운영 비용 절감 가능**  


# 프로젝트 기술 사항
- log 수집
- swagger 사용

# 사용법

## 초기화
```bash
go mod init log-collector    # 프로젝트에 Go 모듈 초기화
go mod tidy                 # 필요한 의존성을 자동으로 다운로드
```

## 실행
```bash
swag init
go run main.go
```

### 종료
```plantext
ctrl + c
```

## 테스트
```bash
go test ./...
```


## swag 문서 생성
주석을 작성한 후 다음 명령어를 실행하여 Swagger 문서를 생성합니다.

```bash
swag init
```

# [swag](http://localhost:8089/swagger/index.html)
