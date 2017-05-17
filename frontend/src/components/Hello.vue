<template>
  <div class="well">
    <div class="row">
      <div class="text-center">
        <img class="logo" src="../assets/reco_logo.png">
        <h1 v-translate>가상 고객 사이트</h1>
      </div>
    </div>
    <div class="header">
      <h1>Vue.js Poster Store</h1>
      <form class="searchbar" v-on:submit.prevent="onSearch">
        <input v-model="newSearch" placeholder="Search for posters">
        <input type="submit" value="Search" class="btn">
      </form>
    </div>
    <div class="main">
      <div class="products">
        <div v-if="loading">
          Loading...
        </div>
        <div class="search-results" v-else>
          Found {{ results.length }} results for search term {{ lastSearch }}.
        </div>
        <div class="product" v-for="(item, index) in items">
          <div>
            <div class="product-image">
              <img v-bind:src="item.link">
            </div>
          </div>
          <div>
            <h4 class="product-title">
              <span v-on:click="onDetail(index)">{{ item.title }}</span>
            </h4>
            <p>Price: <strong>{{ item.score | currency }}</strong></p>
            <button class="btn add-to-cart" v-on:click="addToCart(index)">Add to cart</button>
          </div>
        </div>
        <div id="product-list-bottom">
          <div v-if="noMoreItems">No more items</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    onSearch: function() {
      if (this.newSearch.length) {
        this.items = [];
        this.loading = true;
        this.$http.get('/search/'.concat(this.newSearch))
          .then(
            function(res) {
              console.log(res);
              this.lastSearch = this.newSearch;
              this.results = res.data;
              this.appendItems();
              this.loading = false;
            },
            function() {}
          );
        this.sendLog('search', {
          service_id: RECO_SERVICE_ID,
          uid: RECO_UID,
          ref: RECO_REF,
          url: RECO_URL,
          q: this.newSearch,
          user: {
            mid: RECO_MID,
            gender: RECO_GENDER,
            birthyear: RECO_BIRTHYEAR
          }
        });
      }
    }
  },
  data() {
      return {
        items: [],
        results: [],
        newSearch: 'anime',
        lastSearch: '',
        loading: false
      }
  },
  props: [
    'recoUid'
  ],
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
