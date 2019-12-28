import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";

export let ProfileComponent = {
    data() {
        return {
            profile: {}
        }
    },
    template: viewLoader.load('profile'),
    created() {
        let id = this.$route.params['id'];
        if (id) {
            this.load(id);
        } else {
            this.load('');
        }
    },
    methods: {
        load(id) {
            let url = '/api/profile/';
            if (id) {
                url += id;
            }
            rq.get(url, (resp) => {
                resp.json().then(json => {
                    this.profile = json;
                })
            })
        }
    }
};