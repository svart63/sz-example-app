class InputWaiter {

    constructor(name, timeout) {
        this._name = name;
        this._timeout = timeout;
    }

    timeout;
    expires = 0;

    async wait() {
        let now = Date.now();
        console.info('now - this.expires', now - this.expires);
        if (this.expires - now < 250) {
            return await this.timeout;
        }
        this.timeout = new Promise(r => this.getTimeout(r));
        return await this.timeout;
    }

    timer;

    getTimeout(r) {
        this.timer = setTimeout(() => {
            if (this.expires <= Date.now()) {
                r();
            } else {
                if (this.timer) {
                    clearTimeout(this.timer);
                }
                this.getTimeout(r)
            }
        }, this._timeout);
        return this.timer;
    }
}

let holder = {};

export async function $iw(name, timeout) {
    let now = Date.now();
    let element = holder[name];
    if (!element) {
        element = new InputWaiter(name, timeout);
        holder[name] = element;
    }
    element.expires = now + element._timeout;
    return element.wait().then();
}