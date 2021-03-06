import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";
import {PopupComponent} from "../popup/popup.js";
import {$iw} from "../../../js/input-waiter.js";

export let FriendsComponent = {
    data() {
        return {
            friends: [],
            filteredFriends: [],
            searchString: '',
            fnameFilter: '',
            lnameFilter: ''
        }
    },
    template: viewLoader.load('friends'),
    mounted() {
        this.loadFriends();
    },
    watch: {
        searchString() {
            $iw('search', 500).then(() => {
                this.modifySearchString();
                this.filteredFriends = this.filter();
            })
        }
    },
    methods: {
        loadFriends() {
            rq.get('/api/friendship/friends', resp => {
                resp.json().then(json => {
                    this.friends = json;
                    this.filteredFriends = this.friends;
                })
            })
        },
        filter() {
            let arr = [];
            this.friends.forEach(profile => {
                let fname = profile.firstName;
                let lname = profile.lastName;
                if (this.contains(fname, this.fnameFilter) || this.contains(lname, this.lnameFilter)) {
                    arr.push(profile);
                }
            });
            return arr;
        },
        contains(where, what) {
            let source = where.toLowerCase();
            let target = what.toLowerCase();
            return source.includes(target);
        },
        modifySearchString() {
            let strings = this.searchString.split(/\s+/gm, 2);
            this.fnameFilter = strings[0];
            if (strings[1]) {
                this.lnameFilter = strings[1];
            } else {
                this.lnameFilter = strings[0];
            }
        }
    },
    components: {
        'popup': PopupComponent
    }
};