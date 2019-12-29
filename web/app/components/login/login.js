import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";
import {PopupComponent} from "../popup/popup.js";

export let LoginComponent = {
    data() {
        return {
            email: '',
            password: '',
            showWarning: false,
            errorMessage: '',
            isLoginPage: true
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
                if (resp.status === 401) {
                    this.showWarning = true;
                    this.errorMessage = 'Invalid email or password'
                    return;
                }
                this.$router.push({path: '/'});
            }, pathParams);
        },
        closeWarning() {
            this.showWarning = false;
        }
    },
    components: {
        'popup': PopupComponent
    }
};