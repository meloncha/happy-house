<template>
  <div>
    <h1>마이페이지</h1>
    <br />
    <label for="id">아이디</label>
    <input
      type="text"
      class="form-control"
      id="id"
      v-model="member.userid"
      disabled
    />
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
    <button type="button" class="btn btn-warning" @click="updateMember">
      개인정보 수정
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
  created() {
    this.member.userid = this.$route.params.id;

    axios
      .get(
        "http://127.0.0.1:7777/happyhouse/member/detail/" + this.member.userid
      )
      .then(res => {
        this.member = res.data;
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {
    updateMember() {
      axios
        .put("http://localhost:7777/happyhouse/member/update", this.member)
        .then(res => {
          const msg = res.data;
          if (msg == "success") {
            alert("회원 수정에 성공하였습니다");
            this.$router.push("/member/detail/" + this.member.userid);
          } else {
            alert("회원 수정에 실패하였습니다.");
          }
        });
    },
    moveHome() {
      this.$router.push("/");
    }
  }
};
</script>

<style></style>
