# WaveChat Android App 📱

Нативное Android-приложение (WebView wrapper) для мессенджера WaveChat.

---

## ⚡ Быстрая сборка APK (3 способа)

### Способ 1: Android Studio (рекомендуется)

1. Скачай и установи [Android Studio](https://developer.android.com/studio)
2. Открой папку `WaveChat_Android` через **File → Open**
3. Подожди, пока Gradle синхронизируется (1-3 мин)
4. Нажми **Build → Build Bundle(s) / APK(s) → Build APK(s)**
5. APK появится в: `app/build/outputs/apk/debug/app-debug.apk`

### Способ 2: Командная строка (если есть JDK + Android SDK)

```bash
cd WaveChat_Android
./gradlew assembleDebug
```
APK: `app/build/outputs/apk/debug/app-debug.apk`

### Способ 3: Онлайн-сборка через Appetize или Buildozer

Загрузи папку `WaveChat_Android` на:
- https://appetize.io (тест без установки)
- https://www.codemagic.io (бесплатная облачная сборка)
- https://circleci.com

---

## 🔧 Что умеет приложение

- ✅ Полный интерфейс WaveChat в нативном приложении
- ✅ Поддержка WebSocket (реальный-тайм чат)
- ✅ Автоматический фолбэк: `nnoxtgram.mooo.com` → `144.31.244.143`
- ✅ Страница "нет интернета" при обрыве соединения
- ✅ Кнопка "назад" — возврат по истории браузера
- ✅ Разрешения: камера, микрофон, файлы
- ✅ Тёмная тема (совпадает с дизайном сайта)
- ✅ Иконка приложения в стиле WaveChat
- ✅ minSdk 21 (Android 5.0+) — охват ~99% устройств

---

## 📁 Структура проекта

```
WaveChat_Android/
├── app/
│   ├── src/main/
│   │   ├── java/com/wavechat/messenger/
│   │   │   ├── MainActivity.java      ← основной WebView
│   │   │   └── SplashActivity.java    ← экран загрузки
│   │   ├── res/
│   │   │   ├── mipmap-*/ic_launcher*  ← иконки
│   │   │   ├── values/strings.xml
│   │   │   ├── values/styles.xml
│   │   │   └── xml/network_security_config.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---

## 🌐 URL приложения

| Приоритет | URL |
|-----------|-----|
| Основной  | `https://nnoxtgram.mooo.com` |
| Резервный | `http://144.31.244.143` |

Чтобы изменить URL — отредактируй константы в `MainActivity.java`:
```java
private static final String APP_URL = "https://nnoxtgram.mooo.com";
private static final String FALLBACK_URL = "http://144.31.244.143";
```

---

## 📦 Публикация в Google Play

Для публикации нужен подписанный APK:
1. **Build → Generate Signed Bundle / APK**
2. Создай keystore или используй существующий
3. Выбери **Release** конфигурацию

---

*Версия: 2.0 | minSdk: 21 | targetSdk: 34*
