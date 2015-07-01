package com.thomasuster;

#if android
import openfl.utils.JNI;
#end

class AndroidApplovin {

    static var _init:Dynamic;
    static var _preload:Dynamic;
    static var _isReady:Dynamic;
    static var _show:Dynamic;
    static var _didSucceed:Dynamic;
    static var _wasRejected:Dynamic;
    static var _didFailWithError:Dynamic;

    public function new():Void {}

    public function init():Void {
        initJNI();
        _init();
    }

    public function preload():Void {
        initJNI();
        _preload();
    }

    public function isReady():Bool {
        initJNI();
        if(_isReady() == 1)
            return true;
        return false;
    }

    public function show():Void {
        initJNI();
        _show();
    }

    public function didSucceed():Bool {
        initJNI();
        if(_didSucceed() == 0)
            return false;
        return true;
    }

    public function wasRejected():Bool {
        initJNI();
        if(_wasRejected() == 0)
            return false;
        return true;
    }

    public function didFailWithError():Bool {
        initJNI();
        if(_didFailWithError() == 0)
            return false;
        return true;
    }

    function initJNI():Void {
        if(_init == null) {
            #if android
            _init = JNI.createStaticMethod("com/thomasuster/Applovin", "init", "()V");
            _preload = JNI.createStaticMethod("com/thomasuster/Applovin", "preload", "()V");
            _isReady = JNI.createStaticMethod("com/thomasuster/Applovin", "isReady", "()I");
            _show = JNI.createStaticMethod("com/thomasuster/Applovin", "show", "()V");
            _isReady = JNI.createStaticMethod("com/thomasuster/Applovin", "isReady", "()I");
            _didSucceed = JNI.createStaticMethod("com/thomasuster/Applovin", "didSucceed", "()I");
            _wasRejected = JNI.createStaticMethod("com/thomasuster/Applovin", "wasRejected", "()I");
            _didFailWithError = JNI.createStaticMethod("com/thomasuster/Applovin", "didFailWithError", "()I");
            #end
        }
    }
}