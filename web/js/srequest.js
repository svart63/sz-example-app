export let srequest = {
    post(url, params, body) {
        if (body) {
            params.body = JSON.stringify(body);
        }
        return fetch(url, params)
    }
};