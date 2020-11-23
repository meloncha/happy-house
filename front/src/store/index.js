import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const SERVER_URL = 'http://localhost:7777/happyhouse';

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    accessToken: null,
    id: '',
    name: '',
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.id;
    },
    getUserName(state) {
      return state.name;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['auth-token'];
      state.id = payload['id'];
      state.name = payload['name'];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.id = '';
      state.name = '';
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios.post(`${SERVER_URL}/member/login`, user).then((response) => {
        context.commit('LOGIN', response.data);
        axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;

        localStorage.setItem(
          'img',
          'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl5CNtDH_vn1qWx1gJwCPGzeWbCDf-g4L6BQ&usqp=CAU'
        );
      });
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['auth-token'] = undefined;

      localStorage.setItem(
        'img',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl5CNtDH_vn1qWx1gJwCPGzeWbCDf-g4L6BQ&usqp=CAU'
      );

      window.Kakao.API.request({
        url: '/v1/user/unlink',
        success: function(response) {
          console.log(response);
        },
        fail: function(error) {
          console.log(error);
        },
      });

    },
    KAKAO_LOGIN(context, user) {
      return axios.post(`${SERVER_URL}/member/kakao`, user).then((response) => {
        context.commit('LOGIN', response.data);
        axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
      });
    },
  },
  modules: {},
});
