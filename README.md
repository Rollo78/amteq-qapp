# AMTEQ Q-App Android - Port 5045

Diese Version verbindet sich fest mit:

```text
http://10.0.0.21:5045/
```

## GitHub Build

1. ZIP entpacken.
2. Den gesamten Inhalt dieses Ordners in dein GitHub-Repository hochladen.
   Wichtig: `.github`, `app`, `build.gradle` und `settings.gradle` muessen direkt im Repo-Root liegen.
3. In GitHub auf **Actions** gehen.
4. Workflow **Build AMTEQ Q-App APK** starten.
5. Artifact **AMTEQ-QApp-5045-debug-apk** herunterladen.
6. APK am Handy installieren.

## Wichtig

- Server muss auf `10.0.0.21:5045` laufen.
- Handy muss den Server im Firmennetz/VPN erreichen.
- Android muss Installation aus unbekannten Quellen erlauben.

## Geaenderte Datei

```text
app/src/main/java/at/amteq/qapp/MainActivity.java
```

Dort steht:

```java
private static final String SERVER_URL = "http://10.0.0.21:5045/";
```
