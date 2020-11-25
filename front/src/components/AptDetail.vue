<template>
  <div v-if="apt">
    <h1>{{ apt.아파트 }}</h1>
    <img
      width="500px"
      height="500px"
      src="https://images.unsplash.com/photo-1523192193543-6e7296d960e4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
      alt="행복 아파트"
    />
    <div style="margin: 10px"></div>
    <ul align="left">
      <li>일련번호 : {{ apt.일련번호 }}</li>
      <li>아파트 이름 :{{ apt.아파트 }}</li>
      <li>법정동 : {{ apt.법정동 }}</li>
      <li>층수 : {{ apt.층 }}</li>
      <li>거래금액 : {{ (apt.거래금액.replace(',', '') * 10000) | price }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'AptDetail',
  props: {
    // apt: Object,
    apt: [Object, String],
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
};
</script>

<style></style>
