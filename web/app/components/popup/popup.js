import {viewLoader} from "../../../js/view-loader.js";

export let PopupComponent = {
    props: ['show', 'close'],
    data() {
        return {
            showPopup: true
        };
    },
    template: viewLoader.load('popup'),
};