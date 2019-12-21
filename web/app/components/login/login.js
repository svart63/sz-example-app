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
        login() {
            fetch('/login', {
                method: 'POST',
                body: JSON.stringify({
                    email: this.email,
                    password: this.password
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(resp => {
                if (resp.status === 200) {
                    resp.text().then(token=> {
                        localStorage.setItem('auth', token);
                    });
                }
            })
        }
    }
};