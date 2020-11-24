import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import vuetify from './plugins/vuetify';
import VuetifyDialog from 'vuetify-dialog';

window.Kakao.init('0f4f76ba8fccad976c9cb5ae31ac0a15');

Vue.config.productionTip = false;

Vue.use(VuetifyDialog, {
  context: {
    vuetify,
  },
});

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount('#app');
