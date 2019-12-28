import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";

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
            this.$router.push({path: '/registration'});
        },
        login() {
            let pathParams = {
                email: this.email,
                password: this.password
            };

            rq.post('/login', (resp) => {
                if (resp.status === 200) {
                    this.$router.push({path: '/id/'});
                }
            }, pathParams);
        }
    }
};