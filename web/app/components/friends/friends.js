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
    created() {
        this.loadFriends();
    },
    methods: {
        loadFriends() {
            rq.get('/api/profile/friends', resp => {
                resp.json().then(json => {
                    this.friends.slice(0, this.friends.length);
                    json.forEach(friend => {
                        this.friends.push(friend);
                    })
                })
            })
        }
    },
    components: {
        'popup': PopupComponent
    }
};