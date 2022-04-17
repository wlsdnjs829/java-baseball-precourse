# 숫자 야구 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

## 기본 환경 구성
* 자바 jdk 8 버전 변경
* junit 5    
* stream API 사용 금지

## 기능 구현 정리
* 로직 작성 전 기본적인 단위 테스트 작성
  
* ### 단위 테스트 단위
    * 1 ~ 9 사이 서로 다른 수로 이루어진 3자리의 수 추출 (camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange())
    * 사용자가 1 ~ 9 사이 서로 다른 수 3자리를 입력 (camp.nextstep.edu.missionutils.Console의 readLine())
        * 잘못된 값이 입력된 경우 IllegalArgumentException를 발생시키며, 애플리케이션을 종료
    * 숫자 야구 룰에 의거한 로직 작성
        * 같은 수가 같은 자리에 있으면 스트라이크
        * 다른 자리에 있으면 볼
        * 같은 수가 전혀 없으면 낫싱이라는 힌트 제공
    * 정답을 맞출 때까지 반복할 수 있는 기능
    * 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있는 기능
    
* 컨벤션에 의거한 리펙토링
* 작성된 단위 테스트 코드 및 ApplicationTest 확인  
    
