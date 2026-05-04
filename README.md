# AMTEQ Q-App Android APK bauen

Diese Version der Android-App verbindet automatisch mit:

`http://10.0.0.74:8756`

Der PC-Server muss laufen und das Server-Fenster muss offen bleiben.

## Ganz einfache Anleitung

1. ZIP entpacken.
2. GitHub Repository öffnen.
3. Oben auf **Code** klicken.
4. Auf **Add file** klicken.
5. Auf **Upload files** klicken.
6. Den kompletten Inhalt aus dem entpackten ZIP hochladen.
   Wichtig: Nicht die ZIP-Datei selbst hochladen.
7. Unten auf **Commit changes** klicken.
8. Oben auf **Actions** klicken.
9. Den Eintrag **Build APK** öffnen.
10. Warten, bis ein grüner Haken erscheint.
11. Den Build öffnen und ganz nach unten scrollen.
12. Unter **Artifacts** die Datei **AMTEQ-QApp-APK** herunterladen.
13. Die heruntergeladene ZIP entpacken.
14. Die APK aufs Android-Handy kopieren und installieren.

## Wichtig

Wenn am Handy weiterhin **Server nicht erreichbar** steht:

- PC-Server-Fenster offen lassen.
- Handy muss im gleichen WLAN/Firmennetz sein.
- Der Server muss auf `http://10.0.0.74:8756` laufen.
- Windows-Firewall darf Port `8756` nicht blockieren.
