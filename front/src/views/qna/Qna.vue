<template>
  <div>
    <v-container>
      <v-card-title>
        <h1>Q&A 목록</h1>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="질문 검색"
          single-line
          hide-details
        ></v-text-field>
        <template>
          <div class="col-sm-1">
            <v-btn @click="move" color="primary" dark class="mb-2">
              질문 작성
            </v-btn>
          </div>
        </template>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="qnas"
        :items-per-page="5"
        :search="search"
        :footer-props="{
          showFirstLastPage: true,
          firstIcon: 'mdi-arrow-collapse-left',
          lastIcon: 'mdi-arrow-collapse-right',
          prevIcon: 'mdi-minus',
          nextIcon: 'mdi-plus',
        }"
      >
        <template v-slot:body="{ items }">
          <tbody>
            <tr v-for="(item, index) in items" :key="index">
              <td @click="handleClick(item)">{{ index + 1 }}</td>
              <td v-html="item.title" @click="handleClick(item)"></td>
              <td v-html="item.writer" @click="handleClick(item)"></td>
              <td v-html="item.date" @click="handleClick(item)"></td>
              <td>
                <div v-if="item.comment">
                  <v-btn style="color: red;"><b>답변완료</b></v-btn>
                </div>
                <div v-else>
                  <v-btn style="color: blue;" @click="addComment(item.id)"><b>답변하기</b></v-btn>
                </div>
              </td>
            </tr>
          </tbody>
        </template>
      </v-data-table>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      selected: [],
      search: '',
      keyword: '',
      qnas: [],
      headers: [
        { text: '글번호', value: 'id' },
        { text: '제목', value: 'title' },
        { text: '작성자', value: 'writer' },
        { text: '작성일', value: 'date' },
        { text: '답변여부', value: 'comment' },
      ],
    };
  },
  methods: {
    move() {
      this.$router.push('/qna/regist');
    },
    addComment(id) {
      this.$router.push('/qna/comment/' + id);
    },
    handleClick(item) {
      this.$router.push('qna/detail/' + item.id);
    },
  },
  created() {
    console.log('create');
    axios
      .get('http://localhost:7777/happyhouse/qna/list')
      .then((response) => {
        console.log(response.data);
        this.qnas = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style></style>
