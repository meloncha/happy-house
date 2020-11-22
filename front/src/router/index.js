import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue';
import About from '@/views/About.vue';
import Apt from '@/views/Apt.vue';

import Qna from '@/views/qna/Qna.vue';
import QnaRegist from '@/views/qna/QnaRegist';
import QnaUpdate from '@/views/qna/QnaUpdate';
import QnaDetail from '@/views/qna/QnaDetail';
import Comment from '@/views/qna/Comment';

import Notice from '@/views/notice/Notice';
import NoticeDetail from '@/views/notice/NoticeDetail';
import NoticeRegist from '@/views/notice/NoticeRegist';
import NoticeUpdate from '@/views/notice/NoticeUpdate';

import Member from '@/views/member/Member';
import MemberLogin from '@/views/member/MemberLogin';
import MemberRegist from '@/views/member/MemberRegist';
import MemberDetail from '@/views/member/MemberDetail';
import MemberUpdate from '@/views/member/MemberUpdate';

import Map from '@/views/Map.vue';
import Map2 from '@/views/Map2';


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
    path: '/member/detail',
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
  {
    path: '/map2',
    name: 'Map2',
    component: Map2,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
