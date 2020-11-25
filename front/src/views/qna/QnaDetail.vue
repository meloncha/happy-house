<template>
  <div style="margin:0 auto; width: 70%; text-align: left">
    <h1 style="text-align: center">QnA 상세 페이지</h1>
    <br />
    <label for="id">글번호</label>
    <input type="text" class="form-control" id="id" v-model="qna.id" disabled />

    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" v-model="qna.title" disabled />
    <label for="writer">작성자</label>
    <input type="text" class="form-control" id="writer" v-model="qna.writer" disabled />
    <label for="date">작성일</label>
    <input type="text" class="form-control" id="date" v-model="qna.date" disabled />

    <label for="comment">내용</label>
    <div height="500px" style="backgroundColor: #f7ede2; border: 1px solid">
      <viewer v-if="qna.content" :initialValue="qna.content" />
    </div>

    <label for="comment">답변</label>
    <textarea
      v-model="qna.comment"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
      disabled
    ></textarea>

    <button type="button" class="btn btn-primary" @click="move">목록</button>
    <template v-if="qna.writer == this.$store.getters.getUserId">
      |
      <button type="button" class="btn btn-warning" @click="updateQna">
        질문 수정
      </button>
      |
      <button type="button" class="btn btn-danger" @click="deleteQna">
        질문 삭제
      </button>
    </template>
  </div>
</template>

<script>
import axios from 'axios';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    Viewer,
  },
  data() {
    return {
      qna: {
        id: '',
        title: '',
        writer: '',
        content: '',
        date: '',
        comment: '',
      },
    };
  },
  created() {
    this.qna.id = this.$route.params.id;

    axios
      .get('http://127.0.0.1:7777/happyhouse/qna/' + this.qna.id)
      .then((res) => {
        this.qna = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    move() {
      this.$router.push('/qna');
    },
    updateQna() {
      this.$router.push('/qna/update/' + this.qna.id);
    },
    deleteQna() {
      axios
        .delete('http://127.0.0.1:7777/happyhouse/qna/delete/' + this.qna.id)
        .then((res) => {
          console.log(res);
          alert('삭제 성공했습니다.');
          this.$router.push('/qna');
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
button {
  margin: 20px;
}
</style>
