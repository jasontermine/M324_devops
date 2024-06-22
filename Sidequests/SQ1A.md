# SQ 1A

#### 1. Was ist notwendig, um eine Software auszuliefern?
**1.1 Klärt gemeinsam anhand der oben angegebenen Webseiten die folgenden Begriffe (les die Webseiten arbeitsteilig)**

**Agile**:
- Agile Methoden sind darauf ausgerichtet, Software in kurzen Zyklen zu entwickeln und dabei auf Kundenfeedback zu reagieren. Agile Methoden sind prinzipiell iterativ und inkrementell, was bedeutet, dass die Software in kleinen Schritten entwickelt wird. Die adaptive Natur von Agile ermöglicht es Teams, sich schnell an sich ändernde Anforderungen anzupassen.

**Continuous Integration (CI)**:

- Continuous Integration (CI) ist ein Entwicklungsprinzip, bei dem Entwickler regelmässig Code in ein gemeinsames Repository (Beispielsweise Github etc.) hochladen. Immer wenn jemand Code in das Repository einen `git push` macht, werden automatiserte Tests ausgeführt, die die Sicherheit, Funktionalität und Qualität des Codes überprüfen. Betriebe oder Organisationen können eigene Tests entwickeln die spezifisch auf Ihre Anforderungen zugeschnitten sind. Bei JavaScript oder TypeScript Projekten wird oft ein Linter verwendet, der den Code auf Fehler und Stil überprüft. 

**DevOps (Developer Operations)**:

- DevOps ist eine Kultur und Praxis, die darauf abzielt, die Zusammenarbeit zwischen Entwicklern und Betriebsteams zu verbessern. Da es keine herkömmliche oder Offizielle Definition von DevOps gibt, kann es in verschiedenen Betrieben oder Organisationen unterschiedlich interpretiert und angewendet werden.  

**1.2 Danach stellt ihr die richtige Reihenfolge her.**

**1.3 Bestimmt, welches Team (Entwickler, Tester oder Operations) für jede Aufgabe zuständig ist.**

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

#### 2. Was versteht man unter CI/CD? 

 - **Was bedeutet CI/CD?**

    - **CI/CD** steht für **Continuous Integration (CI)** und **Continuous Delivery/Deployment (CD)**. Diese Praktiken sind wesentliche Bestandteile moderner Softwareentwicklung und DevOps-Prozesse, die darauf abzielen, die Qualität und Effizienz von Software-Entwicklungs- und Bereitstellungsprozessen zu verbessern.

- **Continuous Integration (CI)**

    - **Continuous Integration** ist eine Methode, bei der Entwickler ihren Code häufig in ein gemeinsames Repository integrieren. Dies ermöglicht die frühzeitige Erkennung und Behebung von Integrationsproblemen. Wichtige Aspekte von CI sind:

    - **Automatisierte Tests**: Jeder Code-Commit wird durch automatisierte Tests überprüft, um sicherzustellen, dass neue Änderungen keine bestehenden Funktionen beeinträchtigen.
    - **Build-Automatisierung**: Der Code wird automatisch kompiliert und gebaut, was Entwicklungsfehler frühzeitig erkennen lässt.
    - **Frequent Commits**: Entwickler commiten ihren Code häufig, was eine schnelle Identifikation und Behebung von Fehlern ermöglicht.

- **Continuous Delivery (CD)**

    - **Continuous Delivery** erweitert CI um automatisierte Bereitstellungsprozesse und stellt sicher, dass die Software jederzeit in einer für die Produktion geeigneten Version vorliegt. Wichtige Aspekte von CD sind:

    - **Automatisierte Deployments**: Der Code wird nach erfolgreicher Integration automatisch in verschiedene Umgebungen (z.B. Test, Staging) bereitgestellt.
    - **Ständige Bereitstellungsfähigkeit**: Die Software kann jederzeit für die Produktion freigegeben werden, auch wenn die tatsächliche Freigabe manuell erfolgt.
    - **Geringeres Risiko**: Durch häufige, kleine Updates wird das Risiko von Bereitstellungsproblemen minimiert.

- **Continuous Deployment (CD)**

    - **Continuous Deployment** geht noch einen Schritt weiter als Continuous Delivery und automatisiert die Freigabe des Codes bis in die Produktion. Jedes Update, das die automatisierten Tests besteht, wird automatisch in die Produktionsumgebung überführt. Wichtige Aspekte von Continuous Deployment sind:

    - **Vollständige Automatisierung**: Der gesamte Prozess vom Code-Commit bis zur Produktionsfreigabe ist automatisiert.
    - **Schnellere Time-to-Market**: Änderungen werden schnell und kontinuierlich an die Nutzer ausgeliefert.
    - **Fehlererkennung in Echtzeit**: Fehler werden schnell identifiziert und behoben, was eine hohe Qualität der Software gewährleistet.

- **Vorteile von CI/CD**

    - **Schnellere Fehlererkennung und -behebung**: Fehler werden früh im Entwicklungsprozess erkannt und behoben [Quelle: [ComputerWeekly](https://www.computerweekly.com/de/tipp/Waegen-Sie-die-Vor-und-Nachteile-von-DevOps-sorgfaeltig-ab)].
    - **Höhere Codequalität**: Durch automatisierte Tests und häufige Integrationen wird die Qualität des Codes verbessert [Quelle: [Netzwoche](https://www.netzwoche.ch/news/2019-06-25/die-vorteile-von-devops-in-der-praxis)].
    - **Schnellere Bereitstellung**: Neue Features und Updates können schneller an die Nutzer ausgeliefert werden [Quelle: [Synopsys](https://www.synopsys.com/blogs/software-security/agile-cicd-devops-difference/)].
    - **Geringeres Risiko**: Durch kleinere und häufigere Updates wird das Risiko von Problemen reduziert [Quelle: [Azure](https://azure.microsoft.com/en-us/resources/cloud-computing-dictionary/what-is-devops/)].

    CI/CD ist eine essentielle Praxis in der modernen Softwareentwicklung, die Teams dabei hilft, effizienter zu arbeiten und qualitativ hochwertige Software schneller bereitzustellen.

#### 3. Welche Aufgaben haben IT-Operations Teams im Kontext von SW-Deployment?

Im Kontext von Software-Deployment haben IT-Operations-Teams eine Vielzahl von Aufgaben, die darauf abzielen, die Stabilität, Sicherheit und Effizienz des gesamten Prozesses sicherzustellen. Hier sind die wichtigsten Aufgaben zusammengefasst:

**1. Bereitstellung und Verwaltung der Infrastruktur**
- IT-Operations-Teams sind verantwortlich für die Bereitstellung und Verwaltung der zugrundeliegenden Infrastruktur, auf der Software ausgeführt wird. Dies umfasst sowohl physische Server als auch Cloud-basierte Dienste, um sicherzustellen, dass die Infrastruktur stabil, sicher und skalierbar ist [Quelle: [Netzwoche](https://www.netzwoche.ch/news/2019-06-25/die-vorteile-von-devops-in-der-praxis)].

**2. Automatisierung von Deployment-Prozessen**
- Ein wesentlicher Aspekt der Arbeit von IT-Operations-Teams ist die Automatisierung von Deployment-Prozessen. Dies schließt die Implementierung und Verwaltung von CI/CD-Pipelines ein, die den Code automatisch von der Entwicklungs- bis zur Produktionsumgebung durchlaufen lassen [Quelle: [Azure](https://azure.microsoft.com/en-us/resources/cloud-computing-dictionary/what-is-devops/)].

**3. Konfigurationsmanagement**
- Die Teams sind für das Konfigurationsmanagement zuständig, um sicherzustellen, dass alle Umgebungen (Entwicklung, Staging, Produktion) konsistent und korrekt konfiguriert sind. Dies reduziert die Wahrscheinlichkeit von Fehlern und erleichtert die Problembehebung.

**4. Überwachung und Incident Management**
- Nach der Bereitstellung müssen IT-Operations-Teams kontinuierlich die Performance und Verfügbarkeit der Anwendungen überwachen. Dazu gehören das Einrichten von Überwachungstools und das Reagieren auf Vorfälle, um die Betriebszeit zu maximieren und Ausfälle zu minimieren [Quelle: [ComputerWeekly](https://www.computerweekly.com/de/tipp/Waegen-Sie-die-Vor-und-Nachteile-von-DevOps-sorgfaeltig-ab)].

**5. Sicherheitsmanagement**
- Sicherheit ist ein zentraler Aspekt der Arbeit von IT-Operations-Teams. Sie implementieren Sicherheitsprotokolle und -tools, um die Software und die Infrastruktur vor Bedrohungen zu schützen. Dies umfasst regelmäßige Sicherheitsupdates und das Überwachen auf Sicherheitsverletzungen [Quelle: [Azure](https://azure.microsoft.com/en-us/resources/cloud-computing-dictionary/what-is-devops/)].

**6. Backup und Wiederherstellung**
- IT-Operations-Teams stellen sicher, dass regelmäßige Backups durchgeführt werden und dass Pläne zur Wiederherstellung im Katastrophenfall vorhanden sind. Dies gewährleistet, dass im Falle eines Ausfalls oder Datenverlusts die Systeme schnell wiederhergestellt werden können [Quelle: [Synopsys](https://www.synopsys.com/blogs/software-security/agile-cicd-devops-difference/)].

**7. Kapazitätsplanung und Skalierung**
- Ein weiterer wichtiger Bereich ist die Kapazitätsplanung und das Management der Skalierung. IT-Operations-Teams analysieren die Nutzungsmuster und stellen sicher, dass die Infrastruktur den Anforderungen entsprechend skaliert werden kann, um Leistungseinbußen zu vermeiden.

**8. Unterstützung für Entwickler**
- IT-Operations-Teams arbeiten eng mit Entwicklern zusammen, um eine reibungslose Bereitstellung neuer Funktionen zu gewährleisten. Sie bieten Support und stellen Tools zur Verfügung, die es Entwicklern ermöglichen, ihre Arbeit effizienter zu erledigen und selbstständig sichere und konforme Umgebungen zu nutzen.

**9. Dokumentation und Wissensmanagement**
- Eine gründliche Dokumentation der Prozesse und Systeme ist essenziell, um Wissen innerhalb des Teams zu teilen und neuen Mitgliedern den Einstieg zu erleichtern. Dies umfasst auch die Dokumentation von Vorfällen und deren Lösungen, um aus Fehlern zu lernen und zukünftige Probleme zu vermeiden.

**10. Feedback- und Verbesserungsprozesse**
- Nach jedem Deployment sammeln IT-Operations-Teams Feedback und führen Post-Mortem-Analysen durch, um kontinuierlich Verbesserungsmöglichkeiten zu identifizieren und umzusetzen. Dies hilft, die Prozesse zu optimieren und die Qualität der Softwarebereitsstellung zu erhöhen.

#### 4. DevOps 

**Was bedeutet die Abkürzung DevOps?**

- **DevOps** steht für **Development and Operations**. Es handelt sich um eine Reihe von Praktiken, die darauf abzielen, die Zusammenarbeit zwischen Entwicklungs- (Development) und Betriebsteams (Operations) zu verbessern. Das Ziel von DevOps ist es, die Softwareentwicklungs- und Bereitstellungsprozesse zu automatisieren und zu optimieren, um die Effizienz und Qualität der Softwareprodukte zu steigern [Quelle: [Azure](https://azure.microsoft.com/en-us/resources/cloud-computing-dictionary/what-is-devops/)].

**Welche Vorteile bietet DevOps?**

1. **Schnellere Bereitstellung**:
   Durch die Automatisierung von Entwicklungs- und Bereitstellungsprozessen können Unternehmen schneller auf Marktanforderungen reagieren und neue Funktionen schneller bereitstellen [Quelle: [Netzwoche](https://www.netzwoche.ch/news/2019-06-25/die-vorteile-von-devops-in-der-praxis)].

2. **Verbesserte Zusammenarbeit**:
   DevOps fördert die Zusammenarbeit und Kommunikation zwischen Entwicklungs- und Betriebsteams, was zu einer effizienteren Problemlösung und besserem Wissensaustausch führt [Quelle: [ComputerWeekly](https://www.computerweekly.com/de/tipp/Waegen-Sie-die-Vor-und-Nachteile-von-DevOps-sorgfaeltig-ab)].

3. **Höhere Qualität und Zuverlässigkeit**:
   Durch kontinuierliche Integration und kontinuierliches Testen wird die Qualität der Software verbessert. Probleme können frühzeitig erkannt und behoben werden, was zu einer höheren Zuverlässigkeit führt [Quelle: [Synopsys](https://www.synopsys.com/blogs/software-security/agile-cicd-devops-difference/)].

4. **Reduzierte Ausfallzeiten**:
   Durch die Automatisierung von Überwachungs- und Alarmierungssystemen können Probleme schneller erkannt und behoben werden, bevor sie zu größeren Ausfällen führen [Quelle: [Azure](https://azure.microsoft.com/en-us/resources/cloud-computing-dictionary/what-is-devops/)].

**Welche Probleme werden mit DevOps gelöst?**

1. **Silodenken**:
   DevOps löst das Problem des Silodenkens, indem es die Zusammenarbeit zwischen den Teams fördert. Dadurch wird verhindert, dass Entwicklungs- und Betriebsteams isoliert voneinander arbeiten, was oft zu Kommunikationsproblemen und ineffizienten Prozessen führt [Quelle: [Netzwoche](https://www.netzwoche.ch/news/2019-06-25/die-vorteile-von-devops-in-der-praxis)].

2. **Langsame und fehleranfällige Bereitstellungen**:
   Durch die Automatisierung der Deployment-Pipelines werden Bereitstellungen schneller und weniger fehleranfällig. Manuelle Prozesse, die anfällig für Fehler sind, werden durch automatisierte, wiederholbare Prozesse ersetzt [Quelle: [Synopsys](https://www.synopsys.com/blogs/software-security/agile-cicd-devops-difference/)].

3. **Unzureichende Skalierbarkeit**:
   DevOps-Methoden und -Tools ermöglichen es Unternehmen, ihre Infrastruktur effizient zu skalieren und auf veränderte Anforderungen zu reagieren. Dies ist besonders wichtig in einer Cloud-Umgebung, in der die Fähigkeit zur schnellen Skalierung entscheidend ist [Quelle: [ComputerWeekly](https://www.computerweekly.com/de/tipp/Waegen-Sie-die-Vor-und-Nachteile-von-DevOps-sorgfaeltig-ab)].

4. **Langsame Fehlerbehebung**:
   Durch die kontinuierliche Überwachung und das schnelle Feedback können Probleme schneller erkannt und behoben werden. Dies reduziert die Zeit, die benötigt wird, um auf Fehler zu reagieren, und verbessert die Gesamtzuverlässigkeit der Systeme [Quelle: [Azure](https://azure.microsoft.com/en-us/resources/cloud-computing-dictionary/what-is-devops/)].
 
   *Christoph Knuchel, Jason Termine - 22.06.2024*