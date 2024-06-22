# Unterschiede zwischen klassischen Nightly Builds und dem Continuous-Integration-Ansatz

## Nightly Builds
- **Definition**: Nightly Builds sind tägliche, automatisierte Kompilierungen und Tests des gesamten Projekts, die in der Regel nachts stattfinden.
- **Frequenz**: Einmal pro Tag.
- **Zweck**: Sicherstellung, dass die Software zumindest einmal täglich vollständig gebaut und getestet wird.
- **Prozess**: Der Quellcode wird nachts aus dem Versionskontrollsystem abgerufen, kompiliert und getestet.
- **Feedback-Zyklus**: Lang, da Entwickler erst am nächsten Tag Feedback erhalten.

## Continuous Integration (CI)
- **Definition**: CI ist ein Entwicklungsprozess, bei dem Codeänderungen kontinuierlich in das zentrale Repository integriert und automatisch getestet werden.
- **Frequenz**: Mehrmals täglich, oft bei jeder Codeänderung.
- **Zweck**: Früherkennung von Problemen und Konflikten durch kontinuierliche Integration und Testen.
- **Prozess**: Jede Codeänderung wird sofort in das zentrale Repository integriert und durchläuft automatisierte Tests.
- **Feedback-Zyklus**: Kurz, da Entwickler sofortiges Feedback erhalten.

# Probleme bei Nightly Builds
1. **Langsames Feedback**: Entwickler müssen oft bis zum nächsten Tag warten, um zu erfahren, ob ihre Änderungen Probleme verursacht haben.
2. **Fehlerakkumulation**: Fehler, die tagsüber gemacht wurden, können sich ansammeln und zu größeren Problemen führen.
3. **Konflikte**: Da Integration und Tests nur einmal täglich durchgeführt werden, können Integrationskonflikte spät entdeckt werden.
4. **Produktivitätsverlust**: Verzögerungen bei der Fehlererkennung können die Produktivität der Entwickler beeinträchtigen.

# Vorteile durch den Einsatz von CI/CD-Tools wie Jenkins
1. **Schnelles Feedback**: Entwickler erhalten sofortiges Feedback zu ihren Änderungen, was die Behebung von Fehlern erleichtert.
2. **Früherkennung von Problemen**: Probleme und Konflikte werden frühzeitig erkannt und können schnell behoben werden.
3. **Automatisierung**: Jenkins automatisiert den Build- und Testprozess, was Zeit spart und menschliche Fehler reduziert.
4. **Kontinuierliche Bereitstellung**: CI/CD-Tools unterstützen die kontinuierliche Bereitstellung von Software, was die Release-Zyklen verkürzt.
5. **Erhöhte Qualität**: Durch häufige Tests und Integrationen wird die Softwarequalität verbessert.
6. **Verbesserte Zusammenarbeit**: CI/CD fördert eine bessere Zusammenarbeit und Kommunikation im Team, da alle Änderungen kontinuierlich integriert und getestet werden.
