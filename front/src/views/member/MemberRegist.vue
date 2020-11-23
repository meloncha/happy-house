<template>
  <div>
    <h1>회원 등록</h1>
    <br />
    <label for="id">아이디</label>
    <input type="text" class="form-control" id="id" v-model="member.userid" />
    <label for="pw">password</label>
    <input
      type="text"
      class="form-control"
      id="pw"
      v-model="member.userpassword"
    />
    <label for="title">email</label>
    <input
      type="text"
      class="form-control"
      id="title"
      v-model="member.useremail"
    />
    <label for="writer">address</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      v-model="member.useraddress"
    />

    <button type="button" class="btn btn-primary" @click="moveHome">홈</button>
    <button type="button" class="btn btn-warning" @click="addMember">
      회원 등록
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      member: {
        userid: "",
        userpassword: "",
        email: "",
        address: ""
      }
    };
  },
  methods: {
    addMember() {
      axios
        .post("http://localhost:7777/happyhouse/member/insert", this.member)
        .then(res => {
          const msg = res.data;
          if (msg == "success") {
            alert("회원 가입에 성공하였습니다");
            this.$router.push("/");
          } else {
            alert("회원 가입에 실패하였습니다.");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    moveHome() {
      this.$router.push("/");
    }
  }
};
</script>

<style></style>
