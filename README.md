# Pirates-Test
더 파이러츠 온라인 코딩 테스트
# 스프링부트 프로젝트 docker image로 build하기 위한 과정
## 1. Git Installation
**Windows**   
Download [git for windows](https://gitforwindows.org)

**Linux and Unix**   
Download [git for Linux and Unix](https://git-scm.com/download/linux)   
**유의사항** *아래 과정은 Windows 기준입니다*

## 2. Setting up Local Git Repository
#### 2-1. Git Repository Clone 주소 복사
<img width="800" alt="clone" src="https://user-images.githubusercontent.com/18158548/106550654-e6e08a00-6556-11eb-9266-16b741e2b9ef.PNG">

#### 2-2. 소스코드를 받기 원하는 폴더로 이동 후 우클릭 **Git Bash Here** 선택, 다음의 명령어 수행
```
$ git clone [Clone 주소]
```
```
$ git clone https://github.com/nicekr/lge-robot.git
```
완료되면 폴더에 master branch의 소스코드가 기본으로 checkOut 되어 있음  

#### 2-3. 다른 branch 로 변경하고 싶다면 다음의 명령어를 수행
```
$ git checkout [branch명]
```
```
$ git checkout develop
```
소스코드가 수정되었다면 최신 버전이 유지되는 branch에서 다음의 명령어를 수행
```
$ git pull
```

## 3. Creating a fat JAR Application with SpringBoot
소스코드를 받은 폴더의 Root에서 다음의 명령어를 수행

**Windows cmd**
```
mvnw clean package
```
**Windows Powershell**
```
.\mvnw clean package
```
완료되면 Root폴더 아래 target 이라는 폴더가 생성되고 그 하위에 프로젝트 jar 파일이 생성됨

## 4. Creating a docker image from SpringBoot JAR
소스코드를 받은 폴더의 Root에서 다음의 명령어를 수행

```
docker build -t [jar파일명] .
```
```
docker build -t robot-registry.ainize.ai/local_management:210202 .
```
