<template>
  <div>
    <apt-list-item
      v-for="(apt, index) in aptList"
      :key="index"
      :apt="apt"
      @select-apt="selectApt"
    />
  </div>
</template>

<script>
import AptListItem from "@/components/AptListItem.vue";

export default {
  name: "AptList",
  components: {
    AptListItem
  },
  props: {
    aptList: Array
  },
  methods: {
    selectApt(apt) {
      this.$emit("select-apt", apt);
    }
  }
};
</script>

<style></style>
