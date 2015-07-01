#ifndef STATIC_LINK
#define IMPLEMENT_API
#endif
#include <hx/CFFI.h>
#include "Utils.h"

using namespace facebook;

void init(){
    _init();
}
DEFINE_PRIM (init,0);

extern "C" void facebook_main () {
}
DEFINE_ENTRY_POINT (facebook_main);

extern "C" int facebook_register_prims () { return 0; }