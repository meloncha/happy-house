<template>
  <div>
    <label for="id">ID</label>
    <input type="text" class="form-control" id="id" v-model="member.id" />
    <label for="pw">PASSWORD</label>
    <input type="password" class="form-control" id="pw" v-model="member.pwd" />
    <b-btn @click="login">로그인</b-btn>
    <b-btn @click="moveJoin">회원가입</b-btn>
    <b-btn @click="moveHome">HOME</b-btn> <br />
    <button @click="kakaoLogin">
      <img
        height="40"
        src="@/assets/kakao_login_medium_narrow.png"
        alt="kakao-login"
      />
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      member: {
        id: "",
        pwd: ""
      }
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", this.member)
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
    moveHome() {
      this.$router.push("/");
    },
    moveJoin() {
      this.$router.push("/member/regist");
    },
    kakaoLogin() {
      // console.log(window.Kakao);
      window.Kakao.Auth.cleanup();

      window.Kakao.Auth.login({
        scope: "account_email,profile",
        success: this.GetMe
      });
    },
    GetMe(authObj) {
      console.log(authObj);

      window.Kakao.API.request({
        url: "/v2/user/me",
        success: res => {
          console.log(res);
          const kakao_account = res.kakao_account;
          console.log(kakao_account.profile);
          const userInfo = {
            id: kakao_account.email,
            name: kakao_account.profile.nickname,
            email: kakao_account.email,
            pwd: kakao_account.email
          };

          localStorage.setItem("img", kakao_account.profile.profile_image_url);

          console.log(userInfo);
          this.$store
            .dispatch("KAKAO_LOGIN", userInfo)
            .then(() => this.$router.replace(`/${this.nextRoute}`))
            .catch(({ message }) => (this.msg = message));

          alert("로그인 성공!");
          this.$bvModal.hide("bv-modal-example");
        },
        fail: error => {
          console.log(error);
        }
      });
      this.$router.push("/");
    }
  }
};
</script>

<style></style>
