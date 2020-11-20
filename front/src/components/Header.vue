<template>
	<div>
		<b-navbar toggleable="lg" type="dark" variant="dark">
			<b-navbar-brand href="/">Happy House</b-navbar-brand>

			<b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

			<b-collapse id="nav-collapse" is-nav>
				<b-navbar-nav>
					<!-- <router-link to="/apt">아파트정보</router-link> -->
					<b-nav-item
						><router-link to="/apt">아파트정보</router-link></b-nav-item
					>
					<b-nav-item><router-link to="/about">About</router-link></b-nav-item>
					<b-nav-item><router-link to="/qna">Q&A</router-link></b-nav-item>
					<b-nav-item
						><router-link to="/notice">공지사항</router-link></b-nav-item
					>
					<b-nav-item
						><router-link to="/map">지도 검색</router-link></b-nav-item
					>
					<b-nav-item
						><router-link to="/map2">지도 검색2</router-link></b-nav-item
					>
				</b-navbar-nav>
			</b-collapse>

			<div v-if="isLogin">
				<p>{{ id }}님 안녕하세요</p>
				<b-btn @click="mypage">마이페이지</b-btn>
				<b-btn @click="logout">로그아웃</b-btn>
			</div>
			<div v-if="!isLogin">
				<b-btn @click="login">로그인</b-btn>
				<b-btn @click="join">회원가입</b-btn>
			</div>
		</b-navbar>
	</div>
</template>

<script>
export default {
	name: 'Header',
	data() {
		return {
			id: '',
			isLogin: false,
		};
	},
	created() {
		console.log('header');
		if (localStorage.getItem('id')) {
			this.id = localStorage.getItem('id');
			this.isLogin = true;
		}
	},
	updated() {
		console.log('header-update');
	},
	methods: {
		login() {
			this.$router.push('/member/login');
		},
		logout() {
			localStorage.removeItem('id');
			this.$router.push('/');
		},
		mypage() {
			this.$router.push('/member/detail/' + this.id);
		},
		join() {
			this.$router.push('/member/regist');
		},
	},
};
</script>
