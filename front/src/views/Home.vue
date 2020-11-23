<template>
  <body>
    <div class="jb-box">
      <video muted autoplay loop>
        <source src="@/assets/videoplayback3.mp4" type="video/mp4" />
        <strong>Your browser does not support the video tag.</strong>
      </video>
      <div class="jb-text">
        <pre>
        <br><br>
        어떤 동네, 어떤 방에서
        살고 싶으신가요?
        HAPPYHOUSE에서 찾아보세요!
        </pre>
      </div>
    </div>
  </body>
</template>

<script>
// @ is an alias to /src
// import Main from '@/components/Main.vue';

export default {
  name: "Home",
  components: {
    // Main,
  }
};
</script>

<style>
#logo {
  width: 1000px;
  height: 500px;
  margin: 0px auto;
}

body {
  padding: 0px;
  margin: 0px;
}
.jb-box {
  width: 100%;
  height: fit-content;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
video {
  width: fit-content;
}
.jb-text {
  position: absolute;
  top: 50%;
  width: 100%;
}
.jb-text pre {
  margin-top: -691px;
  text-align: left;
  font-size: 48px;
  color: #ffffff;
  font-family: KOTRA_BOLD-Bold;
}

@font-face {
  font-family: "KOTRA_BOLD-Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.1/KOTRA_BOLD-Bold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>
