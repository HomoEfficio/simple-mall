<template>
  <div class="well">
    <div class="row">
      <div class="text-center">
        <img class="logo" src="../assets/reco_logo.png">
        <h1 v-translate>가상 고객 사이트 {{ uid }}</h1>
      </div>
    </div>
    <div class="header">
      <h1>Vue.js Poster Store</h1>
      <form class="searchbar" v-on:submit.prevent="onSearch">
        <input v-model="newSearch" placeholder="Search for posters">
        <input type="submit" value="Search" class="btn">
      </form>
    </div>
  </div>
</template>

<script>
export default {
  locales: require('../i18n/Hello.js'),
  props: [
      'uid'
  ]
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="sass" scoped>
  h1
    color: #42b983
  /*.logo*/
    /*width: 80px*/
    /*height: 80px*/
</style>
