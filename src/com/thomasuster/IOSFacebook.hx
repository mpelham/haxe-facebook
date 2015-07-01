package com.thomasuster;
import cpp.Lib;
class IOSFacebook {

    static var _activateApp:Dynamic;

    public function new():Void {}

    public function activateApp() {
        load();
        _activateApp();
    }

    function load():Void {
        if(_activateApp == null) {
            #if ios
            _activateApp = Lib.load("facebook","activateApp",0);
            #end
        }
    }
}