import {viewLoader} from "../../../js/view-loader.js";
import {rq} from "../../../js/rq.js";
import {PopupComponent} from "../popup/popup.js";
import {$iw} from "../../../js/input-waiter.js";

export let SearchComponent = {
    data() {
        return {
            friends: [],
            searchString: '',
            fnameFilter: '',
            lnameFilter: '',
            searching: false
        }
    },
    template: viewLoader.load('search'),
    created() {

    },
    watch: {
        searchString() {
            $iw('search', 500).then(() => {
                this.modifySearchString();
                if (this.fnameFilter) {
                    this.search(this.fnameFilter, this.lnameFilter);
                }
            })
        }
    },
    methods: {
        modifySearchString() {
            let strings = this.searchString.split(/\s+/gm, 2);
            this.fnameFilter = strings[0];
            if (strings[1]) {
                this.lnameFilter = strings[1];
            } else {
                this.lnameFilter = '';
            }
        },
        search(firstName, lastName) {
            rq.get("/api/search/profiles", (resp) => {
                resp.json().then(json => {
                    this.friends = json;
                    this.nothingFound = this.friends.length === 0;
                });
            }, {
                firstName: firstName,
                lastName: lastName
            })
        },
        add(id) {
            rq.post("/api/friendship/add/" + id, () => {
                alert('Success');
            })
        }
    },
    components: {
        'popup': PopupComponent
    }
};