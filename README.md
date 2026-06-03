# AMTEQ Produktions-App Android

SERVER_URL ist fest gesetzt auf:

`http://10.0.0.21:5045/`

Fixes:
- Port 5045
- Kamera-Permission
- WebView-Dateiauswahl mit Kameraaufnahme ueber FileProvider
- GitHub Action fix: Gradle 8.10.2 statt Gradle 9.x
- AndroidX aktiviert
- Android SDK 35 wird in der Action installiert

## GitHub

1. Inhalt dieses Ordners ins Repository hochladen.
2. Actions starten oder Push abwarten.
3. Artifact `AMTEQ-QApp-5045-debug-apk` herunterladen.
4. APK am Handy installieren.


Produktions-App separat installierbar:
- applicationId: at.amteq.produktion
- App-Name: AMTEQ Produktion
- überschreibt die Q-App nicht.
