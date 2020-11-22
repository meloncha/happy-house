<template>
  <div>
    <div class="container mt-5" style="background: gray">
      <div class="row" style="margin-top: 20px">
        <div class="col-sm-8">
          <h3>상세검색</h3>
        </div>
        <div class="col-sm-3">
          <input
            class="form-control"
            style="float: left"
            type="text"
            placeholder="Search"
            v-model="keyword"
          />
        </div>
        <div class="col-sm-1">
          <button
            class="btn btn-outline-dark"
            style="float: right"
            @click="search"
          >
            검색
          </button>
        </div>
      </div>
      <hr />

      <div class="row">
        <h6 style="margin-left: 30px; margin-right: 20px">주소</h6>
        시도 :
        <select id="sido" v-model="sido">
          <option
            :value="item.sidoCode"
            v-for="(item, index) in sidos"
            :key="index"
            >{{ item.sidoName }}</option
          >
        </select>
        구군 :
        <select id="gugun" v-model="gugun">
          <option
            :value="item.gugunCode"
            v-for="(item, index) in guguns"
            :key="index"
            >{{ item.gugunName }}</option
          >
        </select>
        읍면동 :
        <select id="dong" v-model="dong">
          <option
            :value="item.dong"
            v-for="(item, index) in dongs"
            :key="index"
            >{{ item.dong }}</option
          >
        </select>
      </div>
      <hr />

      <!-- <div class="row" style="margin-left: 20px">
        <div>
          학군
          <div class="custom-control custom-radio custom-control-inline" style="margin-left: 30px">
            <input type="radio" class="custom-control-input" id="4" name="4" value="4" />
            <label class="custom-control-label" for="4">초등학교</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="5" name="5" value="5" />
            <label class="custom-control-label" for="5">중학교</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="6" name="6" value="6" />
            <label class="custom-control-label" for="6">고등학교</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          상권
          <div class="custom-control custom-radio custom-control-inline" style="margin-left: 30px">
            <input type="radio" class="custom-control-input" id="7" name="7" value="7" />
            <label class="custom-control-label" for="7">음식점</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="8" name="8" value="8" />
            <label class="custom-control-label" for="8">마트</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="9" name="9" value="9" />
            <label class="custom-control-label" for="9">백화점</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="10" name="10" value="10" />
            <label class="custom-control-label" for="10">문화시설</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="11" name="11" value="11" />
            <label class="custom-control-label" for="11">유흥시설</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          교통
          <div class="custom-control custom-radio custom-control-inline" style="margin-left: 30px">
            <input type="radio" class="custom-control-input" id="12" name="12" value="12" />
            <label class="custom-control-label" for="12">지하철</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="13" name="13" value="13" />
            <label class="custom-control-label" for="13">버스</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          치안
          <div class="custom-control custom-radio custom-control-inline" style="margin-left: 30px">
            <input type="radio" class="custom-control-input" id="14" name="14" value="14" />
            <label class="custom-control-label" for="14">CCTV</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" class="custom-control-input" id="15" name="15" value="15" />
            <label class="custom-control-label" for="15">범죄율</label>
          </div>
        </div>
      </div>
      <hr /> -->
    </div>

    <table class="container mt-5">
      <thead>
        <tr>
          <th>번호</th>
          <th>법정동</th>
          <th>아파트이름</th>
          <th>지번</th>
          <th>지역코드</th>
          <th>위도</th>
          <th>경도</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in apts" :key="index">
          <td>{{ item.no }}</td>
          <td>{{ item.dong }}</td>
          <td>{{ item.aptName }}</td>
          <td>{{ item.jibun }}</td>
          <td>{{ item.code }}</td>
          <td>{{ item.lat }}</td>
          <td>{{ item.lng }}</td>
        </tr>
      </tbody>
    </table>
    <div id="map">
      <hr />
      <div id="map"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// const colorArr = ['table-primary', 'table-success', 'table-danger'];

export default {
  data() {
    return {
      keyword: "",
      sidos: [],
      sido: {},
      guguns: [],
      gugun: {},
      dongs: [],
      dong: {},
      apts: [],
      positions: [],
      customOverlay: "",
    };
  },
  name: "Map",

  mounted() {
    //  if (window.kakao && window.kakao.maps) {
    //   this.initMap(); // 초기 맵 으로 이동 (지금은 카카오 좌표)
    // } else {

    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ee37a1035dfa14e56abd773fe878b0fc";
    document.head.appendChild(script);

    var container = document.getElementById("map");
    var options = {
      center: new kakao.maps.LatLng(36.3543926, 127.2971463),
      level: 3,
    }; // 지도를 표시할 di
    var map = new kakao.maps.Map(container, options);

    var positions = [
      {
        title: "대전싸피",
        latlng: new kakao.maps.LatLng(36.3543926, 127.2971463),
      },
    ];
    var imageSrc =
      "https://www.flaticon.com/svg/static/icons/svg/609/609803.svg";

    for (var i = 0; i < positions.length; i++) {
      // 마커 이미지의 이미지 크기 입니다.
      var imageSize = new kakao.maps.Size(30, 40);

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
      marker.setMap(map);
    }
    map.setMapTypeId(kakao.maps.MapTypeId.maps);
    // }
  },

  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450879, 126.56994),
        level: 3,
      };

      var markerPosition = new kakao.maps.LatLng(33.450879, 126.56994);

      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      var map = new kakao.maps.Map(container, options);
      marker.setMap(map);
      map.setMapTypeId(kakao.maps.MapTypeId.kakao);
    },
    search() {
      axios
        .get(`http://localhost:7777/happyhouse/map/apt/search/${this.keyword}`)
        .then(({ data }) => {
          this.apts = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },

  created() {
    // 시도정보 가져오기
    axios
      .get(`http://localhost:7777/happyhouse/map/sido`)
      .then(({ data }) => {
        this.sidos = data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  watch: {
    keyword() {
      //여기에 지도 실시간 변경
      axios
        .get(`http://localhost:7777/happyhouse/map/apt/search/${this.keyword}`)
        .then(({ data }) => {
          //data  ->  apt 정보가 담긴 객체 받아왔어
          // console.log(data);
          this.apts = data; //apt에 전달해서 출력에 활용하고
        }) //then
        .catch((err) => {
          console.log(err);
        });
    },
    sido() {
      axios
        .get(`http://localhost:7777/happyhouse/map/gugun/${this.sido}`)
        .then(({ data }) => {
          this.guguns = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    gugun() {
      axios
        .get(`http://localhost:7777/happyhouse/map/dong/${this.gugun}`)
        .then(({ data }) => {
          this.dongs = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    dong() {
      //여기에 지도 실시간 변경
      axios
        .get(`http://localhost:7777/happyhouse/map/apt/${this.dong}`)
        .then(({ data }) => {
          this.apts = data;

          // geocode(data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    apts() {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ee37a1035dfa14e56abd773fe878b0fc";
      document.head.appendChild(script);

      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450705, 126.570677),
        level: 3,
      }; // 지도를 표시할 div
      var map = new kakao.maps.Map(container, options);

      this.positions = [];
      this.apts.forEach((element) => {
        // apt가 변할 때마다 apt 값 포지션에 넣어주고 마커 생성

        const position = {
          title: element.aptName, //아파트 이름
          latlng: new kakao.maps.LatLng(element.lat, element.lng), // 경도위도
          dealAmount: element.dealAmount, //매매가
          area: element.area, //면적
          //나중에 룸메 구하는 사람 아이디, 충원 인원 추가?

          ///////////////////////////각 마커의 오버레이 콘텐츠 /////////////////////
          content:
            '<div class="overlaybox">' +
            // '    <button class="btn btn-danger" :click="customOverlay.setMap(null);">닫기</button>' +
            '    <div class="boxtitle">' +
            element.aptName +
            "</div>" +
            '    <div class="first">' +
            '        <div class="movietitle text">내부 사진</div>' +
            "    </div>" +
            "    <ul>" +
            '        <li class="up">' +
            '            <span class="number">매매가: </span>' +
            '            <span class="title">' +
            element.dealAmount +
            "</span>" +
            // '            <span class="count">집 값 상승</span>' +
            '            <span class="arrow up"></span>' +
            "        </li>" +
            "        <li>" +
            '            <span class="number">평 수: </span>' +
            '            <span class="title">' +
            element.area +
            "</span>" +
            '            <span class="count">제곱미터</span>' +
            "        </li>" +
            "        <li>" +
            '            <span class="number">건축년도: </span>' +
            '            <span class="title">' +
            element.buildYear +
            "</span>" +
            "        </li>" +
            "        <li>" +
            '            <span class="number">거래년도: </span>' +
            '            <span class="title">' +
            element.dealYear +
            "</span>" +
            "        </li>" +
            // "        <li>" +
            // '            <a href="" class="number">룸메이트 찾기</a>' +
            // '            <span class="count"></span>' +
            // "        </li>" +
            "    </ul>" +
            "</div>",
        };
        this.positions = [...this.positions, position]; //positions[{position객체},{position객체},{position객체}]
      }); //////////// foreach

      var bounds = new kakao.maps.LatLngBounds();

      for (let i = 0; i < this.positions.length; i++) {
        // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
        // marker = new kakao.maps.Marker({ position: this.positions[i].latlng });
        // marker.setMap(map);

        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(this.positions[i].latlng);
      }

      ////////////////////////맵 설정//////////////////////////

      var imageSrc =
        "https://www.flaticon.com/svg/static/icons/svg/609/609803.svg";

      for (var i = 0; i < this.positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니ek.
        var imageSize = new kakao.maps.Size(30, 40);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        const customOverlay = new kakao.maps.CustomOverlay({
          position: this.positions[i].latlng,
          content: this.positions[i].content,
          xAnchor: 0.3,
          yAnchor: 0.91,
        });
        // 마커를 생성합니다
        const marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: this.positions[i].latlng, // 마커를 표시할 위치
          title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", function() {
          customOverlay.setMap(map);
        });

        marker.setMap(map);
      }
      map.setBounds(bounds);

      map.setMapTypeId(kakao.maps.MapTypeId.maps);
    },
  }, //watch
};
</script>

<style>
.fakeimg {
  height: 200px;
  background: #aaa;
}

.navbar-nav .nav-item {
  margin-left: 40px;
  margin-right: 40px;
}

/* #map {
  margin: 0 auto;
  height: 65%;
  width: 65%;
} */

#map {
  width: 1300px;
  height: 700px;
  margin: 0px auto;
}

html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}
/** ------------------오버레이 스타일---------------- **/
.overlaybox {
  position: relative;
  width: 360px;
  height: 350px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png")
    no-repeat;
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .boxtitle {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  text-align: left;
  /* background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png")
    no-repeat right 120px center; */
  margin-bottom: 8px;
}
.overlaybox .first {
  position: relative;
  width: 247px;
  height: 136px;

  background: url("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEhUSEhMWFRUVFRUXFxUXGBcVFxcVFRcWFhUVFRcYHSggGBolGxUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OFRAQFy0dFR0rLSsrLSsrLS0tKy0tLS0tLS0rLS0rLSstLSstLS0rLS0tLS0tKy0tLS03LSstKy0rK//AABEIAMIBAwMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAADAAIEBQYBBwj/xABCEAABAwEEBgcFBwQBAwUAAAABAAIRAwQSITEFQVFhcYEGIpGhscHwEyMystEVQlJyc4LhM0Ni8RQHwtIkU2Ois//EABgBAQEBAQEAAAAAAAAAAAAAAAABAgME/8QAHREBAQEAAwEBAQEAAAAAAAAAAAERAhIxIUFRE//aAAwDAQACEQMRAD8ArfYroswzI1/wpgYk5uHf2Yrz664Fowe9gb/BX7TqP8qj0Y2K4HHwK0JYtckhl1curpBCV9ZadATmhcRaD7rgYBggwcjGoqBj2Qmqz0vbxWIIptZA1a1WFAlyVwlJRXZXZTEkDpSlMlcLkBLy4SmsfBBInHLbuXoOhrNZ302vZTbjuGa1xms24wlOg93wtceAJU2hoS0OypnngvQ2UwMgBwTlv/Nnu8xttjfSID8yJ17jr4hRpWq6dWf4KnI+uY7FkJWOUytz7BLyRch3k28sqLeXC5DlcJVDy5Mc5NJQ6lQASdSCJpCrJDBxPkiUW4KLQ6xLjrP+lOYFZEqXY29ZX9lPrmqOyBXFF2HralRPFRJRQ5dUGPhcezA8CnpIoViH/qGnUQT3FaGVRWEe8pcx/wDQ/RX10q6gRCE9uPJHcEM58kUHEJ7aq64IL2KA5cmlyBfIXfaBZUdcQTXARGPnJNU5cJTntjNDKmhEri5KaXIHSrno7p3/AI94OBLSMhtVFK4SrLiWa2dbps37lI8yB4KBW6ZVj8LWN7SswXJt5a71OsWmkdO1qwuvcI2AYYKBeQC5K+ptUa8uXkG+uX0B764XIJeuF6oIXKFpCpkwa8+ARnVFBoG+4u7OAyQqbQZAUhqYxEYFtlOoalPa/wBc1AoqUHeuayJV9JBBXUGfSXFwlZ1pK0IyazAfxv8AB/1W1ZYgdSw+iakVmH/5G98DzXodApEqK7RTTqVdatDkHq7D5fVaZhSIF4flPi1VGFrUi0wQgOC2mkLC14yWUttkLDuRUJzUENUqEGFmtQG0NF3ETl6wUKlXdSIcMQMY1/z6zVvTspqG4MJXdJdHKtNt8dZozjMcli1QbVp7/kODnQMIwAGW4ZoTq4WeuwXajeKiWvS5pkAgkmdeyFr9RqvbLhrLHHpC7Uwdv8JjtO1TqaORPmrhrZGqFw1QsU7S1c/fjgB5hCdbaxzqO7Y8ExNbc1Uw1lh3Occ3OPEkouj7Uab7s9V3c7V25diuGth7RL2iqmWhFFZME/2iV9QxUThUVEq+kXKN7RcNRArbVwujN2HLX63o9kZAUKzdd5dqGA5Z96s6QVSjBGpoKNTVRLpHxRgVHplPBUEppSQ2uXUFGTsg80sdYWGp9JrUz42tcN7S09ow7lY2TpY0/FSe38pDh5J0q9mmpPh87Lp7z9F6PZHSvJ7JpGnUPVLpOEOY5u/MiNq9R0aZY07h4KZhataZTiesPynxCC1ydPWHA+IRDqjlV26heVqWoTmIrK2iwlgJ1KAW4rY6Rog0zy8QsvUs/WWap2i/6g9bFsGuBbB2LMWOhBBVs20kBceXrcYzpfo4U3hzAA1wMx+K+7HsjsXn+nR1m8/JerdIiHiNx+Zy8w6RMhzR+byXThfqVVNCPZ6Re5rBm4honKSYxTGNKm6Lpn21LD+4z5gulZWNLo0451G8muPjCk0+jI11HHgwDxctHSZgjNCxtXGZq9HKbWudNSQ1xHwgSATlBWVtVOQvSrePdv8AyP8AlK86q+S1xSo1PSz9gnbirjR1qL2y7AzqWfY3HmrShIa2DGfitVI0uj6Ae9rS5rATi50wOMAlWOk9H06bw1lZtQY/CHYRETIAx3Tksswu/Ee0pxB2ntUxVrVAChWqtAwOOSiezTCxBfaLpwwKW1AshhoRmlaZGRWIAKM1BJYURpQWp7SoJLQkmMOC4gzLLANiTtGj0JVq2n65p1xNFXTouYZAaeI+qk0tIvbjdIO1hI+qlOYguppq4m2bpXUbm9372h3fM9yt7L0vBIJuHAjBxYdX48FlqlJV7rMCYu88N31Qx6hR6SUSJdebviW9oU2jpOi/4ajTzjxXkbLLGLS5uzPymM1IpVKo+/PET4qafXrFsPuzy8VQVhisjR0hVGF4j8pcPNXFitTyJcS7jGHMBZqxcgwFx1aFHbXnUh1XrlW4Hb6l6eJ8SsJ0jpC+w/m8lr6rs+J8VlekXxM/d5Kz1PxWMYFO0Uz39L9Sn8wUJpU3RB9/S/Vp/MFtluqVLqjA5bvqumju70egOqOCfCkaqq0jTPs6n6b9f+JXmdY+C9W0mPdVP03/ACleUWo48lvizURivrDQvU2RnLvFUDFqNCH3bf3eK1Ui00fopp+LFXTLBSA+Adig2epCr9I9K3sqvpgsbdcQC5uqNs4nkufLlipts0fT1CFViwi8Nn8hXdrrS48vAKDr5LcQOkUVpUVjkdhVMSGozSgNKJTciJTSngoTSngoChy6mtKSCKHJ0qfp+ytbRp3W3XOYZcMwbrdnFVGjmudTa7OQPBL8J9HJTSQnmi7YmOpO2eCimGEAAXuRRjQfsQ6Fn6+IOIdER8UGM9UpVFa0YLgpYJ5sjwRuzGXfqRBQdg2MSYWcEajSkq1otQqli9m0mZjPtjDtRbO6VKsSgICDWednZ/MIrnYKLWesVYi1KmfE+KzfSA9Zn7vJX734u4lZ3T56zP3eSv6fiACpuiD7+l+rT+YKA1TdEn39L9RnzBaZei2cm6OCIo1nf1QJ1BSAoqLpP+lU/Tf8pXk9rOPIL1jSf9Kp+m/5SvJbWceQ81vilRWFaTQr/dt/d8yzDFf6Kd1B+7xWqkX7aphMc4kk+zoScSTTkk7ySo7aid7RTBK9u44uid2A2JhqZ8Co99MfUwPBUPpOUqkVX0HJ9utnsqTngSWtJA2xnOxBYufCVGtisXT6TVnva0tphpvSAHTg0uzJ3bFdaNtl5wWurOtUwp7UKlqRWrKjMOCSa0JIYz9DQzWklprSc/e1cdWPWxVpZWVabA1rSQBAlpJjjGK1RYdvrtXC0ayrqMyy1VyRFJxkkf0n4RPdv3q9o2aWgubBIE4EYxijNunIg+uCgVdMUWuuxVcR+GlUdvzDY70+CTUs+EAfKPFQDYXucA5pugkyCyZ+7BmRmcRsUmsKryDScGtjJ2cydx3JgsVc51Ryn+E2CWW5Qx3Gaff1kGpRfea5oaIP3jmMQfhnahfZr9dZ3Kf/ACXW6KBAmo84bR5psXBLdZnPEBzGjXN+eHwodGztZnUZ2nzCQ0PS/wAjxP0CfT0VRgdScNrvqs3FmuVqjCIa8E4YAqDWKm2mzU2NlrADhiM1WVnHZ4fVcuXrc8R3HF3ErP6fOLP3eSuiT1uJVDp7Nn7vJWen4ggqZok+/pfqM+YKACpmiT7+l+pT+YLTL0KhTMAzq9a1JE+h/KjUHdUcEYOWY1QtJf0qn6b/AJSvJbaetyHmvV9JH3VT9N/yleT2048gt8Wah01daNf1W8/FUbV6Hp2kKtmo2oNAc5jC+BEkgB2X+RB5lbrKpY5PvqIx6d7RMEm+hVX4FCvoVV+CYJtmKHp1p9jUIBj2bpMGMHNABPPuTrIVN0lSc+x1mtEk3QBtM5KT1b488sx644P/APzerPo1aCagVZSa5tQNc1zXRUwcCD/TftU3ouPeDiu1co9Rs4wHJFBQ7PkERcXQRqSTSkgs2255MCiebvoCuaQoOqtLBLbzTDthByMHXuVjQpFxgc9wWf6XdNqNh93Tb7WsRNwEAgficfujcMTwxViH6B0RVs7A19R1UlxLnvcXGDlBOfdhwU0UH4YtG2B/HH+V5kz/AKq2wVPeUqRbOLRfDo/MXHHkvSdE6UZaaTa1M9V2Y1g6wU5SwiVQYQIJnf24Iq436J0LLRpTG5DgiFNYMBwCK4CmtyT4QxlyWaI9uPV5hU9Yq2t3w8wquoxY5etzxXTi78xVDp49Zn7vJX7Gk3vzFVGl7BUqFtxsxM4gZxtKfqfimaVN0Uff0v1GfME37Jrj+2e0HzUjRthqtrUyabwBUZJgxF4Yyt7EbqgeqMTkiglR6D8AjteubWA6QPuqn6b/AJSvKbdny+q9U0k73VT9N/yleYmyOqPgZQJOzPvW+FZ5Kxi9F0E72ujLhzYaje0mPmZ2LN09FU/w95V/0fpFgq0mTdcy81olxL2ySG7SREDcF07azjNMqKz6PVG/8mkHgFpddIIBHWBAwO8hZOnpYRl3x/pHs2mbr2m7iHAjHWCI1LpeHLPGO8ey1NF2cGHUG54n2QIA2zEKvraJs1R0UatkwzYaDnOB3kPb4Kuq9MqDKjqRtRa9ri0gioAHAxF67Hepf2+D/fHP+QvPnKOmypNTo+WiQbId117Dy6xXTYGlhaGt3gZHkT3KL9sD/wB2lz9n5hFbpx2QqUzwueSfT4zunLBaILBZ77SCAQ69EiJF4SFntCaKqUnzUYW45xgvQxpl51sPZ5FdGkX/AIGHkfqtdr/EyI9n1InrvTatqn+21u9sjuyXQrLpRmpLrcklUXulraLPZalU/dY953hgJjmQvnSpaX1ajq9R0vc5znHWSdQ3auGC+gOk9kNeyVaTc30XNH5oMDtXgtKntERIunCIzvb1vjUobaTXGDM7Tr3Eal6j/wBJmkWesCZHtABwu5ifWC82o2V9aq2lSF57iIA2yfUr2jo/ocWWiylMuzeR+I4kDcMlOXhPVvR8h5oqYweA809YbNcmMyHAJ7kwEACcMAs6EUMDBMfahqBPcO1DDKjtw3epUqhW5wuxOxVTq0/CC7hl2nBXf2cNePH6J3sGjJYrUZ2jYKmMmASTAxOO8o7LKG5fVW7qW9R6tNRUOBsTmNk5KQ2gj0aOOSuJoFKymNaKKJGvwUwiNSa4beQTDUGpTwMqntlhaThs1QFfVG5qscBKsFY3R4zRxZIEjAgYHYRl3wp4am1hhG0geZ8CtRlWWjRlOoS6pTp1HHNz6dNzzvc+7ecd5KgWrQNmwPsKfxNyvMzcB90haGozAlQbW7AfmZ8wWpyv9TIyum9C0alaq4MIfec8m8TeOZwMjkkCrOv/AF51GoAeBIlVjG4DgrbpJjhclTzCRak0YqKzVBovZbfEq2oNVXZ/iHPxKt6C3WVvolxFVmJjrYT/AIlayi7BZDRxio393gVqrK7BETA5JOakoLoVowOXgqLS3RKxWl/tXtc1x+I03lt78wyJ3xKvSEN1IbFNVA0Tomy2QEWemA44F2LnHi848lY0mnM5rjWJ73hokmBtOCintz9b0x9oaNcndiolUl5wm7GuQDnq1o9KzD1gFNUN1oc7Bo8z9AlTspOLjPepvswPoleGpQMp2cDcnyAkRt/lDLtmCg7Vq4epUc1ER4QxTn1jyUqxyU001IawDUhvP+kw0O6E4FMc9NacZKCWBhJTSDxJyCG+oGgucYAzJyQKGkXNh10Ccr2IE5a8zOxXEX9is1wf5HM+SO5gOYB4iVRDTVTAQyTq8TiRIXGadq3iDTbgB+KCTOAO4DZrXSWM5Vu+wUjnTb2AeCj1dCUXR1SIM4OOeI27yo/27ta2TMNLrpJHInuRaGm6btREGDGInXBMTxTYmUyroBhEBzx2HyUC2dEw8QKpbljdxwIIxncrynpOk7727EEIzK7HfC5p4EFMh9Ym2dCqpcXNqskmRILfCVWVOhdqGXs3cHHzaF6aWrkK4a8nq9FbYP7M8HM/8lGqaAtbRP8Ax3k7MMecr2AtXC1TF7Pnv7AtjHdey1o1EU3OG3NoKmU7JVGdOoOLHDxC92NNNNJVNeK0AWvYSCPizBGretRYKkjkt3abEx7S17Q5pzBWT0loF1CX05dT1g4ub9Rv/wBoCg8FxR2VsFxUaUlR6tpaDGZ2DE89nNMFFzvicY2DAduZR6dINEARwhc9awOm57tjR2u+g70ZlATOZ2nE+uCTfX+11z4w9diiiho4pxqBR5O2EmnYgLdHBcvQMMBtQr2O0rpxUHSZyTmbsfBcaET1Coa7finDh9U4D1sTHO9bVA2pU9agolV8n13ozwhPAGKKYcAhjOSUjtKfY7Garoyb94/9o3nwSTQC0VSdsDKBJnaAolppy5sguxwyDQACSXHjdPEDetI3Q9MCAXczPimjQwAgPPPrHmTiVrrWeyjaA0jDCMDBw4uJKfVc6C7HCeq2De2ZjNS7RoB5nryCWkgyfhIMQZABjEAa0P7NrNvSL4OQN2ANghuI4ymGotKoWBgc4ScMcC50TgS/jhiU8MABAbIxd1jeBJxOZJGP8Llez3Id7J8sDrrWwBiIiJA7UypaKZgvEEQQHAi6eOU8CmKFTtbr11xAJkiHNwbhuxxKKKbXMDHXZ1QROBkOHVGOvALlC1UahFRr8YLQC5zcJkzTJGMgYkagu1rS28GdeXNMVGsLw3MfFBAduI2ICvtQpguvObdxMEtGOvMAo50hWAddqSYMCAeAMkz3KvsmkGXKV95vVAALzbt5xGIIugDZqRnU3C8QW5dUBt0jDIukzjGoc1PBY0dMViAS1uIxBkEHfCNT05taCdYa7XsyVPYajn02uN4EjEFpZ1hn1XYjEbd4URlOL8VDniCSYIgYh06gBjndB2q7UyNKdPMAl1N7QMz1TA2mDkjM0zROsjiCM9m1Zyy3YFybjhgA1rWjaYABx3o5wHVExqEDsmAnanVoG6QpExfHOR3kQjh4ORB4YrIky+HNIgEtIc4hw1yBAnEYGc01lNwcC3BhGIMtIO1oLZ5SNvG9k6rmt0fpFxILmzqBEDhgkqy9VGVV3rgup2Oqzn1/C4akevAJt48EydnestCSTtXL0Jp3nkmtJQG4p0ngmNCI0Sgai0xC4EgUCcPW1PafWtCvdqI2BjmVB1ztqG95PrJOc+cuZXIhAN2AUV7pMlHfig1Meq3Ek4BRSpUzUN1v+gr6hRDAGjIepQrBZRTbGs4k+tSkrtx44526RXZXElpDpSTUlB0gIb7Kw5tCfK7KCvr6DoPzpt7FXv6IUPugsO1pI8FoAU5QZU9FHN/p16gGwkkd659i2puVUO/M0eULVSkmRdZJ9itYLcGQD1gAcRuM4d6I9tQf2zwEea1KRaFOsXsx9GiBJuuaXEkgySCcTiCdewwhWgskNeb04tBGAcMocBDTxMrZmmNgQqljpnNoU6nZjqhbUbIcWOa7W74XDCHAOiCCeTt6K9riAA9zTgZAYRwxacO9aN2h6JnqxOca+O3IITtCU4gEgDUCR4J1OzPvrMnEGfyP8hCSv/skfiPaUlMq6iVF1q6korjl1uSSSAjdfAIxyCSSAbtfFMceqVxJB2nlyXScEklAViZUy5pJIpr0tFD3h/KfJJJXj6l8XCSSS7OZJJJIEuhJJKEEkklB0JFdSQNTkkkCSSSQJJJJA0pJJIOJJJIP/9k=")
    no-repeat;
  margin-bottom: 8px;
}
.first .text {
  color: #fff;
  font-weight: bold;
}
.first .triangle {
  position: absolute;
  width: 48px;
  height: 48px;
  top: 0;
  left: 0;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png")
    no-repeat;
  padding: 6px;
  font-size: 18px;
}
.first .movietitle {
  position: absolute;
  width: 100%;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  padding: 7px 15px;
  font-size: 14px;
}
.overlaybox ul {
  width: 247px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: #ffe37d;
  padding: 5px 10px;
  color: #3d1f00;
  line-height: 1;
}
.overlaybox li span {
  display: inline-block;
}
.overlaybox li .number {
  font-size: 16px;
  font-weight: bold;
}
.overlaybox li .title {
  font-size: 13px;
}
.overlaybox ul .arrow {
  position: absolute;
  margin-top: 8px;
  right: 25px;
  width: 5px;
  height: 3px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png")
    no-repeat;
}
.overlaybox li .up {
  background-position: 0 -40px;
}
.overlaybox li .down {
  background-position: 0 -60px;
}
.overlaybox li .count {
  position: absolute;
  margin-top: 5px;
  right: 15px;
  font-size: 10px;
}
.overlaybox li:hover {
  color: #fff;
  background: #d24545;
}
.overlaybox li:hover .up {
  background-position: 0 0px;
}
.overlaybox li:hover .down {
  background-position: 0 -20px;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.close:hover {
  cursor: pointer;
}
</style>
