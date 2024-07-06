# API-Versionierung

## Inhaltsverzeichnis
- [Verschiedene Versionierungsstrategien](#verschiedene-versionierungsstrategien)
  - [URL-Parameter](#url-parameter)
  - [Request Parameter (Header)](#request-parameter-header)
  - [Custom Header](#custom-header)
  - [Parameter Versionierung](#parameter-versionierung)
- [Bewertung der verschiedenen Methoden](#bewertung-der-verschiedenen-methoden)

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

### Parameter Versionierung
Eine weitere Möglichkeit ist die Verwendung von Parametern in der URL. Hierbei wird die Version der API in der URL angegeben. Zum Beispiel: `https://api.example.com/resource?version=1`.

#### Vorteile:
- Einfach zu implementieren
- Flexibel
- Keine Änderungen an der URL-Struktur notwendig

#### Nachteile:
- Kann zu Missverständnissen führen, wenn Parameter nicht korrekt angegeben werden
- Mögliche Konflikte mit anderen URL-Parametern
- Erfordert zusätzliche Logik zur Versionierung in der Anwendung

## Bewertung der verschiedenen Methoden

| Methode                 | Zuverlässigkeit | Einfachheit              | Flexibilität                |
|-------------------------|-----------------|--------------------------|-----------------------------|
| **URL-Parameter**       | Hoch            | Sehr einfach             | Mittel                      |
| - Vorteile              | - Klar und verständlich für Nutzer | - Einfach zu implementieren | -                          |
| - Nachteile             | - Kann zu langen URLs führen       | - Erfordert Anpassungen an der Routing-Logik | - Kann die URL-Struktur unübersichtlich machen |
| **Request Parameter (Header)** | Hoch   | Mittel                   | Hoch                        |
| - Vorteile              | - Saubere URL-Struktur             | - Ermöglicht flexible Versionierung | - Keine Auswirkungen auf die URL-Struktur |
| - Nachteile             | - Kann zu Problemen mit Caching und Proxies führen | - Komplexer zu implementieren | - Erfordert zusätzliche Konfiguration auf Client-Seite |
| **Custom Header**       | Hoch            | Mittel                   | Hoch                        |
| - Vorteile              | - Flexibel                         | - Keine Änderungen an der URL-Struktur notwendig | - Unterstützt mehrere Versionierungsstrategien parallel |
| - Nachteile             | - Kann zu Problemen mit Caching und Proxies führen | - Komplexer zu implementieren | - Erfordert zusätzliche Konfiguration auf Client-Seite |
| **Parameter Versionierung** | Hoch   | Einfach                  | Mittel                      |
| - Vorteile              | - Einfach zu implementieren         | - Flexibel                    | - Keine Änderungen an der URL-Struktur notwendig |
| - Nachteile             | - Kann zu Missverständnissen führen, wenn Parameter nicht korrekt angegeben werden | - Mögliche Konflikte mit anderen URL-Parametern | - Erfordert zusätzliche Logik zur Versionierung in der Anwendung |


## Entscheidung für eine API-Versionierungsstrategie
Am einfachsten und zuverlässigsten ist die URL-Parameter Methode. Sie ist einfach zu implementieren, leicht zu testen und klar und verständlich für Nutzer. Die Nachteile wie lange URLs oder unübersichtliche URL-Struktur sind in den meisten Fällen vernachlässigbar. Daher entscheiden wir uns für die Custom Header Methode.

Das weil wir bereits Erfahrungen mit URL-Parametrierten APIs haben und die Custom Header Methode eine gute Alternative darstellt, die die URL-Struktur nicht beeinflusst und mehr Flexibilität bietet. Die Implementierung ist zwar etwas komplexer, aber die Vorteile überwiegen die Nachteile. Ebenso möchten wir diese Methode verwenden, um etwas neues auszuprobieren und zu lernen