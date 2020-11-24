<template>
  <div>
    <b-navbar toggleable="lg" style="background-color:#fffdd6;">
      <b-navbar-brand
        ><router-link to="/"
          ><img height="100" src="@/assets/happyhouselogo3.png" alt=""/></router-link
      ></b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item @click="moveApt">Apt</b-nav-item>
          <b-nav-item @click="moveAbout">About</b-nav-item>
          <b-nav-item @click="moveQna">Q&A</b-nav-item>
          <b-nav-item @click="moveNotice">Notice</b-nav-item>
          <b-nav-item @click="moveMap">Map</b-nav-item>
          <b-nav-item @click="moveNews">News</b-nav-item>
          <b-nav-item @click="moveMember" v-if="this.$store.getters.getUserId == 'admin'"
            >Member</b-nav-item
          >
        </b-navbar-nav>
      </b-collapse>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto" v-if="getAccessToken">
          <b-nav-item @click="moveDetail"
            ><b-avatar variant="warning" v-text="getUserId.charAt(0).toUpperCase()"></b-avatar
            >{{ getUserName }}({{ getUserId }})님 환영합니다.</b-nav-item
          >
          <b-nav-item @click="onClickLogout">로그아웃</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item @click="moveLogin">로그인</b-nav-item>
          <b-nav-item @click="moveRegist">회원가입</b-nav-item>
          <!-- <b-nav-item href="/login">로그인</b-nav-item>
            <b-nav-item href="/join">회원가입</b-nav-item> -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import axios from 'axios';
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
    intercepter() {
      if (this.$store.getters.getAccessToken == null) {
        alert('로그인이 필수 입니다.');
        this.$router.push('/');
      }
    },
    onClickLogout() {
      this.$store.dispatch('LOGOUT').then(() => this.$router.push('/logout').catch(() => {}));

      window.Kakao.Auth.logout();

      axios
        .get(
          'https://kauth.kakao.com/oauth/logout?client_id=1477c3cde72d97e6eb6a3fde455fd68a&logout_redirect_uri=http://localhost:8080/logout'
        )
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    moveLogin() {
      this.$router.push('/member/login');
    },
    moveRegist() {
      this.$router.push('/member/regist');
    },
    moveDetail() {
      this.$router.push('/member/detail');
    },
    moveApt() {
      this.$router.push('/apt');
      this.intercepter();
    },
    moveNotice() {
      this.$router.push('/notice');
      this.intercepter();
    },
    moveAbout() {
      this.$router.push('/about');
      this.intercepter();
    },
    moveQna() {
      this.$router.push('/qna');
      this.intercepter();
    },
    moveMap() {
      this.$router.push('/map2');
      this.intercepter();
    },
    moveNews() {
      this.$router.push('/news');
      this.intercepter();
    },
    moveMember() {
      this.$router.push('/member');
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

#nav-collapse {
  font-family: KOTRA_BOLD-Bold;
  font-size: 20px;
  color: black !important;
}
@font-face {
  font-family: 'KOTRA_BOLD-Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.1/KOTRA_BOLD-Bold.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}

a:hover {
  color: rgb(219, 250, 250);
  text-decoration: none;
}
</style>
