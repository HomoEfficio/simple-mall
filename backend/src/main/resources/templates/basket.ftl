<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>Simple Mall</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="app">
    <div class="header">
        <h1>Vue.js Poster Store</h1>
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
                        <img v-bind:src="item.productImage">
                    </div>
                </div>
                <div>
                    <h4 class="product-title">
                        <span v-on:click="onDetail(index)">{{ item.productName }}</span>
                    </h4>
                    <p>Price: <strong>{{ item.productPrice }}</strong></p>
                    <button class="btn add-to-cart" v-on:click="addToCart(index)">Add to cart</button>
                </div>
            </div>
            <div id="product-list-bottom">
                <div v-if="noMoreItems">No items</div>
            </div>
        </div>
    </div>
</div>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="/js/scrollMonitor.js"></script>
<script src="/js/basket.js"></script>
</body>
</html>