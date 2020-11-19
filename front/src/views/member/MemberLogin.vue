<template>
  <div>
    <label for="id">ID</label>
    <input type="text" class="form-control" id="id" v-model="member.userid" />
    <label for="pw">PASSWORD</label>
    <input type="password" class="form-control" id="pw" v-model="member.userpassword" />
    <b-btn @click="login">로그인</b-btn>
    <b-btn @click="moveJoin">회원가입</b-btn>
    <b-btn @click="moveHome">HOME</b-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      member: {
        userid: '',
        userpassword: '',
      },
    };
  },
  methods: {
    login() {
      axios
      .post('http://localhost:7777/happyhouse/member/login', this.member)
      .then((res) => {
        const msg = res.data;
        if (msg == 'success') {
          alert('로그인에 성공하였습니다.');
          localStorage.setItem('id', this.member.userid);
          this.$router.push('/');
        } else {
          alert('로그인에 실패하였습니다.');
          this.member.userid = '';
          this.member.userpassword = '';
        }
      });
    },
    moveHome() {
        this.$router.push('/');
    },
    moveJoin() {
        this.$router.push('/member/regist');
    }
  },
};
</script>

<style></style>
