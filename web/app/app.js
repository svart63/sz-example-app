import {LoginComponent} from "./components/login/login.js";
import {RegistrationComponent} from "./components/registration/registration.js";
import {ProfileComponent} from "./components/profile/profile.js";
import {FriendsComponent} from "./components/friends/friends.js";

const routes = [
    {
        path: '/login',
        component: LoginComponent
    },
    {
        path: '/registration',
        component: RegistrationComponent
    },
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