# AMTEQ Q-App Android

Diese Dateien sind alles, was GitHub braucht, um die Android-App zu bauen.

Die App verbindet standardmäßig mit:

`http://10.0.0.21:8756`

Das bedeutet: Auf dem Server `10.0.0.21` muss einmal zentral der Q-App Server laufen.
Normale Handy-User brauchen am PC nichts.

## GitHub Build

1. In GitHub Repository öffnen.
2. Inhalt dieses Ordners `01_HANDY_APP_GITHUB` hochladen.
3. `Commit changes` klicken.
4. Auf `Actions` klicken.
5. `Build APK` öffnen.
6. Warten bis grüner Haken.
7. Unter `Artifacts` die APK herunterladen.
8. APK am Android-Handy installieren.

## Wichtig

Wenn der Server nicht `10.0.0.21` ist, in `MainActivity.java` diese Zeile ändern:

`private static final String DEFAULT_URL = "http://10.0.0.21:8756";`
