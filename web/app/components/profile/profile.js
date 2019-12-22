import {viewLoader} from "../../../js/ViewLoader.js";

export let ProfileComponent = {
    data() {
        return {
            profile: {}
        }
    },
    template: viewLoader.load('profile'),
    created() {
        let id = this.$route.params['id'];
        this.load(id)
    },
    methods: {
        load(id) {
            fetch('/profile/' + id, {
                credentials: 'include'
            }).then(resp => {
                resp.json().then(json => {
                    this.profile = json;
                })
            });
        }
    }
};