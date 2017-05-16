<template>
  <div id="app" class="container text-center">
    <br />
    <navbar></navbar>
    <br />
    <div v-if="hasUID()">
      <router-view></router-view>
    </div>
    <div v-else>
      <h2>Add '?uid=UID_VALUE' at the end of the URL</h2>
    </div>
    <br /><br />
  </div>
</template>

<script>
import Navbar from './components/Navbar'

export default {
  components: { Navbar },
  methods: {
    hasUID() {
        return this.uid;
    }
  },
  data() {
      return {
          uid: (function() {
            var url = document.location.href;
            var paramString = url.substring(url.indexOf('?') + 1);
            var params = paramString.split('&');
            for (var i = 0, len = params.length; i < len; i++) {
              var param = params[i];
              if (param.trim().indexOf('uid') === 0)
                return param.substring(param.indexOf('=') + 1);
            }
            return '';
          }())
      }
  }
}
</script>

<style lang="sass">

  // Bootstrap
  $icon-font-path: '../node_modules/bootstrap-sass/assets/fonts/bootstrap/';
  $link-color: #42b983;
  $brand-success: #42b983;
  @import 'node_modules/bootstrap-sass/assets/stylesheets/bootstrap';

  // font awesome
  $fa-font-path: '../node_modules/font-awesome/fonts/';
  @import 'node_modules/font-awesome/scss/font-awesome';

  //some styling adaptations
  h1
    color: #42b983
  p
    text-align: justify
</style>
