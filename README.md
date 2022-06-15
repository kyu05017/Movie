# Java 영화관


### 환경
- JDK 8
- Eclipse
- Windows 10
- GitHub

### 서비스 설계 
![ㄴ](https://user-images.githubusercontent.com/98489230/173708385-b83b0cb1-db60-4cc5-9482-1112dbdef6c6.png)

-----

# 메인 [ View ]
1. 현재시간
상영관 / 상영작 / 시작 시간 / 종료 시간 / 가격
1. 상영중인 영화목록 [ 상영시간이 지나거나 상영시간이면 "상영 종료" 또는 "상영 중" ]

***
1.예매 
   1. 영화 선택
   2. 좌석 선택
   3. 결제
 
2. 예매확인
   1. 예매취소 

4. 회원정보
   1. 회원탈퇴
   2. 비밀번호 변경
      
5. 로그아웃

1. 영화등록 
   1. 영화제목 입력
   2. 상영관 선택 [ 총 3개의 상영관이 존재 ] 
   3. 시작하는 시간 입력 [ 24시간제 , 동일 상영관 중복시 등록 실패 ]
   4. 시작하는 분 입력 [ 동일시간 중복시 등록 실패 ] 
   5. 러닝타임 입력 [ 분 입력 (Ex : 120분) 등록한 시간 내에 중복된 영화가 있을 경우 등록 실패 ]
   
2. 영화삭제 
   1. 영화목록 표시
   2. 영화삭제
   
3. 매출확인 
   1. 영화별 매출과 전체 매출 총액을 보여줌

4. 회원목록
   1. 가입된 전체 회원 표시

5. 예매 목록
   1. 영화목록표시
   2. 선택된 영화 좌석에 예매된 좌석 표시
 
## 클래스 [ Model ]
Member [ 회원 클래스 ]
***
필드
***
* private String id
* private String pw
* private String name
* private String phone
* rivate boolean check

***
생성자
***
* 기본 생성자
* 풀 생성자

Movie [ 영화 클래스 ]
***
필드
***
* private String title;
*	private String intime;
*	private String runtime;
*	private int money;
*	private int Th_num;
*	private boolean check;
*	private String[] theater

***
생성자
***
* 기본 생성자
* 풀 생성자

Movie [ 상영관 클래스 ] = Hall에 상속되어있음.
***
필드
***
* private int Th_num;
*	private String[] theater

***
생성자
***
* 기본 생성자
* 풀 생성자

## 데이터 저장 및 불러오기 [ DB ]
사용한 메소드
***
1. 회원 파일처리 [ memberDB.txt 파일로 저장됨 ] 
   1. memberSave()
   2. memberLoad()
3. 영화 파일처리
   1. movieSave()
   2. movieLoad()
5. 예매정보 파일처리
   1. ticketSave()
   2. ticketLoad()

## Control [ Control ]
사용한 ArrayList 
***
* static ArrayList<Member> memberlist = new ArrayList<>();
* static ArrayList<Movie> movielist = new ArrayList<>();
* static ArrayList<Ticket> ticketlist = new ArrayList<>();
 
사용한 메소드
***

1. 회원 관련 메소드
    1. 회원가입
    2. 로그인
    3. 아이디 찾기
    4. 비밀번호 찾기
    5. 회원 정보
       1. 회원 탈퇴
       2. 비밀번호 변경
 
2. 예매 관련 메소드
    1. 예매
    2. 나의 예매 정보
       1. 예매 취소

3. 관리자 메소드
   1. 영화등록
   2. 영화삭제
   3. 매출확인

 

