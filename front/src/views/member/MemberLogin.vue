<template>
  <div>

    <div class="container-fluid">
      <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
          <div class="login d-flex align-items-center py-5">
            <div class="container">
              <div class="row">
                <div class="col-md-9 col-lg-8 mx-auto">
                  <h1 class="login-heading mb-4">HappyHouse LOGIN</h1>
                  <div class="form-label-group">
                    <input
                      type="text"
                      id="inputEmail"
                      class="form-control"
                      placeholder="id"
                      v-model="member.id"
                      required
                      autofocus
                    />
                    <label for="inputEmail">User ID</label>
                  </div>

                  <div class="form-label-group">
                    <input
                      type="password"
                      id="inputPassword"
                      class="form-control"
                      placeholder="Password"
                      v-model="member.pwd"
                      required
                      @keyup.enter="login"
                    />
                    <label for="inputPassword">Password</label>
                  </div>

                  <button
                    class="btn btn-lg btn-warning btn-login text-uppercase font-weight-bold mb-2"
                    @click="login"
                  >
                    LOG IN
                  </button>
                  <button
                    style="hegiht: 50px"
                    class="btn btn-lg btn-login text-uppercase font-weight-bold mb-2"
                    @click="kakaoLogin"
                  >
                    <img
                      height="50"
                      src="@/assets/kakao_login_large_narrow.png"
                      alt="kakao-login"
                    />
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import alertify from 'alertifyjs';

export default {
  data() {
    return {
      member: {
        id: '',
        pwd: '',
      },
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  methods: {
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('LOGIN', this.member)
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
    moveHome() {
      this.$router.push('/');
    },
    moveJoin() {
      this.$router.push('/member/regist');
    },
    kakaoLogin() {
      // console.log(window.Kakao);
      window.Kakao.Auth.cleanup();

      window.Kakao.Auth.login({
        scope: 'account_email,profile',
        success: this.GetMe,
      });
    },
    GetMe(authObj) {
      console.log(authObj);

      window.Kakao.API.request({
        url: '/v2/user/me',
        success: (res) => {
          console.log(res);
          const kakao_account = res.kakao_account;
          console.log(kakao_account.profile);
          const userInfo = {
            id: kakao_account.email,
            name: kakao_account.profile.nickname,
            email: kakao_account.email,
            pwd: kakao_account.email,
          };

          localStorage.setItem('img', kakao_account.profile.profile_image_url);

          console.log(userInfo);
          this.$store
            .dispatch('KAKAO_LOGIN', userInfo)
            .then(() => this.$router.replace(`/${this.nextRoute}`))
            .catch(({ message }) => (this.msg = message));

          // alertify.alert('로그인 성공!!', function() {
          //   alertify.message('OK');
          // });

          this.$bvModal.hide('bv-modal-example');
        },
        fail: (error) => {
          console.log(error);
        },
      });
      this.$router.push('/');
    },
  },
};
</script>

<style>
:root {
  --input-padding-x: 1.5rem;
  --input-padding-y: 0.75rem;
}

.login,
.image {
  min-height: 100vh;
}

.bg-image {
  background-image: url('https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');
  background-size: cover;
  background-position: center;
}

.login-heading {
  font-weight: 300;
}

.btn-login {
  font-size: 0.9rem;
  letter-spacing: 0.05rem;
  padding: 0.75rem 1rem;
  border-radius: 2rem;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group > input,
.form-label-group > label {
  padding: var(--input-padding-y) var(--input-padding-x);
  height: auto;
  border-radius: 2rem;
}

.form-label-group > label {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  margin-bottom: 0;
  /* Override default `<label>` margin */
  line-height: 1.5;
  color: #495057;
  cursor: text;
  /* Match the input under the label */
  border: 1px solid transparent;
  border-radius: 0.25rem;
  transition: all 0.1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown) ~ label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}

/* Fallback for Edge
-------------------------------------------------- */

@supports (-ms-ime-align: auto) {
  .form-label-group > label {
    display: none;
  }
  .form-label-group input::-ms-input-placeholder {
    color: #777;
  }
}

/* Fallback for IE
-------------------------------------------------- */

@media all and (-ms-high-contrast: none), (-ms-high-contrast: active) {
  .form-label-group > label {
    display: none;
  }
  .form-label-group input:-ms-input-placeholder {
    color: #777;
  }
}
</style>
