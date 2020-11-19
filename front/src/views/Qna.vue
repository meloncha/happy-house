<template>
  <div id="app" style="text-align: center;">
    <h1>Q&A 목 록</h1>
    <input type="text" v-model="keyword" @keyup.enter="search" />
    <button type="button" class="btn btn-warning" @click="search">검색</button>

    <table class="table table-striped table-bordered">
      <thead class="table-primary">
        <tr>
          <th scope="col">글번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일</th>
          <th scope="col">답변버튼</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in qnas" :key="index">
          <th scope="row">{{ item.id }}</th>
          <td>
            <router-link :to="{ name: 'QnaDetail', params: { id: item.id } }">{{
              item.title
            }}</router-link>
          </td>
          <td>{{ item.writer }}</td>
          <td>{{ item.date }}</td>
          <td><b-btn @click="addComment(item.id)">답변하기</b-btn></td>
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
      qnas: [],
    };
  },
  methods: {
    search() {
      axios
        .get('http://localhost:7777/happyhouse/qna/list')
        .then((response) => {
          const word = this.keyword.toUpperCase();
          let arr = response.data;
          this.qnas = arr.filter((v) => {
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
    addComment(id) {
      this.$router.push('/qna/comment/' + id);
    },
  },
  created() {
    console.log('create');
    axios
      .get('http://localhost:7777/happyhouse/qna/list')
      .then((response) => {
        this.qnas = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  watch: {
    keyword() {
      axios
        .get('http://localhost:7777/happyhouse/qna/list')
        .then((response) => {
          const word = this.keyword;
          let arr = response.data;
          this.qnas = arr.filter((v) => {
            const title = v.title;
            return title.includes(word);
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
