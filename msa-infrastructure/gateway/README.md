# 라우팅 명령어
## 존재하는 라우팅 확인
```
GET : /actuator/gateway/routes
```

## 라우터 추가
```
POST : /actuator/gateway/routes/{id}
```
POST Body에 JSON 타입으로 데이터를 추가해야 한다.

## 리프래시
```
POST : /actuator/gateway/refresh
```

## 라우터 제거
```
DELETE : /actuator/gateway/routes/{id}
```

## 특정 라우터 확인
```
GET : /actuator/gateway/routes/{id}
```

## 글로벌 필터 목록
```
GET : /actuator/gateway/globalfilters
```

## 특정 라우터 필터 목록
```
GET : /actuator/gateway/routefilters/{id}
```


# 라우팅 추가 실습
## 현재 라우팅 목록 확인
```
GET : /actuator/gateway/routes
```

## 라우팅 추가
```
POST : /actuator/gateway/routes/{이름}
```

## 라우팅 JSON Body
```
{
"predicate": "Paths: [/ms1/**]",
"filters": [],
"uri": "http://localhost:8081",
"order": 0
}
```

## 추가 후 refresh
```
POST : /actuator/gateway/refresh
```

