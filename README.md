# CS496 Imersive Camp (Winter 2021)

# Project #2: Server & DB Application

## Team

박예림 (https://github.com/rimrim990)

송성민 (https://github.com/reverg)

## Introduction

 이 앱의 이름은 Let’s Run이다. 달린 경로를 지도 API를 이용해 구하고 관련 정보를 서버에 전송한다. 저장된 운동 기록은 서버에서 유저별로 받아올 수 있다. 커뮤니티 그룹을 만들고 가입하여 달린 거리와 순위를 공유할 수 있게 하였다.

 Android를 위한 application으로 제작되었으며, Backend는 node.js와 mongoDB를 mongoose로 연결하여 구성됐다. Frontend에서 Backend와 연결하기 위해 Retrofit2를 사용했다.

## Tab 1: Running Tracker

### Features

- 위치 추적
- 경로 지도에 그리기
- 이동거리, 최고 속력, 운동 시간 기록
- 서버에 운동 기록 전송

### Function 1: Location Tracking

 기기의 위치 좌표를 받아와서 지도에 현재 위치를 나타낸다. Android가 Marshmallow version 이상인 경우 위치 정보에 접근하기 위한 권한을 얻어야 하는데, 이를 위해 앱 설치 후 최초 실행시에 위치 정보 접근을 요청하는 창이 뜨도록 하였다. 지도는 Naver Map API를 사용하였다.

### Function 2: Draw Path on Map

 Start button을 누르면 경로를 기록하기 시작한다. 실시간으로 받아오는 위치 좌표를 List에 넣어 지도에 파란색 경로를 그리게 하였다. Stop button을 누르면 위치 추적과 경로 그리기를 중지한다.
 
 <div align=center> <img src = "https://user-images.githubusercontent.com/48681924/148922606-45aff1a8-eadd-4c46-9e06-21c56bfa68a5.jpg" width="30%" height="30%"></div>

### Function 3: Make Record

 Running 중에 받아온 좌표들 사이의 거리와 걸린 시간을 얻어 총 이동거리와 실시간 속력을 구한다. 운동 시간은 Android Widget의 Chronometer을 통해 얻었다. Running이 끝난 후에는 실시간 속력 대신 최고 속력을 화면에 표시한다.

### Function 4: Send Record to Server

 기록된 이동 경로의 좌표 리스트, 총 이동거리, 최고 속력, 운동 시간을 서버에 보내 저장한다. 보내진 기록은 User가 달린 총 거리를 업데이트하는데 사용된다.

## Tab 2: Record

### Features

- 유저별 운동 기록 받아오기
- 운동 기록 상세 정보 확인

### Function 1: Get Record from Server

 User가 서버에 보냈던 운동 기록들을 받아와 RecyclerView로 표시한다. 

### Function 2: Show Details of Record

 각각의 운동 기록들을 누르면 새로운 Fragment가 생성되어 상세 정보를 표시해준다. 서버에 저장했던 경로 좌표들을 이용해 Naver Map에 경로를 그려준다. 이동 거리, 최고 속력, 운동 시간 또한 아래의 작은 창을 이용해 표시해준다.

## Tab 3: Community

### Features

- 그룹 정보 확인
- 그룹 만들기 & 그룹 참가하기

### Function 1: Show Group & Details

 처음 Community Tab에 들어가면 RecyclerView로 서버에 저장된 유저가 속한 그룹 전체를 띄운다. 각각의 Group 창을 누르면 새로운 Fragment가 생성되면서 Group User 사이의 순위와 달린 거리를 확인할 수 있는 창이 생긴다. 내 순위는 상단에 표시되고, 하단에서 다른 User들이 얼마나 달렸는지 볼 수 있다.

### Function 2: Make Group

 우측 하단의 FAB을 누르면 Make Group과 Join Group 버튼이 나온다. Make Group을 누르면 Group의 Name과 Information을 입력하는 새로운 Dialog가 나타난다. 정보를 입력해 새로운 Group을 생성하면 서버에 등록되고 난수로 Group Code를 생성해 사용자에게 반환해준다. 이 Code는 Join Group에 사용된다.

### Function 3: Join Group

 Make Group 때와 비슷하게 새로운 Dialog에서 Group Name과 Code를 입력하면 새로운 그룹에 참가할 수 있다. 

---

[https://github.com/reverg/MadCamp2_front](https://github.com/reverg/MadCamp2_front)

[https://github.com/rimrim990/CS496_week2](https://github.com/rimrim990/CS496_week2)
