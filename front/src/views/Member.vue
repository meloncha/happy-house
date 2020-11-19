<template>
  <div id="app" style="text-align: center;">
    <h1>회원 목록 ( admin 페이지 )</h1>
    <input type="text" v-model="keyword" @keyup.enter="search" />
    <button type="button" class="btn btn-warning" @click="search">검색</button>

    <table class="table table-striped table-bordered">
      <thead class="table-primary">
        <tr>
          <th scope="col">아이디</th>
          <th scope="col">email</th>
          <th scope="col">address</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in members" :key="index">
          <th scope="row">{{ item.userid }}</th>
          <td>{{ item.useremail }}</td>
          <td>{{ item.useraddress }}</td>
        </tr>
      </tbody>
    </table>

    <button type="button" class="btn btn-primary" @click="move">Home</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      keyword: '',
      members: [],
    };
  },
  methods: {
    search() {
      axios
        .get('http://localhost:7777/happyhouse/member/list')
        .then((response) => {
          const word = this.keyword.toUpperCase();
          let arr = response.data;
          this.members = arr.filter((v) => {
            const fname = v.firstName.toUpperCase();
            const lname = v.lastName.toUpperCase();
            return fname.includes(word) || lname.includes(word);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    move() {
      this.$router.push('/');
    },
  },
  created() {
    axios
      .get('http://localhost:7777/happyhouse/member/list')
      .then((response) => {
        this.members = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  watch: {
    keyword() {
      axios
        .get('http://localhost:7777/happyhouse/member/list')
        .then((response) => {
          const word = this.keyword.toUpperCase();
          let arr = response.data;
          this.members = arr.filter((v) => {
            const fname = v.firstName.toUpperCase();
            const lname = v.lastName.toUpperCase();
            return fname.includes(word) || lname.includes(word);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
