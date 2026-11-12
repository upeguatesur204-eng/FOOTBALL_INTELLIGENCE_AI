# FOOTBALL INTELLIGENCE AI — Android

Aplicación Android nativa desarrollada con Kotlin y Jetpack Compose.

## Estado de esta versión
- Selector de dos equipos.
- Pantallas iniciales de análisis, jugadores e historial.
- Interfaz adaptable y navegación inferior.
- Etiqueta visible de MODO DEMO: no presenta estadísticas inventadas como si fueran datos reales.
- Permiso de internet preparado para la futura conexión a fuentes deportivas.

## Requisitos
- JDK 17.
- Android SDK 35.
- Gradle 8.9 (el workflow lo instala automáticamente).

## Compilación local
```bash
gradle assembleDebug
```
El APK se genera en `app/build/outputs/apk/debug/app-debug.apk`.

## Compilación automática en GitHub
Cada push a `main`, pull request hacia `main` o ejecución manual dispara GitHub Actions. Al terminar, descarga el artefacto **football-intelligence-ai-debug-apk** desde la sección Actions del repositorio.

## Próxima etapa: datos deportivos
La app todavía no consulta datos deportivos ni genera predicciones reales. Antes de activar pronósticos se integrará una fuente de datos y se validarán sus límites, cobertura y atribución.

No incluir claves privadas de API dentro del APK. Si el proveedor requiere una clave, utilizar un backend intermedio o configuración segura.
