import {ProfileComponent} from "./components/profile/profile.js";
import {FriendsComponent} from "./components/friends/friends.js";
import {SearchComponent} from "./components/search/search.js";

const routes = [
    {
        path: '/id/:id',
        component: ProfileComponent
    },
    {
        path: '/',
        component: ProfileComponent
    },
    {
        path: '/friends/:id',
        component: FriendsComponent
    },
    {
        path: '/friends',
        component: FriendsComponent
    },
    {
        path: '/search',
        component: SearchComponent
    }
];

const router = new VueRouter({
    routes
});

const app = new Vue({
    router
}).$mount('#app');