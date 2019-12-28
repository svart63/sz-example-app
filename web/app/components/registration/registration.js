import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";
import {PopupComponent} from "../popup/popup.js";

export let RegistrationComponent = {
    data() {
        return {
            profile: {
                email: '',
                password: '',
                firstName: '',
                lastName: '',
                birthDay: 0
            },
            birthDay: '',
            showWarning: false,
            errorMessage: ''
        }
    },
    template: viewLoader.load('registration'),
    methods: {
        registration() {
            rq.post('/api/registration', resp => {
                if (resp.status !== 201) {
                    resp.text().then(error => {
                        this.showWarning = true;
                        this.errorMessage = "Ooops, registration failed due to: " + error;
                    })
                } else {
                    alert("Success");
                }
            }, {}, {body: this.profile})
        },
        closeWarning() {
            this.showWarning = false;
        },
        login() {
            this.$router.push({path: '/login'});
        }
    }, components: {
        'popup': PopupComponent
    }
};