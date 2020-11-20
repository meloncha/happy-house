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
		<input
			type="text"
			class="form-control"
			id="title"
			v-model="notice.title"
			disabled
		/>
		<label for="writer">작성자</label>
		<input
			type="text"
			class="form-control"
			id="writer"
			v-model="notice.writer"
			disabled
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
			disabled
		></textarea>

		<button type="button" class="btn btn-primary" @click="move">목록</button>
		<button type="button" class="btn btn-warning" @click="updateNotice">
			공지사항 수정
		</button>
		<button type="button" class="btn btn-danger" @click="deleteNotice">
			공지사항 삭제
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
	created() {
		this.notice.num = this.$route.params.num;

		axios
			.get('http://127.0.0.1:7777/happyhouse/notice/' + this.notice.num)
			.then((res) => {
				this.notice = res.data;
			})
			.catch((err) => {
				console.log(err);
			});
	},
	methods: {
		move() {
			this.$router.push('/notice');
		},
		updateNotice() {
			this.$router.push('/qna/update/' + this.notice.num);
		},
		deleteNotice() {
			axios
				.delete('http://127.0.0.1:7777/happyhouse/notice/' + this.notice.num)
				.then((res) => {
					const status = res.status;
					if (status == 200) {
						alert('삭제 성공했습니다.');
					} else {
						alert('삭제 실패했습니다.');
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
