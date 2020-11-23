<template>
  <div>
    <h1>QnA 등록</h1>
    <br />
    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" v-model="qna.title" />
    <label for="writer">작성자</label>
    <input type="text" class="form-control" id="writer" v-model="qna.writer" />
    <label for="content">내용</label>
    <textarea
      v-model="qna.content"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
    ></textarea>

    <button type="button" class="btn btn-primary" @click="addQna">
      QnA 등록
    </button>
    <button type="button" class="btn btn-secondary" @click="move">
      QnA 목록
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      qna: {
        title: "",
        writer: "",
        content: ""
      }
    };
  },
  methods: {
    addQna() {
      axios
        .post("http://127.0.0.1:7777/happyhouse/qna/add", this.qna)
        .then(response => {
          alert("Qna가 등록되었습니다");
          console.log(response);
          this.$router.push("/qna");
        })
        .catch(error => {
          console.log(error);
        })
        .finally();
    },
    move() {
      this.$router.push("/qna");
    }
  }
};
</script>

<style></style>
