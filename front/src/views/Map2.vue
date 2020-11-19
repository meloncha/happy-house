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
          <button class="btn btn-outline-dark" style="float: right" @click="search">검색</button>
        </div>
      </div>
      <hr />

      <div class="row">
        <h6 style="margin-left: 30px; margin-right: 20px">주소</h6>
        시도 :
        <select id="sido" v-model="sido">
          <option :value="item.sidoCode" v-for="(item, index) in sidos" :key="index">{{
            item.sidoName
          }}</option>
        </select>
        구군 :
        <select id="gugun" v-model="gugun">
          <option :value="item.gugunCode" v-for="(item, index) in guguns" :key="index">{{
            item.gugunName
          }}</option>
        </select>
        읍면동 :
        <select id="dong" v-model="dong">
          <option :value="item.dong" v-for="(item, index) in dongs" :key="index">{{
            item.dong
          }}</option>
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
  </div>
</template>

<script>
import axios from 'axios';

// const colorArr = ['table-primary', 'table-success', 'table-danger'];

export default {
  data() {
    return {
      keyword: '',
      sidos: [],
      sido: {},
      guguns: [],
      gugun: {},
      dongs: [],
      dong: {},
      apts: [],
    };
  },
  methods: {
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
    // geocode(jsonData) {
    // 	let idx = 0;
    // 	$.each(jsonData, function (index, vo) {
    // 		let tmpLat;
    // 		let tmpLng;
    // 		alert(vo.aptName)
    // 		$.get("https://maps.googleapis.com/maps/api/geocode/json"
    // 			, {
    // 				key: 'AIzaSyC1fWgvVTDiVDjhUzUok0hbqiiWUaSV1hU&callback'
    // 				, address: vo.dong + "+" + vo.aptName + "+" + vo.jibun
    // 			}
    // 			, function (data, status) {
    // 				alert(data.results[0].geometry.location.lat);
    // 				tmpLat = data.results[0].geometry.location.lat;
    // 				tmpLng = data.results[0].geometry.location.lng;
    // 				$("#lat_" + index).text(tmpLat);
    // 				$("#lng_" + index).text(tmpLng);
    // 				addMarker(tmpLat, tmpLng, vo.aptName);
    // 			}
    // 			, "json"
    // 		);//get
    // 	});//each
    // }
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
			axios
        .get(`http://localhost:7777/happyhouse/map/apt/search/${this.keyword}`)
        .then(({ data }) => {
          this.apts = data;
        })
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
  },
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

#map {
  margin: 0 auto;
  height: 65%;
  width: 65%;
}

html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
