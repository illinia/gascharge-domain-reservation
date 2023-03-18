# gascharge-domain-reservation

예약 도메인 관련 모듈

* charge, reservation, user 엔티티, 레포지토리, 관련 enum 클래스들
* 도메인 유닛 테스트

*common-common, module-yml, module-jpa 의존, 독립적으로 실행 불가능*

## 수정 내역
### 페이징, 조건문 검색시 아무 조건 없으면 전체 조회되는 버그 수정
전체 조회되지 않게 조건 클래스에 모든 필드값들이 null 이면 빈 값 반환하게 수정

### 예약 엔티티 외래키 참조 제거
예약 엔티티와 db 예약 테이블에서 유저, 충전소 외래키 참조 제티

### 유저 엔티티 고유 제약조건 삭제
user_id, email 컬럼 고유 제약조건 삭제

query dsl Q클래스 만드는법
```shell
# 중복 생성이 불가능 하다는 에러가 뜨면 src/main/generated 삭제 후 다시 시도
./gradlew compileJava
```

로컬, 원격 메이븐 레포지토리
```groovy
implementation 'com.gascharge.taemin:gascharge-domain-reservation:0.0.1-SNAPSHOT'
```

멀티 모듈 프로젝트
```groovy
// settings.gradle
includeProject("reservation", "domain")
```
```groovy
// build.gradle
implementation project(":gascharge-domain-reservation")
```

YAML 파일 설정은 https://github.com/illinia/gascharge-module-yml 참조