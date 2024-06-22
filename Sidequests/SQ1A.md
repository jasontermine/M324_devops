# SQ 1A

#### 1. Was ist notwendig, um eine Software auszuliefern?
**1.1** Klärt gemeinsam anhand der oben angegebenen Webseiten die folgenden Begriffe (les die Webseiten arbeitsteilig)

**Agile**:
- Agile Methoden sind darauf ausgerichtet, Software in kurzen Zyklen zu entwickeln und dabei auf Kundenfeedback zu reagieren. Agile Methoden sind prinzipiell iterativ und inkrementell, was bedeutet, dass die Software in kleinen Schritten entwickelt wird. Die adaptive Natur von Agile ermöglicht es Teams, sich schnell an sich ändernde Anforderungen anzupassen.

**Continuous Integration (CI)**:

- Continuous Integration (CI) ist ein Entwicklungsprinzip, bei dem Entwickler regelmässig Code in ein gemeinsames Repository (Beispielsweise Github etc.) hochladen. Immer wenn jemand Code in das Repository einen `git push` macht, werden automatiserte Tests ausgeführt, die die Sicherheit, Funktionalität und Qualität des Codes überprüfen. Betriebe oder Organisationen können eigene Tests entwickeln die spezifisch auf Ihre Anforderungen zugeschnitten sind. Bei JavaScript oder TypeScript Projekten wird oft ein Linter verwendet, der den Code auf Fehler und Stil überprüft. 

**DevOps (Developer Operations)**:

- DevOps ist eine Kultur und Praxis, die darauf abzielt, die Zusammenarbeit zwischen Entwicklern und Betriebsteams zu verbessern. Da es keine herkömmliche oder Offizielle Definition von DevOps gibt, kann es in verschiedenen Betrieben oder Organisationen unterschiedlich interpretiert und angewendet werden.  

**1.2** Danach stellt ihr die richtige Reihenfolge her.  

**1.3** Bestimmt, welches Team (Entwickler, Tester oder Operations) für jede Aufgabe zuständig ist.

- **Compilieren** der einzelnen Klassen bzw. Komponenten

    - *Beschreibung*: Übersetzung des Quellcodes von Klassen oder Komponenten in maschinenlesbaren Code.
    `Team: Entwickler`

- **Unit-Test**

    - *Beschreibung*: Test einzelner Komponenten oder Module der Software, um sicherzustellen, dass sie korrekt funktionieren.
    `Team: Entwickler`

- **Software-Build**

    - *Beschreibung*: Prozess der Übersetzung des Quellcodes in ausführbare Dateien.
    `Team: Entwickler`

- **Systemintegrationstest**

    - *Beschreibung*: Test, um sicherzustellen, dass verschiedene Systeme oder Komponenten nahtlos zusammenarbeiten.
    `Team: Tester`

- **Deployment in Testumgebung**

    - *Beschreibung*: Installation und Konfiguration der Software in einer Testumgebung zur Durchführung von Tests.
    `Team: Entwickler/Tester`

- **Deployment und Konfiguration im Produktivsystem**

    - *Beschreibung*: Installation und Konfiguration der Software in der Live-Produktionsumgebung für Endbenutzer.
    `Team: Operations`

- **Überwachung und Monitoring**

    - *Beschreibung*: Kontinuierliche Überwachung der Software, um die Leistung zu messen und Probleme frühzeitig zu erkennen.
    `Team: Operations`

- **Rollback**

    - *Beschreibung*: Prozess der Wiederherstellung eines früheren, stabilen Zustands der Software im Falle eines Fehlers nach einer neuen Bereitstellung.
    `Team: Operations`
