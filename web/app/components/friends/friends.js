import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";
import {PopupComponent} from "../popup/popup.js";

export let FriendsComponent = {
    data() {
        return {
            friends: [
                {firstName:'Somename', lastName:'Lastname'},
                {firstName:'Secondfirst', lastName:'Secondlast'},
                ],
            searchString: '',
            fnameFilter: '',
            lnameFilter: ''
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
        },
        filteredFriends() {
            let arr = [];
            this.friends.forEach(profile=> {
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
            }
        }
    },
    components: {
        'popup': PopupComponent
    }
};