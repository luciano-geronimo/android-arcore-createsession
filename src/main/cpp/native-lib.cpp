#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_teste01_arcore_geronimo_don_androidarcoreteste01_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
