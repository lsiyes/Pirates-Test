# Pirates-Test 더 파이러츠 온라인 코딩 테스트
## 1. 설치 및 환경설정 가이드
#### 1-1. Sts4 설치
Download [IDE for SpringBoot](https://spring.io/tools)
#### 1-2. 소스코드 다운로드
Sts4 실행 -> File -> Import -> Project From Git(with smart import) -> Clone URI -> https://github.com/lsiyes/Pirates-Test.git 붙여넣기
-> master 브랜치 선택 -> 이후 계속 진행 Finish
#### 1-3. chrome 웹 스토어에서 POSTMAN 앱 설치
https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=ko

## 2. 테이블 생성 SQL
#### 2-1. 톰캣 실행 시 shcema.sql을 실행하기 때문에 아래 sql을 따로 수행할 필요는 없습니다.
```
DROP TABLE IF EXISTS fish_market_shop_holidays;
DROP TABLE IF EXISTS fish_market_shop_business_times;
DROP TABLE IF EXISTS fish_market_shop;

CREATE TABLE fish_market_shop (id IDENTITY
                    , name VARCHAR(15)
                    , owner VARCHAR(15)
                    , description VARCHAR(100)
                    , level BIGINT
                    , address VARCHAR(50)
                    , phone VARCHAR(13)
                    , holidays VARCHAR(100)
                    );

CREATE TABLE fish_market_shop_business_times (time_id IDENTITY
                    , id BIGINT
                    , day VARCHAR(10)
                    , open VARCHAR(5)
                    , close VARCHAR(5)
                    , FOREIGN KEY(id) REFERENCES fish_market_shop ON DELETE CASCADE
                    );

CREATE TABLE fish_market_shop_holidays (holiday_id IDENTITY
                    , id BIGINT
                    , holiday VARCHAR(10)
                    , FOREIGN KEY(id) REFERENCES fish_market_shop ON DELETE CASCADE
                    );
```

## 3. API 사용 가이드
#### 3-1. 다운받은 소스코드 프로젝트 Boot Dashboard 에서 was 실행
#### 3-2. POSTMAN 앱 실행 후 API 진행 순서
1)PUT localhost:8080/api/shop
1-1)BODY 의 raw 선택 -> JSON(application/sjon) 선택
1-2)json 형식은 아래 ctrl+c, v
```
{
 "name": "인어수산",
 "owner": "장인어",
 "description": "인천소래포구 종합어시장 갑각류센터 인어수산",
 "level": 2,
 "address": "인천광역시 남동구 논현동 680-1 소래포구 종합어시장 1 층 1 호",
 "phone": "010-1111-2222",
"businessTimes": [
 {
 "day": "Monday",
 "open": "13:00",
 "close": "23:00"
 },
 {
 "day": "Tuesday",
 "open": "13:00",
 "close": "23:00"
 },
 {
 "day": "Wednesday",
 "open": "09:00",
 "close": "18:00"
 },
 {
 "day": "Thursday",
 "open": "09:00",
 "close": "23:00"
 },
 {
 "day": "Friday",
 "open": "09:00",
 "close": "23:00"
 }
 ]
}
```
1-3)Send

2)PUT localhost:8080/api/shop
2-1)BODY 의 raw 선택 -> JSON(application/sjon) 선택
2-2)json 형식은 아래 ctrl+c, v
```
{
 "name": "해적수산",
 "owner": "박해적",
 "description": "노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집",
 "level": 1,
 "address": "서울 동작구 노량진동 13-8 노량진수산시장 활어 001",
 "phone": "010-1234-1234",
"businessTimes": [
 {
 "day": "Monday",
 "open": "09:00",
 "close": "24:00"
 },
 {
 "day": "Tuesday",
 "open": "09:00",
 "close": "24:00"
 },
 {
 "day": "Wednesday",
 "open": "09:00",
 "close": "24:00"
 },
 {
 "day": "Thursday",
 "open": "09:00",
 "close": "24:00"
 },
 {
 "day": "Friday",
 "open": "09:00",
 "close": "24:00"
 }
 ]
}
```
2-3)Send

3)PUT localhost:8080/api/holidays
3-1)BODY 의 raw 선택 -> JSON(application/sjon) 선택
3-2)json 형식은 아래 ctrl+c, v
```
{
 "id": 1,
 "holidays": [
 "2021-04-30",
 "2021-05-01"
 ]
}
```
3-3)Send

4)GET localhost:8080/api/shop
4-1)Send

5)GET localhost:8080/api/shopDetail?id=1
5-1)Send

6)DELETE localhost:8080/api/shop
6-1)BODY 의 raw 선택 -> JSON(application/sjon) 선택
6-2)json 형식은 아래 ctrl+c, v
```
{
	"id" : 1
}
```
6-3)Send
