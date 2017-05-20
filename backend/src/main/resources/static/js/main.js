/**
 * Created by 1003604 on 2017. 5. 18..
 */
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
            if (this.newSearch.length) {
                this.items = [];
                this.loading = true;
                axios.get('/api/search/'.concat(this.newSearch))
                    .then(res => {
console.log(res);
                        this.lastSearch = this.newSearch;
                        this.results = res.data;
                        this.appendItems();
                        this.loading = false;
                    })
                    .catch(err => {
                        console.log(err);
                    });
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
        },
        addToCart: function(index) {
            var item = this.items[index];
            this.total += item.productPrice;
            var found = false;
            for (var i = 0, len = this.cart.length; i < len; i++) {
                if (this.cart[i].id === item.productCode) {
                    this.cart[i].count++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                this.cart.push({
                    id: item.productCode,
                    name: item.productName,
                    price: item.productPrice,
                    image: item.productImage,
                    image100: item.productImage,
                    image110: item.productImage,
                    image120: item.productImage,
                    image130: item.productImage,
                    image140: item.productImage,
                    image150: item.productImage,
                    image170: item.productImage,
                    image200: item.productImage,
                    image250: item.productImage,
                    image270: item.productImage,
                    image300: item.productImage,
                    text1: item.text1,
                    text2: item.text2,
                    sellerNick: item.sellerNick,
                    seller: item.seller,
                    sellerGrd: item.sellerGrd,
                    rating: item.rating,
                    detailPageUrl: item.detailPageUrl,
                    salePrice: item.salePrice,
                    delivery: item.delivery,
                    reviewCount: item.reviewCount,
                    buySatisfy: item.buySatisfy,
                    minorYn: item.minorYn,
                    benefit: item.benefit,
                    count: 1
                });
            }
            this.sendLog('basket', {
                service_id: RECO_SERVICE_ID,
                uid: RECO_UID,
                ref: RECO_REF,
                url: RECO_URL,
                items: this.cart,
                user: {
                    mid: RECO_MID,
                    gender: RECO_GENDER,
                    birthyear: RECO_BIRTHYEAR
                }
            });
        },
        inc: function(i) {
            var current = this.cart[i];
            current.count++;
            this.total += current.price;
            this.sendLog('basket', { msg: 'inc' });
        },
        dec: function(i) {
            var current = this.cart[i];
            current.count--;
            this.total -= current.price;
            if (current.count <= 0) {
                this.cart.splice(i, 1);
            }
            this.sendLog('basket', { msg: 'dec' });
        },
        onOrder: function(cart) {
            this.convertToOrderItems(cart);
            this.sendLog('order', {
                service_id: RECO_SERVICE_ID,
                uid: RECO_UID,
                ref: RECO_REF,
                url: RECO_URL,
                items: this.cart,
                user: {
                    mid: RECO_MID,
                    gender: RECO_GENDER,
                    birthyear: RECO_BIRTHYEAR
                }
            });
        },
        sendLog: function(action, payload) {
            console.log(action, payload);
        },
        convertToOrderItems: function(cartItems) {
            for (var i = 0, len = cartItems.length; i < len; i++) {
                var item = cartItems[i];
                item.total_sales = item.price * item.count;
                // item.order_no = Math.floor(Math.random()*(10000000000-1000000000)+1000000000).toString();
            }
        }
    },
    computed: {
        noMoreItems: function() {
            return this.items.length == this.results.length && this.results.length > 0
        }
    },
    filters: {
        currency: function(price) {
            return price + ' 원';
        }
    },
});