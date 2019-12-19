import {LoginComponent} from "./components/login/login.js";

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
        component: LoginComponent,meta: {
            requiresAuth: true
        }
    }
];

const router = new VueRouter({
    routes // сокращённая запись для `routes: routes`
});
router.beforeEach((to, from, next) => {
    if(to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem('jwt') == null) {
            next({
                path: '/login',
                params: { nextUrl: to.fullPath }
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