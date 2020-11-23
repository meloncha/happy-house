<template>
  <div>
    <h1>QnA 등록</h1>
    <br />
    <label for="id">글번호</label>
    <input
      type="text"
      class="form-control"
      id="id"
      v-model="notice.num"
      disabled
    />
    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" v-model="notice.title" />
    <label for="writer">작성자</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      v-model="notice.writer"
    />
    <label for="date">작성일</label>
    <input
      type="text"
      class="form-control"
      id="date"
      v-model="notice.date"
      disabled
    />
    <label for="content">내용</label>
    <textarea
      v-model="notice.content"
      class="form-control"
      id="content"
      cols="30"
      rows="10"
    ></textarea>

    <button type="button" class="btn btn-primary" @click="move">목록</button>
    <button type="button" class="btn btn-warning" @click="updateNotice">
      공지사항 등록
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      notice: {
        num: "",
        title: "",
        writer: "",
        content: "",
        date: ""
      }
    };
  },
  created() {
    this.notice.num = this.$route.params.num;

    axios
      .get("http://127.0.0.1:7777/happyhouse/notice/" + this.notice.num)
      .then(res => {
        this.notice = res.data;
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {
    move() {
      this.$router.push("/notice");
    },
    updateNotice() {
      axios
        .put(
          `http://localhost:7777/happyhouse/notice/${this.notice.num}`,
          this.notice
        )
        .then(response => {
          const status = response.status;
          if (status == 200) {
            alert("수정이 완료되었습니다.");
          } else {
            alert("수정에 실패하였습니다.");
          }
          this.$router.push("/notice");
        });
    }
  }
};
</script>

<style></style>
