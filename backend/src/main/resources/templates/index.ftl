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
            <form class="sign">
                <input v-model="memberId" placeholder="MemberId를 입력하세요.">
                <button class="btn" v-on:submit.prevent="onSignIn">Sign In</button>
                <button class="btn" v-on:submit.prevent="onSignUp">Sign Up</button>
            </form>
        </div>
    </div>
</div>
<script src="/js/sign.js"></script>
</body>
</html>
