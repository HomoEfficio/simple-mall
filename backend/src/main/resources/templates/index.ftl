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
                <div v-if="noMoreItems">No more items</div>
            </div>
        </div>
        <div class="cart">
            <h2>Shopping Cart</h2>
            <transition-group name="fade" tag="ul">
                <li class="cart-item" v-for="(item, index) in cart" v-bind:key="index">
                    <div class="item-title">{{ item.name }}</div>
                    <span class="item-qty">{{ item.price }} * {{ item.count }}</span>
                    <button class="btn" v-on:click="inc(index)">+</button>
                    <button class="btn" v-on:click="dec(index)">-</button>
                </li>
            </transition-group>
            <transition name="fade">
                <div v-if="cart.length">
                    <p>Total: {{ total | currency }}</p>
                    <div><button class="btn order-now" v-on:click="onOrder(cart)">Order Now</button></div>
                </div>
            </transition>
            <div v-if="cart.length === 0" class="empty-cart">
                No items in the cart
            </div>
        </div>
    </div>
</div>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="/js/scrollMonitor.js"></script>
<script src="/js/home.js"></script>
</body>
</html>