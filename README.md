# gascharge-domain-reservation

예약 도메인 관련 모듈

* charge, reservation, user 엔티티, 레포지토리, 관련 enum 클래스들
* 도메인 유닛 테스트

*common-common, module-yml, module-jpa 의존, 독립적으로 실행 불가능*

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