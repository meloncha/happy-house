<template>
  <div class="about">
    <h1>Happy House</h1>
    <p>해피 하우스 입니다!!</p>

  </div>
</template>
<script>
export default {
  name: 'About',
  data() {
    return {
      is_show: false,
      modalShow: true,
    };
  },
  methods: {
    showModal() {
      this.$refs['my-modal'].show();
    },
    hideModal() {
      this.$refs['my-modal'].hide();
    },
    toggleModal() {
      // We pass the ID of the button that we want to return focus to
      // when the modal has hidden
      this.$refs['my-modal'].toggle('#toggle-btn');
    },
  },
};
</script>
