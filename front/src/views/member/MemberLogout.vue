<template>
  <div>
    로그아웃 완료!!
  </div>
</template>

<script>
export default {
  created() {
    alert('로그아웃 완료!!');

    this.$router.push('/');
  },
};
</script>

<style></style>
