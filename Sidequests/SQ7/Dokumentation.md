# API-Versionierung

## Inhaltsverzeichnis
- [Verschiedne Versionierungsstrategien](#verschiedene-versionierungsstrategien)
  - [URL-Parameter](#url-parameter)
  - [Request Parameter (Header)](#request-parameter-header)
  - [Custom Header](#custom-header)


## Verschiedene Versionierungsstrategien
### URL-Parameter
Die einfachste Variante der Versionierung ist die Verwendung von URL-Parametern. Hierbei wird die Version der API in der URL angegeben. Zum Beispiel: `https://api.example.com/v1/resource`.

#### Vorteile:
- Einfach zu implementieren
- Einfach zu testen
- Klar und leicht verständlich für Nutzer

#### Nachteile:
- Kann zu langen URLs führen
- Erfordert Anpassungen an der Routing-Logik
- Kann die URL-Struktur unübersichtlich machen

### Request Parameter (Header)
Eine weitere Möglichkeit ist die Verwendung von Request-Parametern. Hierbei wird die Version der API in einem Header angegeben. Zum Beispiel: `Accept: application/com.example.v1+json`.

#### Vorteile:
- Saubere URL-Struktur
- Ermöglicht flexible Versionierung
- Keine Auswirkungen auf die URL-Struktur

#### Nachteile:
- Komplexer zu implementieren
- Erfordert zusätzliche Konfiguration auf Client-Seite
- Kann zu Problemen mit Caching und Proxies führen

### Custom Header
Eine weitere Möglichkeit ist die Verwendung eines benutzerdefinierten Headers. Hierbei wird die Version der API in einem benutzerdefinierten Header angegeben. Zum Beispiel: `X-API-Version: 1`.

#### Vorteile:
- Flexibel
- Keine Änderungen an der URL-Struktur notwendig
- Unterstützt mehrere Versionierungsstrategien parallel

#### Nachteile:
- Komplexer zu implementieren
- Erfordert zusätzliche Konfiguration auf Client-Seite
- Kann zu Problemen mit Caching und Proxies führen
