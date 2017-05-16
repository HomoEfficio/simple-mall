<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>Simple Mall</title>
</head>
<body>
<div id="app">
    <h1>Vue</h1>
    <div v-for="item in list">
        <h3>{{ item }}</h3>
    </div>
    <hr/>
    <button @click="showData">Show Data</button>
</div>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
new Vue({
    el: '#app',
    data: {
        list: [
            'a', '1', 'c'
        ]
    },
    methods: {
        showData() {
            axios.get("/data")
                    .then(res => console.log(res))
                    .catch(err => console.log(err));
        }
    }
});
</script>
</body>
</html>