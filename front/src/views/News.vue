<template>
  <div>
    <v-container>
      <v-row>
        <v-col cols="20" sm="6" md="3">
          <v-text-field
            label="Solo"
            placeholder="News Keyword"
            solo
            v-model="keyword"
            @keyup.enter="searchApi"
          ></v-text-field>
        </v-col>
        <v-col cols="4"> </v-col>
      </v-row>
    </v-container>

    <v-container>
      <v-card-title>
        <h1>부동산 뉴스</h1>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="items"
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
      </v-data-table>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      search: '',
      keyword: '부동산',
      items: [],
      headers: [
        { text: '제목', value: 'title' },
        { text: '내용', value: 'description' },
        { text: '날짜', value: 'pubDate' },
        { text: '링크', value: 'link' },
      ],
    };
  },
  created() {
    axios
      .get('http://localhost:7777/happyhouse/api/news/' + this.keyword)
      .then(({ data: { items } }) => {
        console.log(items);
        this.items = items;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    searchApi() {
      axios
        .get('http://localhost:7777/happyhouse/api/news/' + this.keyword)
        .then(({ data: { items } }) => {
          console.log(items);
          this.items = items;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
