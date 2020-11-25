<template>
  <div>
    <h1>QnA 상세 페이지</h1>
    <br />
    <label for="id">글번호</label>
    <input type="text" class="form-control" id="id" v-model="qna.id" disabled />
    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" v-model="qna.title" disabled />
    <label for="writer">작성자</label>
    <input type="text" class="form-control" id="writer" v-model="qna.writer" disabled />
    <label for="date">작성일</label>
    <input type="text" class="form-control" id="date" v-model="qna.date" disabled />
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

    <div>
      <h1>게시판 상세보기</h1>

      <div class="AddWrap">
        <form>
          <table class="tbAdd">
            <colgroup>
              <col width="15%" />
              <col width="*" />
            </colgroup>
            <tr>
              <th>제목</th>
              <td>{{ subject }}</td>
            </tr>
            <tr>
              <th>내용</th>
              <td class="txt_cont" v-html="cont"></td>
            </tr>
          </table>
        </form>
      </div>

      <div class="btnWrap">
        <a href="javascript:;" @click="fnList" class="btn">목록</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
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
          const msg = res.data;
          if (msg == 'true') {
            alert('삭제 성공했습니다.');
          } else {
            alert('삭제 실패했습니다.');
          }
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
.tbAdd {
  border-top: 1px solid #888;
}
.tbAdd th,
.tbAdd td {
  border-bottom: 1px solid #eee;
  padding: 5px 0;
}
.tbAdd td {
  padding: 10px 10px;
  box-sizing: border-box;
  text-align: left;
}
.tbAdd td.txt_cont {
  height: 300px;
  vertical-align: top;
}
.btnWrap {
  text-align: center;
  margin: 20px 0 0 0;
}
.btnWrap a {
  margin: 0 10px;
}
.btnAdd {
  background: #43b984;
}
.btnDelete {
  background: #f00;
}
</style>
