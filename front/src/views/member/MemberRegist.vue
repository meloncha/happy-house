<template>
  <div>
    <body>
      <div class="container">
        <div class="row">
          <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card card-signin flex-row my-5">
              <div class="card-img-left d-none d-md-flex">
                <!-- Background image for card set in CSS! -->
              </div>
              <div class="card-body">
                <h1 class="card-title text-center">회원 가입</h1>
                <div class="form-label-group">
                  <input
                    type="text"
                    id="inputId"
                    class="form-control"
                    placeholder="Username"
                    required
                    autofocus
                    v-model="member.id"
                  />
                  <label for="inputId">ID</label>
                </div>

                <div class="form-label-group">
                  <input
                    type="text"
                    id="Username"
                    class="form-control"
                    placeholder="Username"
                    required
                    v-model="member.name"
                  />
                  <label for="Username">User name</label>
                </div>

                <div class="form-label-group">
                  <input
                    type="email"
                    id="inputEmail"
                    class="form-control"
                    placeholder="Email address"
                    required
                    v-model="member.email"
                  />
                  <label for="inputEmail">Email address</label>
                </div>

                <hr />

                <div class="form-label-group">
                  <input
                    type="password"
                    id="inputPassword"
                    class="form-control"
                    placeholder="Password"
                    required
                    v-model="member.pwd"
                  />
                  <label for="inputPassword">Password</label>
                </div>

                <div class="form-label-group">
                  <input
                    type="password"
                    id="inputConfirmPassword"
                    class="form-control"
                    placeholder="Password"
                    required
                    v-model="confirmPassword"
                  />
                  <label for="inputConfirmPassword">Confirm password</label>
                </div>

                <hr />

                <div class="form-label-group">
                  <input
                    type="text"
                    id="phone"
                    class="form-control"
                    placeholder="Username"
                    v-model="member.phone"
                  />
                  <label for="phone">Phone</label>
                </div>

                <div class="form-label-group">
                  <input
                    type="text"
                    id="address"
                    class="form-control"
                    placeholder="Username"
                    v-model="member.address"
                  />
                  <label for="address">Address</label>
                </div>

                <button class="btn btn-lg btn-warning btn-block text-uppercase" @click="addMember">
                  Register
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      confirmPassword: '',
      member: {
        id: '',
        pwd: '',
        name: '',
        email: '',
        address: '',
        phone: '',
      },
    };
  },
  methods: {
    addMember() {
      if (this.member.pwd != this.confirmPassword) {
        alert('비밀번호를 확인하세요');
        return;
      }

      // 제약 조건
      if (this.member.id == '') {
        alert('아이디를 입력하세요');
        return;
      } else if (this.member.name == '') {
        alert('이름을 입력하세요');
        return;
      } else if (this.member.email == '') {
        alert('이메일을 입력하세요');
        return;
      } else if (this.member.pwd == '') {
        alert('비밀번호를 입력하세요');
        return;
      }

      axios
        .post('http://localhost:7777/happyhouse/member/insert', this.member)
        .then((res) => {
          const msg = res.data;
          if (msg == 'success') {
            alert('회원 가입에 성공하였습니다');
            this.$router.push('/');
          } else {
            alert('회원 가입에 실패하였습니다.');
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveHome() {
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

body {
  background: #b9ab56;
  background: linear-gradient(to right, #faedcb, #f7d9c4);
}

.card-signin {
  border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.card-signin .card-title {
  margin-bottom: 2rem;
  font-weight: 300;
  font-size: 1.5rem;
}

.card-signin .card-img-left {
  width: 45%;
  /* Link to your background image using in the property below! */
  background: scroll center
    url('https://cdn.pixabay.com/photo/2019/03/31/14/31/italy-4093227_960_720.jpg');
  background-size: cover;
}

.card-signin .card-body {
  padding: 2rem;
}

.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: 0.1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

.form-label-group > input,
.form-label-group > label {
  padding: var(--input-padding-y) var(--input-padding-x);
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

.btn-google {
  color: white;
  background-color: #ea4335;
}

.btn-facebook {
  color: white;
  background-color: #3b5998;
}
</style>
