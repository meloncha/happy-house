<template>
  <div>
    <h1>마이페이지</h1>
    <br />
    <img :src="img" alt="프로필 이미지" height="200" /> <br />

    <label for="id">아이디</label>
    <input type="text" class="form-control" id="id" v-model="member.id" disabled />
    <label for="title">email</label>
    <input type="text" class="form-control" id="title" v-model="member.email" disabled />
    <label for="writer">name</label>
    <input type="text" class="form-control" id="writer" v-model="member.name" disabled />
    <label for="writer">address</label>
    <input type="text" class="form-control" id="writer" v-model="member.address" disabled />

    <button type="button" class="btn btn-primary" @click="moveHome">홈</button>
    <button type="button" class="btn btn-warning" @click="updateMember">개인정보 수정</button>
    <button type="button" class="btn btn-danger" @click="deleteMember">회원 탈퇴</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      member: {
        id: '',
        email: '',
        address: '',
        name: '',
      },
      img: '',
    };
  },
  created() {
    this.member.userid = this.$store.getters.getUserId;
    this.img = localStorage.getItem('img');
    console.log(this.img);

    axios
      .get('http://127.0.0.1:7777/happyhouse/member/detail/' + this.member.userid)
      .then((res) => {
        this.member = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    updateMember() {
      const id = localStorage.getItem('id');
      this.$router.push('/member/update/' + id);
    },
    moveHome() {
      this.$router.push('/');
    },
    deleteMember() {
      console.log(this.member.userid, this.member.userpassword);
      axios
        .post('http://127.0.0.1:7777/happyhouse/member/delete/', this.member)
        .then((res) => {
          const msg = res.data;
          if (msg == 'success') {
            localStorage.removeItem('id');
            alert('삭제 성공했습니다.');
          } else {
            alert('삭제 실패했습니다.');
          }
          this.$router.push('/');
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
