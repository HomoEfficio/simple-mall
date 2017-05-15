<template>
  <div class="well">
    <div class="row">
      <div class="col-sm-5 text-right">
        <img class="logo" src="../assets/reco_logo.png">
      </div>
      <div class="col-sm-7 text-left">
        <h1 v-translate>가상 고객 사이트</h1>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    locales: require('../i18n/Hello.js')
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
