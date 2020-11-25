<template>
  <div>
    <div class="container mt-5" style="background: rgb(255, 253, 214); border-radius: 2em">
      <div class="row" style="margin-top: 20px">
        <div class="col-sm-8">
          <h1>아파트 매매 정보 (2020.10)</h1>
        </div>
      </div>
      <hr />

      <div class="row">
        <h6 style="margin-left: 30px; margin-right: 20px">주소</h6>
        시도 :
        <select id="sido" v-model="sido" style="border: 1px solid">
          <option :value="item.sidoCode" v-for="(item, index) in sidos" :key="index">
            {{ item.sidoName }}
          </option>
        </select>
        구군 :
        <select id="gugun" v-model="gugun" style="border: 1px solid">
          <option :value="item.gugunCode" v-for="(item, index) in guguns" :key="index">
            {{ item.gugunName }}
          </option>
        </select>
      </div>
      <hr />

      <br /><br />
    </div>

    <b-container class="bv-example-row" style="margin:0 auto; width: 70%;">
      <b-row>
        <b-col cols="5" align="left">
          <apt-list :aptList="apts" @select-apt="selectApt" />
        </b-col>
        <b-col cols="7">
          <apt-detail :apt="selectedApt" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import AptList from '@/components/AptList.vue';
import AptDetail from '@/components/AptDetail.vue';
import axios from 'axios';

// vue cli enviroment variables 검색
// 반드시 VUE_APP으로 시작해야 한다.
// const API_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
// const API_URL =
//   'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?LAWD_CD=11110&DEAL_YMD=201512&serviceKey=';
/*
<거래금액>82,500</거래금액>
<건축년도>2008</건축년도>
<년>2015</년>
<도로명>사직로8길</도로명>
<도로명건물본번호코드>00004</도로명건물본번호코드>
<도로명건물부번호코드>00000</도로명건물부번호코드>
<도로명시군구코드>11110</도로명시군구코드>
<도로명일련번호코드>03</도로명일련번호코드>
<도로명지상지하코드>0</도로명지상지하코드>
<도로명코드>4100135</도로명코드>
<법정동>사직동</법정동>
<법정동본번코드>0009</법정동본번코드>
<법정동부번코드>0000</법정동부번코드>
<법정동시군구코드>11110</법정동시군구코드>
<법정동읍면동코드>11500</법정동읍면동코드>
<법정동지번코드>1</법정동지번코드>
<아파트>광화문풍림스페이스본(9-0)</아파트>
<월>12</월>
<일>1</일>
<전용면적>94.51</전용면적>
<지번>9</지번>
<지역코드>11110</지역코드>
<층>11</층>
*/

export default {
  name: 'Apt',
  components: {
    AptList,
    AptDetail,
  },
  data() {
    return {
      dongCode: '',
      apts: [],
      // selectedApt: {},
      date: 202010,
      selectedApt: '',
      sidos: [],
      sido: {},
      guguns: [],
      gugun: {},
      dongs: [],
      dong: {},
    };
  },
  created() {
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
    sido() {
      console.log(this.sido);
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
      this.sendDongCode(this.gugun);
    },
  },
  methods: {
    sendDongCode(dongCode) {
      console.log(dongCode);
      this.dongCode = dongCode;

      const API_KEY =
        'FoQwxActT1yGsjTvn%2Fa883AmpwaaoG93IZmXH4unLLOSv9lBENc0tNI66%2B9jNe2aGQIGVL9ckDlPz%2BwjaXvwyg%3D%3D';
      const API_URL =
        'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?LAWD_CD=' +
        dongCode +
        '&DEAL_YMD=' +
        this.date +
        '&serviceKey=' +
        API_KEY;

      axios
        .get(API_URL)
        .then((response) => {
          console.log(response);
          this.apts = response.data.response.body.items.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    selectApt(apt) {
      this.selectedApt = apt;
    },
  },
};
</script>

<style></style>
