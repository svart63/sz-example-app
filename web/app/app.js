import {LoginComponent} from "./components/login/login.js";
import {ProfileComponent} from "./components/profile/profile.js";

const routes = [
    {
        path: '/login',
        component: LoginComponent,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/id/:id',
        component: ProfileComponent, meta: {
            requiresAuth: true
        }
    },
    {
        path: '/',
        component: ProfileComponent, meta: {
            requiresAuth: true
        }
    }
];

const router = new VueRouter({
    routes // сокращённая запись для `routes: routes`
});
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem('auth') == null) {
            next({
                path: '/login',
                params: {nextUrl: to.fullPath}
            })
        } else {
            next()
        }
    } else {
        next()
    }
});

const app = new Vue({
    router
}).$mount('#app');