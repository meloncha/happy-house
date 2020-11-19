<template>
  <div id="app" style="text-align: center;">
    <h1>공지사항</h1>
    <input type="text" v-model="keyword" @keyup.enter="search" />
    <button type="button" class="btn btn-warning" @click="search">검색</button>

    <table class="table table-striped table-bordered">
      <thead class="table-primary">
        <tr>
          <th scope="col">글번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in notices" :key="index">
          <th scope="row">{{ item.id }}</th>
          <td>
            <router-link :to="{ name: 'QnaDetail', params: { id: item.id } }">{{
              item.title
            }}</router-link>
          </td>
          <td>{{ item.writer }}</td>
          <td>{{ item.date }}</td>
        </tr>
      </tbody>
    </table>

    <button type="button" class="btn btn-primary" @click="move">Q&A 등록</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      keyword: '',
      notices: [],
    };
  },
  methods: {
    search() {
      axios
        .get('http://localhost:7777/happyhouse/notice/page/1')
        .then((response) => {
          const word = this.keyword.toUpperCase();
          let arr = response.data;
          this.notices = arr.filter((v) => {
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
      this.$router.push('/qna/regist');
    },
    addComment() {
      this.$router.push('/comment');
    },
  },
  created() {
    axios
      .get('http://localhost:7777/happyhouse/notice/page/1')
      .then((response) => {
        this.notices = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  watch: {
    keyword() {
      axios
        .get('http://localhost:7777/happyhouse/notice/page/1')
        .then((response) => {
          const word = this.keyword.toUpperCase();
          let arr = response.data;
          this.notices = arr.filter((v) => {
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
