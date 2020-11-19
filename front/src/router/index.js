import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue';
import About from '@/views/About.vue';
import Apt from '@/views/Apt.vue';
import Map from '@/views/Map.vue';

import Qna from '@/views/Qna.vue';
import QnaRegist from '@/views/QnaRegist';
import QnaUpdate from '@/views/QnaUpdate';
import QnaDetail from '@/views/QnaDetail';

import Notice from '@/views/Notice';
import NoticeDetail from '@/views/NoticeDetail';
import NoticeRegist from '@/views/NoticeRegist';
import NoticeUpdate from '@/views/NoticeUpdate';

import Member from '@/views/Member';
import MemberLogin from '@/views/MemberLogin';
import MemberRegist from '@/views/MemberRegist';
import MemberDetail from '@/views/MemberDetail';
import MemberUpdate from '@/views/MemberUpdate';

import Comment from '@/views/Comment';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/apt',
    name: 'Apt',
    component: Apt,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/qna',
    name: 'Qna',
    component: Qna,
  },
  {
    path: '/qna/detail/:id',
    name: 'QnaDetail',
    component: QnaDetail,
  },
  {
    path: '/qna/update/:id',
    name: 'QnaUpdate',
    component: QnaUpdate,
  },
  {
    path: '/qna/regist',
    name: 'QnaRegist',
    component: QnaRegist,
  },
  {
    path: '/notice',
    name: 'Notice',
    component: Notice,
  },
  {
    path: '/notice/detail/:id',
    name: 'NoticeDetail',
    component: NoticeDetail,
  },
  {
    path: '/notice/update/:id',
    name: 'NoticeUpdate',
    component: NoticeUpdate,
  },
  {
    path: '/notice/regist',
    name: 'NoticeRegist',
    component: NoticeRegist,
  },
  {
    path: '/member',
    name: 'Member',
    component: Member,
  },
  {
    path: '/member/login',
    name: 'MemberLogin',
    component: MemberLogin,
  },
  {
    path: '/member/detail/:id',
    name: 'MemberDetail',
    component: MemberDetail,
  },
  {
    path: '/member/update/:id',
    name: 'MemberUpdate',
    component: MemberUpdate,
  },
  {
    path: '/member/regist',
    name: 'MemberRegist',
    component: MemberRegist,
  },
  {
    path: '/qna/comment/:id',
    name: 'Comment',
    component: Comment,
  },
  {
    path: '/map',
    name: 'Map',
    component: Map,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
