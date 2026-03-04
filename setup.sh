#!/bin/bash
# Скрипт первоначальной настройки проекта
# Запусти этот файл ОДИН РАЗ перед первым использованием

echo "🚀 Настройка WaveChat Android проекта..."

# Скачиваем gradle-wrapper.jar
WRAPPER_DIR="gradle/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/gradle-wrapper.jar"

if [ ! -f "$WRAPPER_JAR" ]; then
    echo "📦 Скачиваю gradle-wrapper.jar..."
    curl -L -o "$WRAPPER_JAR" \
        "https://github.com/gradle/gradle/raw/v8.4.0/gradle/wrapper/gradle-wrapper.jar"
    echo "✅ gradle-wrapper.jar скачан"
else
    echo "✅ gradle-wrapper.jar уже существует"
fi

chmod +x gradlew
echo "✅ gradlew сделан исполняемым"
echo ""
echo "🎉 Готово! Теперь запусти: ./gradlew assembleDebug"
