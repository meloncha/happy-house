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
      showModal: false,
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

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
