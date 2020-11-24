<template>
  <div>
    <v-container>
      <v-card-title>
        <h1>공지사항</h1>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
        <template v-if="this.$store.getters.getUserId == 'admin'">
          <div class="col-sm-1">
            <v-btn @click="move" color="primary" dark class="mb-2">
              작성하기
            </v-btn>
          </div>
        </template>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="notices"
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
            <tr v-for="(item, index) in items" :key="index" @click="handleClick(item)">
              <td>{{ index + 1 }}</td>
              <td v-html="item.title"></td>
              <td v-html="item.writer"></td>
              <td v-html="item.date"></td>
              <td>{{ item.readcnt }}</td>
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
      keyword: '',
      search: '',
      notices: [],
      headers: [
        { text: '글번호', value: 'num' },
        { text: '제목', value: 'title' },
        { text: '작성자', value: 'writer' },
        { text: '작성일', value: 'date' },
        { text: '조회수', value: 'readcnt' },
      ],
    };
  },
  methods: {
    move() {
      this.$router.push('/notice/regist');
    },
    handleClick(item) {
      this.$router.push('/notice/detail/' + item.num);
    },
  },
  created() {
    axios
      .get(`http://localhost:7777/happyhouse/notice`)
      .then(({ data }) => {
        console.log(data);
        this.notices = data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style></style>
