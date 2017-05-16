<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>Simple Mall</title>
    <link rel="stylesheet" type="text/css" href="../static/style.css">
</head>
<body>
<div id="app">
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
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
const LOAD_NUM = 10;
const RECO_SERVICE_ID = '543';
const RECO_REF = 'http://dev.recopick.com/index.html';
const RECO_URL = 'http://dev.recopick.com/index.html';
const RECO_UID = (function() {
    let url = document.location.href;
    let paramString = url.substring(url.indexOf('?') + 1);
    let params = paramString.split('&');
    for (let i = 0, len = params.length; i < len; i++) {
        let param = params[i];
        if (param.trim().indexOf('uid') === 0)
            return param.substring(param.indexOf('=') + 1);
    }
    return '';
}());
const RECO_MID = Math.floor(Math.random()*(1000000000-100000000)+100000000);
const RECO_BIRTHYEAR = Math.floor(Math.random()*(2000-1930)+1930);
const RECO_GENDER = Math.floor(Math.random()*(1-100)+100) % 2 ? 'F' : 'M';

new Vue({
    el: '#app',
    data: {
        total: 0,
        items: [],
        cart: [],
        results: [],
        newSearch: 'anime',
        lastSearch: '',
        loading: false
    },
    methods: {
        appendItems() {
            if (this.items.length < this.results.length) {
                var append = this.results.slice(this.items.length, this.items.length + LOAD_NUM);
                this.items = this.items.concat(append);
            }
        },
        onSearch() {
            console.log('Search NOW!!!');
//            if (this.newSearch.length) {
//                this.items = [];
//                this.loading = true;
//                this.$http.get('/search/'.concat(this.newSearch))
//                        .then(
//                                function(res) {
//                                    console.log(res);
//                                    this.lastSearch = this.newSearch;
//                                    this.results = res.data;
//                                    this.appendItems();
//                                    this.loading = false;
//                                },
//                                function() {}
//                        );
//                this.sendLog('search', {
//                    service_id: RECO_SERVICE_ID,
//                    uid: RECO_UID,
//                    ref: RECO_REF,
//                    url: RECO_URL,
//                    q: this.newSearch,
//                    user: {
//                        mid: RECO_MID,
//                        gender: RECO_GENDER,
//                        birthyear: RECO_BIRTHYEAR
//                    }
//                });
//            }
        },
    },
    computed: {
        noMoreItems: function() {
            return this.items.length == this.results.length && this.results.length > 0
        }
    },
});
</script>
</body>
</html>