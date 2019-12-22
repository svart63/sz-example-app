import {viewLoader} from "../../../js/ViewLoader.js";

export let LoginComponent = {
    data() {
        return {
            email: '',
            password: ''
        }
    },
    template: viewLoader.load('login'),
    methods: {
        registration() {
            fetch('/registration', {
                method: 'POST',
                body: JSON.stringify({
                    email: this.email,
                    password: this.password
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(resp => {
                if (resp.status !== 201) {
                    resp.text().then(error => {
                        alert('Error: ' + error);
                    })
                } else {
                    alert("Success");
                }
            })
        },
        buildUri(params) {
            let uri = '';
            Object.keys(params).forEach(k => {
                if (uri) {
                    uri += '&'
                }
                uri += encodeURI(k + '=' + params[k]);
            });
            return uri;
        },
        login() {
            let params = {
                email: this.email,
                password: this.password
            };


            let url = '/login?' + this.buildUri(params);
            fetch(url, {
                method: 'POST',
                credentials: 'include',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify(params)
            }).then(resp => {
                if (resp.status === 200) {
                    resp.text().then(id => {
                        this.$router.push({path: '/id/' + id});
                    });
                }
            })
        }
    }
};