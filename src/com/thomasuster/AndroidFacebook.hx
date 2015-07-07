package com.thomasuster;

#if android
import openfl.utils.JNI;
#end

class AndroidFacebook {

    static var _init:Dynamic;

    public function new():Void {}

    public function init():Void {
        initJNI();
        _init();
    }

    function initJNI():Void {
        if(_init == null) {
            #if android
            _init = JNI.createStaticMethod("com/thomasuster/Facebook", "init", "()V");
            #end
        }
    }
}