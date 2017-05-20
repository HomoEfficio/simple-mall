new Vue({
    el: '#app',
    data: {
        userName: '',
        // form: document.getElementById('sign-form'),
        form: '',
    },
    methods: {
        onSignIn(endPoint) {
console.log('onSignIn');
            // GET /sign-in/{memberName}
            this.form.method = 'GET';
            this.form.action = endPoint + '/' + this.userName;
            this.form.submit();

            // axios.get(endPoint + '/' + this.userName)
            //     .then(res => {
            //         console.log(res);
            //         document.location.href = './main.html'
            //     })
            //     .catch(err => {
            //         console.log(err);
            //     });
        },
        onSignUp(endPoint) {
console.log('onSignUp');
            // POST /members/{memberName}
            axios.post(endPoint + '/' + this.userName)
                .then(res => {
                    console.log(res);
                    this.onSignIn('/sign-in');
                })
                .catch(err => {
                    console.log(err);
                });
        },
    }
});