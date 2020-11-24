<template>
  <div>
    <h1>QnA 상세 페이지</h1>
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
    <label for="writer">작성자</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      v-model="qna.writer"
      disabled
    />
    <label for="date">작성일</label>
    <input
      type="text"
      class="form-control"
      id="date"
      v-model="qna.date"
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
      disabled
    ></textarea>

    <button type="button" class="btn btn-primary" @click="move">목록</button>
    <button type="button" class="btn btn-warning" @click="updateQna">
      질문 수정
    </button>
    <button type="button" class="btn btn-danger" @click="deleteQna">
      질문 삭제
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
    move() {
      this.$router.push('/qna');
    },
    updateQna() {
      this.$router.push("/qna/update/" + this.qna.id);
    },
    deleteQna() {
      axios
        .delete("http://127.0.0.1:7777/happyhouse/qna/delete/" + this.qna.id)
        .then(res => {
          const msg = res.data;
          if (msg == "true") {
            alert("삭제 성공했습니다.");
          } else {
            alert("삭제 실패했습니다.");
          }
          this.$router.push("/qna");
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style></style>
