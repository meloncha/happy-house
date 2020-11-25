<template>
  <div>
    <h1>QnA 답변</h1>
    <br />
    <label for="id">글번호</label>
    <input type="text" class="form-control" id="id" v-model="qna.id" disabled />
    <label for="title">제목</label>
    <input
      type="text"
      class="form-control"
      id="title"
      v-model="qna.title"
      disabled
    />
    <label for="content">내용</label>
    <textarea
      v-model="qna.content"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
      disabled
    ></textarea>

    <label for="comment">답변</label>
    <textarea
      v-model="qna.comment"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
    ></textarea>

    <button type="button" class="btn btn-primary" @click="addComment">
      답변 등록
    </button>
    <button type="button" class="btn btn-secondary" @click="move">
      목록 돌아가기
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      qna: {
        id: "",
        title: "",
        writer: "",
        content: "",
        date: "",
        comment: ""
      }
    };
  },
  created() {
    this.qna.id = this.$route.params.id;

    axios
      .get("http://127.0.0.1:7777/happyhouse/qna/" + this.qna.id)
      .then(res => {
        this.qna = res.data;
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {
    addComment() {
      axios
        .put("http://127.0.0.1:7777/happyhouse/qna/modify", this.qna)
        .then(response => {
          const msg = response.data;
          console.log(msg);
          if (msg == "success") {
            alert("답변이 등록되었습니다");
          } else {
            alert("답변 등록에 실패하였습니다.");
          }
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
