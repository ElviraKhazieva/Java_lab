let postsAmount = 0;
let updated;
let path;
const postsToLoad = 5;
const loadBorder = 100;
let requestSend = true;

function request() {

    if (document.body.scrollHeight - window.pageYOffset - document.body.clientHeight < loadBorder && requestSend) {
        requestSend = false;
        $.ajax({
            url: path + "?begin=" + postsAmount + "&end=" + (postsAmount + postsToLoad) + "&updated="
            + updated,
            success: function (msg) {
                requestSend = true;
                $("#posts-appender").append(msg);
                postsAmount += postsToLoad;
            }
        });
    }


}

function addScrollListener() {
    if (postsAmount === 0) {
        request();
    }
    document.addEventListener("scroll", request);
}