<template>
  <div style="margin:0 auto; width: 70%">
    <h1>QnA 등록</h1>
    <br />
    <input
      type="text"
      class="form-control"
      id="title"
      v-model="qna.title"
      placeholder="제목을 입력하세요."
    />
    <br />

    <editor ref="toast" style="text-align: left" />

    <!-- <b-btn @click="crateAction">저장하기</b-btn> -->

    <button type="button" class="btn btn-primary" @click="addQna">
      QnA 등록
    </button>
    <button type="button" class="btn btn-secondary" @click="move">
      QnA 목록
    </button>
  </div>
</template>

<script>
import axios from 'axios';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';

export default {
  components: {
    Editor,
  },
  data() {
    return {
      qna: {
        title: '',
        writer: '',
        content: '',
      },
    };
  },
  methods: {
    addQna() {
      this.qna.writer = this.$store.getters.getUserId;
      this.qna.content = this.$refs.toast.invoke('getMarkdown');
      console.log(this.qna);
      axios
        .post('http://127.0.0.1:7777/happyhouse/qna/add', this.qna)
        .then((response) => {
          alert('Qna가 등록되었습니다');
          console.log(response);
          this.$router.push('/qna');
        })
        .catch((error) => {
          console.log(error);
        })
        .finally();
    },
    move() {
      this.$router.push('/qna');
    },
    createAction() {
      let content = this.$refs.toastuiEditor.invoke('getMarkdown');
      console.log(content);
    },
  },
};
</script>

<style></style>
