cordova.define("cordova-plugin-video-play.VideoPlay", function(require, exports, module) {
var exec = require("cordova/exec");
        module.exports = {
            videoPlay: function(){
                exec(
                null,
                null,
                "videoPlay",//feature name
                "videoPlay",//action
                []//要传递的参数，json格式
                );
            }
        }
});
