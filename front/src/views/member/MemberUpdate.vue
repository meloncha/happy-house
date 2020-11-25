<template>
  <div>
    <h1>마이페이지</h1>
    <br />
    <label for="id">아이디</label>
    <input type="text" class="form-control" id="id" v-model="member.id" disabled />
    <label for="pw">password</label>
    <input type="text" class="form-control" id="pw" v-model="member.pwd" />
    <label for="nn">name</label>
    <input type="text" class="form-control" id="nn" v-model="member.name" />
    <label for="pp">phone</label>
    <input type="text" class="form-control" id="pp" v-model="member.phone" />
    <label for="title">email</label>
    <input type="text" class="form-control" id="title" v-model="member.email" />
    <label for="writer">address</label>
    <input type="text" class="form-control" id="writer" v-model="member.address" />

    <button type="button" class="btn btn-primary" @click="moveHome">홈</button>
    <button type="button" class="btn btn-warning" @click="updateMember">
      개인정보 수정
    </button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      member: {
        id: '',
        pwd: '',
        email: '',
        address: '',
        name: '',
        phone: '',
      },
    };
  },
  created() {
    this.member.id = this.$route.params.id;

    axios
      .get('http://127.0.0.1:7777/happyhouse/member/detail/' + this.member.id)
      .then((res) => {
        this.member = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    updateMember() {
      axios.put('http://localhost:7777/happyhouse/member/update', this.member).then((res) => {
        const msg = res.data;
        if (msg == 'success') {
          alert('회원 수정에 성공하였습니다');
          this.$router.push('/member/detail/' + this.member.id);
        } else {
          alert('회원 수정에 실패하였습니다.');
        }
      });
    },
    moveHome() {
      this.$router.push('/');
    },
  },
};
</script>

<style></style>
