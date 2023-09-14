# Back

# 포인트를 쌓고 적립하는 KarinaPoint

<br>

[![gif](/asset/machine_state_gif.gif)](https://youtu.be/TqG5Wmtujec)
🔼 클릭하면 UCC로 이동합니다 !

<br>

##  💁 간단 소개

### "누구나 쉽게 보는 직관적인 모니터링 시스템"
12개의 반도체 설비에서 개수와 생성주기가 서로 다른 15개 종류의 데이터를 실시간으로 생성, 취합 및 가공하여 한눈에, 시각적으로 보이는 시스템을 개발했습니다.


- 🔗 LINK : https://semse.info/ (서버비용으로 인해 임시 중지)
- 🎼 Period : 2023.04.10 ~ 2023.05.19 (6주간 진행)
- 📑 Notion : https://www.notion.so/PJT-770f43012e344dfa9aad09a5a6d324f6

<br>

## ✨ 기능 및 화면
### 1) 메인 화면


- 모든 설비에 대한 데이터를 종합적으로 확인 가능합니다.
- 좌측 상단
  - 반도체 공정과정에서 중요하다고 생각되는 데이터 세가지를 뽑아 표시합니다.
  - [이상 없음 : 하늘색 / 데이터 이상 : 빨간색 / 설비 정지 : 회색] 으로 표현합니다.
  - 설비별 점수를 그물 그래프 형식으로 표현합니다.
- 좌측 하단
  - 공정과정에서 이상이 생길 경우 에러 메시지가 실시간으로 전송됩니다.
- 우측
  - 각 설비별 모든 데이터의 값을 확인할 수 있습니다.
  - 박스의 이동이 가능해 중요한 데이터의 경우 상단으로 끌어올려 스크롤 없이 확인 가능합니다.
  - 실제 값, 막대 그래프 중에서 출력 방식을 변경할 수 있습니다.

<br>

### 2) 설비 화면


- 설비 내 9가지의 데이터 종류를 한눈에 확인할 수 있습니다.
- 각 데이터의 특성에 따라 그래프 형식과 집계 방식(avg, min, max)을 다르게 표현했습니다. 

<br>

### 3) 데이터 화면


- 설비 내의 한 데이터 타입에 대해 센서 개수만큼의 변동 그래프를 확인할 수 있습니다.
- 좌측 상단
  - 센서의 상태를 [정상 : 초록 / 경고 : 노랑 / 빨강 : 위험] 에 따라 표현합니다.
- 우측 상단
  - 해당 데이터 타입 내 모든 센서 그래프를 나타냅니다.
- 하단
  - 각 센서별 그래프를 나타냅니다.

<br>

### 4) 설비관리 화면



- 데이터 요청시간, 기준선 등의 설비관리에 대한 세부 설정을 할 수 있습니다.

<br>

### 5) 센서 화면


- 한 센서에 대해 기간별로 조회 및 비교를 할 수 있습니다.
- 최대, 최소값 및 평균값의 집계를 제공합니다.

<br>

### 6) 설비상태 화면


- 12대의 설비에 대한 모든 상태값들을 실시간으로 확인할 수 있습니다.

<br>

### 7) 커스텀 화면


- 제공하는 화면 구성 외에 비교가 필요한 데이터가 있을 때 쉽게 조회 및 배치해서 비교할 수 있게 했습니다.
- 배치 방식으로는 아래 정렬과 바둑판 정렬을 제공하고 있습니다.

<br>

---

<br>

## 📃 데이터 명세


<br>

## 🏋️‍♂️ 기술 스택
### Front-end
[![React](https://camo.githubusercontent.com/20779f9d605be40d4f84bbc93a5fee22e86068e785a0c0ed8d90d3d15041a3fc/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f52656163742d3631444146423f7374796c653d666f722d7468652d6261646765266c6f676f3d5265616374266c6f676f436f6c6f723d626c61636b)](https://camo.githubusercontent.com/20779f9d605be40d4f84bbc93a5fee22e86068e785a0c0ed8d90d3d15041a3fc/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f52656163742d3631444146423f7374796c653d666f722d7468652d6261646765266c6f676f3d5265616374266c6f676f436f6c6f723d626c61636b) [![TypeScript](https://camo.githubusercontent.com/6a138baf27a486e6fb68d759541144cd7ddfbff9839b41fb3f64b16458575a7f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f547970655363726970742d3331373843363f7374796c653d666f722d7468652d6261646765266c6f676f3d74797065266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/6a138baf27a486e6fb68d759541144cd7ddfbff9839b41fb3f64b16458575a7f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f547970655363726970742d3331373843363f7374796c653d666f722d7468652d6261646765266c6f676f3d74797065266c6f676f436f6c6f723d7768697465) ![axios](https://img.shields.io/badge/axios-5A29E4.svg?style=for-the-badge&logo=axios&logoColor=white) ![recoil](https://img.shields.io/badge/recoil-D3002D.svg?&style=for-the-badge) ![chart.js](https://img.shields.io/badge/chartdotjs-FF6384.svg?&style=for-the-badge&logo=chartdotjs&logoColor=white)

### Back-end
[![JAVA](https://camo.githubusercontent.com/3a1c7dafcdfce483e68f5fb95d057e9421c8109fd105e603542b1ff00fd7ae91/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a4156412d4646303030303f7374796c653d666f722d7468652d6261646765266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/3a1c7dafcdfce483e68f5fb95d057e9421c8109fd105e603542b1ff00fd7ae91/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a4156412d4646303030303f7374796c653d666f722d7468652d6261646765266c6f676f436f6c6f723d7768697465) [![Spring](https://camo.githubusercontent.com/57da5a02a135c27818a618285a57f7e54df63419d1f7ad598905a0bd27e780c7/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e67626f6f742d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67626f6f74266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/57da5a02a135c27818a618285a57f7e54df63419d1f7ad598905a0bd27e780c7/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e67626f6f742d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67626f6f74266c6f676f436f6c6f723d7768697465) ![Kafka](https://camo.githubusercontent.com/1b371597d577a5f430f0dbc8a356d8951f0b7a6d7dded5eb99e2b4cf1593397f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6b61666b612d3233314632303f7374796c653d666f722d7468652d6261646765266c6f676f3d6170616368656b61666b61266c6f676f436f6c6f723d7768697465)
![grafana](https://img.shields.io/badge/grafana-F46800.svg?style=for-the-badge&logo=grafana&logoColor=white) ![prometheus](https://img.shields.io/badge/prometheus-E6522C.svg?style=for-the-badge&logo=prometheus&logoColor=white)
[![Gradle](https://camo.githubusercontent.com/e850f9c862ce515586c3859cab52395f8d096f0de68825fdaaf6b9bea572311e/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f477261646c652d3032333033413f7374796c653d666f722d7468652d6261646765266c6f676f3d677261646c65266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/e850f9c862ce515586c3859cab52395f8d096f0de68825fdaaf6b9bea572311e/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f477261646c652d3032333033413f7374796c653d666f722d7468652d6261646765266c6f676f3d677261646c65266c6f676f436f6c6f723d7768697465)

### DB
![InfluxDB](https://img.shields.io/badge/influxdb-22ADF6.svg?&style=for-the-badge&logo=influxdb&logoColor=white)

### Infra
[![docker](https://camo.githubusercontent.com/b184cf7adbab9f5464e80c0f5dd32c85393f6248499a57d743e619f4214391c4/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f646f636b65722d3234393645443f7374796c653d666f722d7468652d6261646765266c6f676f3d646f636b6572266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/b184cf7adbab9f5464e80c0f5dd32c85393f6248499a57d743e619f4214391c4/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f646f636b65722d3234393645443f7374796c653d666f722d7468652d6261646765266c6f676f3d646f636b6572266c6f676f436f6c6f723d7768697465) [![Jenkins](https://camo.githubusercontent.com/afb2118755f7a25ec1e70bdd9f2d4be4f4b166d0cbbc0fc529f0ec1c450aa60d/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a656e6b696e732d4432343933393f7374796c653d666f722d7468652d6261646765266c6f676f3d6a656e6b696e73266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/afb2118755f7a25ec1e70bdd9f2d4be4f4b166d0cbbc0fc529f0ec1c450aa60d/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a656e6b696e732d4432343933393f7374796c653d666f722d7468652d6261646765266c6f676f3d6a656e6b696e73266c6f676f436f6c6f723d7768697465) ![Terraform](https://img.shields.io/badge/terraform-7B42BC.svg?&style=for-the-badge&logo=terraform&logoColor=white) ![Ansible](https://img.shields.io/badge/ansible-EE0000.svg?&style=for-the-badge&logo=ansible&logoColor=white) 

<br>

## ⚙ 아키텍처


<br>

## 🖥️ 실행 방법
[포팅 메뉴얼]()에 자세히 작성되어 있습니다😊 참고해 주세요 !

<br>

## 팀원 소개
![image]()

## 개발 참고 자료
[PR 보내는 방법](./Contribute.md)

[Git 참고자료](./Git.md)

[상위 브랜치에서 작업한 경우](./UseUpper.md)
