<template>
  <div id="app">
    <div class="container mt-5" style="background: #ffc95c">
      <div class="row" style="margin-top: 20px">
        <div class="col-sm-8">
          <br />
          <h1>지도 상세 검색</h1>
        </div>
        <div class="col-sm-3">
          <br />
          <input
            class="form-control"
            style="float: left"
            type="text"
            placeholder="Search"
          />
        </div>
        <div class="col-sm-1">
          <br />
          <button
            class="btn btn-outline-dark"
            style="float: right"
            type="submit"
          >
            Search
          </button>
        </div>
      </div>
      <hr />
      <div class="row">
        <h6 style="margin-left: 30px; margin-right: 20px">주소</h6>
        시도 :
        <select id="sido">
          <option value="0">선택</option>
        </select>
        구군 :
        <select id="gugun">
          <option value="0">선택</option>
        </select>
        읍면동 :
        <select id="dong">
          <option value="0">선택</option>
        </select>
        <div>
          종류
          <div
            class="custom-control custom-radio custom-control-inline"
            style="margin-left: 30px"
          >
            <input
              type="radio"
              class="custom-control-input"
              id="1"
              name="1"
              value="1"
            />
            <label class="custom-control-label" for="1">아파트</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="2"
              name="2"
              value="2"
            />
            <label class="custom-control-label" for="2">빌라</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="3"
              name="3"
              value="3"
            />
            <label class="custom-control-label" for="3">오피스텔</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          학군
          <div
            class="custom-control custom-radio custom-control-inline"
            style="margin-left: 30px"
          >
            <input
              type="radio"
              class="custom-control-input"
              id="4"
              name="4"
              value="4"
            />
            <label class="custom-control-label" for="4">초등학교</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="5"
              name="5"
              value="5"
            />
            <label class="custom-control-label" for="5">중학교</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="6"
              name="6"
              value="6"
            />
            <label class="custom-control-label" for="6">고등학교</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          상권
          <div
            class="custom-control custom-radio custom-control-inline"
            style="margin-left: 30px"
          >
            <input
              type="radio"
              class="custom-control-input"
              id="7"
              name="7"
              value="7"
            />
            <label class="custom-control-label" for="7">음식점</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="8"
              name="8"
              value="8"
            />
            <label class="custom-control-label" for="8">마트</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="9"
              name="9"
              value="9"
            />
            <label class="custom-control-label" for="9">백화점</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="10"
              name="10"
              value="10"
            />
            <label class="custom-control-label" for="10">문화시설</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="11"
              name="11"
              value="11"
            />
            <label class="custom-control-label" for="11">유흥시설</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          교통
          <div
            class="custom-control custom-radio custom-control-inline"
            style="margin-left: 30px"
          >
            <input
              type="radio"
              class="custom-control-input"
              id="12"
              name="12"
              value="12"
            />
            <label class="custom-control-label" for="12">지하철</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="13"
              name="13"
              value="13"
            />
            <label class="custom-control-label" for="13">버스</label>
          </div>
        </div>
      </div>
      <hr />
      <div class="row" style="margin-left: 20px">
        <div>
          치안
          <div
            class="custom-control custom-radio custom-control-inline"
            style="margin-left: 30px"
          >
            <input
              type="radio"
              class="custom-control-input"
              id="14"
              name="14"
              value="14"
            />
            <label class="custom-control-label" for="14">CCTV</label>
          </div>
          <div class="custom-control custom-radio custom-control-inline">
            <input
              type="radio"
              class="custom-control-input"
              id="15"
              name="15"
              value="15"
            />
            <label class="custom-control-label" for="15">범죄율</label>
          </div>
        </div>
      </div>
      <hr />
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

      <tbody></tbody>
    </table>
    <div id="map">
      <hr />
      <div id="map"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Map",

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap(); // 초기 맵 으로 이동 (지금은 카카오 좌표)
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ee37a1035dfa14e56abd773fe878b0fc";
      document.head.appendChild(script);

      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667); //methods 호출하고 axios로 vue 드롭다운 메뉴에 있는 좌표 값으로 변경

      var marker = new kakao.maps.Marker({
        position: markerPosition
      });
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      }; // 지도를 표시할 div
      var map = new kakao.maps.Map(container, options);
      marker.setMap(map);
      map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
    }
  },
  methods: {
    //axios로 vue 드롭다운 메뉴에 있는 좌표 값 리스트로
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(36.354975, 127.298382),
        level: 3
      };

      var markerPosition = new kakao.maps.LatLng(36.354975, 127.298382);

      var marker = new kakao.maps.Marker({
        position: markerPosition
      });
      var map = new kakao.maps.Map(container, options);
      marker.setMap(map);
      map.setMapTypeId(kakao.maps.MapTypeId.kakao);
    }
  }
};
</script>

<style>
#map {
  width: 1300px;
  height: 700px;
  margin: 0px auto;
}
</style>
