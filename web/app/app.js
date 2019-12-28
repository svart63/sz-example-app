import {LoginComponent} from "./components/login/login.js";
import {RegistrationComponent} from "./components/registration/registration.js";
import {ProfileComponent} from "./components/profile/profile.js";

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
        path: '/id/:id',
        component: ProfileComponent
    },
    {
        path: '/',
        component: ProfileComponent
    }
];

const router = new VueRouter({
    routes
});

const app = new Vue({
    router
}).$mount('#app');