<template>
  <div>
    <v-container>
      <v-card-title>
        <h1>회원 목록 ( admin 페이지 )</h1>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="members"
        :items-per-page="5"
        :search="search"
        :footer-props="{
          showFirstLastPage: true,
          firstIcon: 'mdi-arrow-collapse-left',
          lastIcon: 'mdi-arrow-collapse-right',
          prevIcon: 'mdi-minus',
          nextIcon: 'mdi-plus',
        }"
      >
        <template v-slot:body="{ items }">
          <tbody>
            <tr v-for="(item, index) in items" :key="index">
              <td>{{ index + 1 }}</td>
              <td v-html="item.id"></td>
              <td v-html="item.name"></td>
              <td v-html="item.email"></td>
              <td v-html="item.phone"></td>
              <td v-html="item.pwd"></td>
              <td>
                <v-chip
                  class="ma-2"
                  style="backgroundColor: rgb(202, 255, 191); color: gray"
                  @click="updateMember(item.id)"
                >
                  수정
                </v-chip>
                <v-chip
                  class="ma-2"
                  style="backgroundColor: rgb(255, 173, 173); color: gray"
                  @click="deleteMember(item)"
                >
                  삭제
                </v-chip>
              </td>
            </tr>
          </tbody>
        </template>
      </v-data-table>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      members: [],
      keyword: '',
      search: '',
      headers: [
        { text: 'index', value: 'num' },
        { text: 'id', value: 'title' },
        { text: 'name', value: 'writer' },
        { text: 'email', value: 'date' },
        { text: 'phone', value: 'readcnt' },
        { text: 'password', value: 'readcnt' },
        { text: 'manage', value: '' },
      ],
    };
  },
  methods: {
    move() {
      this.$router.push('/');
    },
    updateMember(id) {
      this.$router.push(`/member/update/${id}`);
    },
    deleteMember(item) {
      const isDelete = confirm(`${item.name}님을 회원 삭제하시겠습니까?`);
      if (isDelete) {
        axios
          .post(`http://localhost:7777/happyhouse/member/delete/${item.id}`)
          .then(({ data }) => {
            if (data == 'success') {
              alert('삭제 성공');

              axios
                .get('http://localhost:7777/happyhouse/member/list')
                .then((response) => {
                  console.log(response.data);
                  this.members = response.data;
                })
                .catch((err) => {
                  console.log(err);
                });
            } else {
              alert('삭제 실패');
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
  created() {
    axios
      .get('http://localhost:7777/happyhouse/member/list')
      .then((response) => {
        console.log(response.data);
        this.members = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style></style>
