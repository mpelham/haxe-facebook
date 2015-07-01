package com.thomasuster;
import cpp.Lib;
class IOSFacebook {

    static var _init:Dynamic;

    public function new():Void {}

    public function init() {
        load();
        _init();
    }

    function load():Void {
        if(_init == null) {
            #if ios
            _init = Lib.load("facebook","init",0);
            #end
        }
    }
}