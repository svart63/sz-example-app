export class Rq {
    post(url, callback, pathParams, params) {
        url += this.buildUri(pathParams);
        let p = Object.assign({}, params);
        p['method'] = 'POST';
        p['credentials'] = 'include';
        p['redirect'] = 'manual';
        let body = p['body'];
        if (body) {
            p['body'] = JSON.stringify(body);
        }
        let headers = p["headers"];
        if (!headers) {
            headers = {};
            p["headers"] = headers;
        }
        if (!headers['content-type']) {
            headers['content-type'] = 'application/json';
        }
        return fetch(url, p).then(resp => callback(resp))
    }


    get(url, callback, pathParams, params) {
        url += this.buildUri(pathParams);
        let p = Object.assign({}, params);
        p['method'] = 'GET';
        p['credentials'] = 'include';
        fetch(url, p).then(resp => callback(resp))
    }

    buildUri(params) {
        let uri = '';
        if (!params) {
            return uri;
        }
        Object.keys(params).forEach(k => {
            if (uri) {
                uri += '&';
            } else {
                uri += '?';
            }
            uri += encodeURI(k + '=' + params[k]);
        });
        return uri;
    }
}

export const rq = new Rq();