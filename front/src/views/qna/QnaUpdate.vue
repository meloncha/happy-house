<template>
  <div>
    <h1>QnA 등록</h1>
    <br />
    <label for="id">글번호</label>
    <input type="text" class="form-control" id="id" v-model="qna.id" disabled />
    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" v-model="qna.title" />
    <label for="writer">작성자</label>
    <input type="text" class="form-control" id="writer" v-model="qna.writer" />
    <label for="date">작성일</label>
    <input type="text" class="form-control" id="date" v-model="qna.date" />
    <label for="content">내용</label>
    <textarea
      v-model="qna.content"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
    ></textarea>

    <label for="comment">답변</label>
    <textarea
      v-model="qna.comment"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
    ></textarea>

    <button type="button" class="btn btn-primary" @click="updateQna">
      질문 수정
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
    updateQna() {
      axios
        .put("http://127.0.0.1:7777/happyhouse/qna/modify", this.qna)
        .then(response => {
          const msg = response.data;
          if (msg == "success") {
            alert("질문이 수정되었습니다");
          } else {
            alert("수정에 실패하였습니다.");
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
