<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="/">Happy House</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <!-- <router-link to="/apt">아파트정보</router-link> -->
          <b-navbar-nav>
            <!-- <router-link to="/apt">아파트정보</router-link> -->
            <b-nav-item><router-link to="/apt">아파트정보</router-link></b-nav-item>
            <b-nav-item><router-link to="/about">About</router-link></b-nav-item>
            <b-nav-item><router-link to="/qna">Q&A</router-link></b-nav-item>
            <b-nav-item><router-link to="/notice">공지사항</router-link></b-nav-item>
            <b-nav-item><router-link to="/map">지도 검색</router-link></b-nav-item>
            <b-nav-item><router-link to="/map2">지도 검색2</router-link></b-nav-item>
          </b-navbar-nav>
        </b-navbar-nav>
      </b-collapse>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto" v-if="getAccessToken">
          <b-nav-item
            ><b-avatar variant="primary" v-text="getUserId.charAt(0).toUpperCase()"></b-avatar
            >{{ getUserName }}({{ getUserId }})님 환영합니다.</b-nav-item
          >
          <b-nav-item><router-link to="/member/detail">내정보보기</router-link></b-nav-item>
          <b-nav-item @click.prevent="onClickLogout">로그아웃</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item> <router-link to="/member/login">로그인</router-link></b-nav-item>
          <b-nav-item><router-link to="/member/regist">회원가입</router-link></b-nav-item>
          <!-- <b-nav-item href="/login">로그인</b-nav-item>
            <b-nav-item href="/join">회원가입</b-nav-item> -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
//mapGetters 등록
import { mapGetters } from 'vuex';

export default {
  name: 'Header',
  data() {
    return {
      isLogin: false,
    };
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    onClickLogout() {
      this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/').catch(() => {}));
    },
  },
  updated() {
    console.log('header-update');
    console.log(this.getUserId);
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

a {
  color: rgb(219, 250, 250);
}

a:hover {
  color: rgb(219, 250, 250);
  text-decoration: none;
}
</style>
