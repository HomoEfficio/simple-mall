<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>Simple Mall</title>
    <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.2/build/pure-min.css" integrity="sha384-UQiGfs9ICog+LwheBSRCt1o5cbyKIHbwjWscjemyBMT9YCUMZffs6UqUTd0hObXD" crossorigin="anonymous">
</head>
<body>
<div id="app">
    <div class="header">
        <h1>Vue.js Poster Store</h1>
    </div>
    <div class="main">
        <div class="sign">
            <form id="sign-form" class="sign">
                <input v-model="userName" placeholder="UserName을 입력하세요.">
                <button class="btn" v-on:submit.prevent="onSignIn('/sign-in')">Sign In</button>
                <button class="btn" v-on:submit.prevent="onSignUp('/api/members')">Sign Up</button>
            </form>
        </div>
    </div>
</div>

<#--<script src="https://unpkg.com/vue"></script>-->
<#--<script src="https://unpkg.com/axios/dist/axios.min.js"></script>-->
<script src="/js/vue.min.js"></script>
<script src="/js/axios.min.js"></script>
<script src="/js/sign.js"></script>
</body>
</html>
