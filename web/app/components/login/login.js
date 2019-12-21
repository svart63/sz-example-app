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
                body: {
                    email: this.email,
                    password: this.password
                }
            }).then(resp => {
                if (resp.status !== 200) {
                    resp.text().then(error => {
                        alert('Error: ' + error);
                    })
                } else {
                    alert("Success");
                }
            })
        }
    }
};