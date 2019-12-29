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
        alias: '/id',
        children: [
            {path: 'friends', component: FriendsComponent}
        ]
    }
];

const router = new VueRouter({
    routes
});
let isLoginPage = false;
router.beforeEach((to, from, next) => {
    let path = router.currentRoute.path;
    isLoginPage = path.indexOf('login') || path.indexOf('registration');
    next();
});
const app = new Vue({
    data() {
        return {
            isNotLoginPage: !isLoginPage
        }
    },
    router
}).$mount('#app');