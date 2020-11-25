<template>
  <div style="margin:0 auto; width: 70%; text-align: left">
    <h1>공지사항 등록</h1>
    <br />
    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" v-model="notice.title" />
    <label for="content">내용</label>
    <textarea
      v-model="notice.content"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
    ></textarea>

    <button type="button" class="btn btn-primary" @click="move">목록</button>
    <button type="button" class="btn btn-warning" @click="addNotice">
      공지사항 등록
    </button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      notice: {
        num: '',
        title: '',
        writer: '',
        content: '',
        date: '',
      },
    };
  },
  methods: {
    move() {
      this.$router.push('/notice');
    },
    addNotice() {
      this.notice.writer = 'admin';
      axios
        .post('http://localhost:7777/happyhouse/notice', this.notice)
        .then(({ status }) => {
          if (status == 200) {
            alert('공지사항 등록에 성공하였습니다');
          } else {
            alert('공지사항 등록에 실패하였습니다');
          }
          this.$router.push('/notice');
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
