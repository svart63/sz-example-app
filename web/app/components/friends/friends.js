import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";
import {PopupComponent} from "../popup/popup.js";

export let FriendsComponent = {
    data() {
        return {
            friends: []
        }
    },
    template: viewLoader.load('friends'),
    mounted() {
        this.loadFriends();
    },
    methods: {
        loadFriends() {

        }
    },
    components: {
        'popup': PopupComponent
    }
};