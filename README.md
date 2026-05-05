# AMTEQ Q-App Android

Diese Android-App oeffnet fest den internen AMTEQ Q-App Server:

`http://10.0.0.21:5022/`

## APK ueber GitHub bauen

1. Diesen Ordner in ein GitHub-Repository hochladen.
2. In GitHub auf **Actions** gehen.
3. Workflow **Build AMTEQ Q-App APK** starten.
4. Danach das Artefakt **AMTEQ-QApp-debug-apk** herunterladen.
5. APK am Handy installieren.

## Voraussetzungen am Handy

- Handy muss im Firmen-WLAN/VPN sein.
- Android muss die Installation aus unbekannten Quellen erlauben.
- Der Server `10.0.0.21:5022` muss erreichbar sein.

## Server

Die Excel-Dateien werden nicht direkt vom Handy gelesen. Die App spricht mit dem Server auf 10.0.0.21. Der Server liest den Windows-Netzwerkordner.
