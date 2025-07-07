# logstash jdbc 설치 
```bash
wget https://mariadb.com/downloads/connectors/connectors-data-access/java8-connector/mariadb-java-client-3.4.1.jar
```

# 포팅 방법

# Java 포팅 및 SSL 인증서 적용 가이드

## ✅ 현재 Java 버전 확인

```bash
java -version
```

## ✅ JAVA\_HOME 환경 변수 확인

```bash
echo $JAVA_HOME
```

## ✅ JAVA\_HOME 설정 (Java 17)

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

### 🗂️ 영구적으로 적용하기

```bash
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc
```

## ✅ JAVA\_HOME 설정 확인

```bash
echo $JAVA_HOME
```

## ✅ CA 인증서를 Java 트러스트스토어에 추가

```bash
sudo keytool -importcert \
  -file /home/sihyun/certs/ca/ca.crt \
  -alias elasticsearch \
  -keystore $JAVA_HOME/lib/security/cacerts \
  -storepass changeit
```

## ✅ JVM 옵션에 트러스트스토어 설정 추가

```bash
export JAVA_OPTS="-Djavax.net.ssl.trustStore=$JAVA_HOME/lib/security/cacerts -Djavax.net.ssl.trustStorePassword=changeit"
```

### 실행 예시

```bash
java $JAVA_OPTS -jar your-application.jar
```

## ✅ ElasticsearchService 클래스 수정

```java
private CloseableHttpClient createHttpClient() throws Exception {
    SSLContextBuilder sslBuilder = new SSLContextBuilder();
    sslBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

    return HttpClients.custom()
        .setSSLContext(sslBuilder.build())
        .setSSLHostnameVerifier(new DefaultHostnameVerifier())
        .setDefaultCredentialsProvider(getCredentialsProvider())
        .build();
}

private CredentialsProvider getCredentialsProvider() {
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(
        new AuthScope(elasticsearchHost, Integer.parseInt(elasticsearchPort)),
        new UsernamePasswordCredentials(elasticsearchUserName, elasticsearchPassword)
    );
    return credentialsProvider;
}
```

## ✅ 빌드 설정 확인

* `pom.xml` 또는 `build.gradle` 파일에서 Java 버전을 17로 설정했는지 확인합니다.

## ✅ 애플리케이션 빌드 및 실행

* 변경사항을 적용한 후 애플리케이션을 다시 빌드하고 실행합니다.

```bash
nohup java $JAVA_OPTS -jar recommendation-0.0.1-SNAPSHOT.jar &
```

## 🟢 문제 해결

* SSL 연결 문제가 지속될 경우, Elasticsearch 서버 로그와 애플리케이션 로그를 확인하세요.
