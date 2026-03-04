-keep class com.wavechat.messenger.** { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
