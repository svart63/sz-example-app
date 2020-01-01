import {ProfileComponent} from "./components/profile/profile.js";
import {FriendsComponent} from "./components/friends/friends.js";

const routes = [
    {
        path: '/',
        component: ProfileComponent,
        alias: ['/id', "/id/{id}"],
    },
    {
        path: '/friends',
        component: FriendsComponent
    }
];

const router = new VueRouter({
    routes
});

const app = new Vue({
    router
}).$mount('#app');