# Branching-Strategien für ein Software-Projekt

## Recherchierte Branching-Strategien
- **Git Flow**
- **GitHub Flow**
- **Feature Branching**
- **Trunk-based Development**

## Vergleich der Branching-Strategien

| Strategie               | Beschreibung                                                                 | Vorteile                                                                                             | Nachteile                                                                                          | Anwendungsbeispiele                       |
|-------------------------|-----------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|------------------------------------------|
| Git Flow                | Detaillierte Strategie mit mehreren Branches (main, develop, feature, release, hotfix).  | Klare Trennung von Entwicklungs- und Produktivzweigen, gut für größere Projekte.                     | Komplexität, Overhead durch viele Branches.                                                       | Große, komplexe Projekte mit festen Release-Zyklen. |
| GitHub Flow             | Einfache Strategie mit einem main Branch und feature Branches.               | Einfachheit, schnelleres Deployment, gut für Continuous Deployment.                                 | Nicht geeignet für Projekte mit langen Entwicklungszyklen.                                         | Projekte, die kontinuierlich ausgeliefert werden.  |
| Feature Branching       | Jeder neue Feature wird in einem separaten Branch entwickelt.                | Isolierung von Features, einfache Code-Reviews.                                                      | Kann zu vielen langen lebenden Branches führen, Merge-Konflikte.                                  | Projekte mit klar definierten, voneinander getrennten Features. |
| Trunk-based Development | Entwickler integrieren kleine Änderungen häufig direkt in den main Branch.   | Weniger Merge-Konflikte, kontinuierliche Integration, schnelleres Feedback.                          | Erfordert strenge CI/CD-Pipelines, höhere Anforderungen an Tests und Code-Qualität.               | Projekte mit hoher Änderungsgeschwindigkeit, wo kontinuierliche Integration notwendig ist. |

## Auswahl einer Branching-Strategie
**Ausgewählte Strategie: GitHub Flow**

**Begründung:**
- **Einfachheit:** Die einfache Struktur erleichtert es, den Überblick zu behalten und vermeidet unnötigen Overhead.
- **Continuous Deployment:** Die Strategie ermöglicht häufige Releases und ist ideal für Projekte, die kontinuierlich ausgeliefert werden.
- **Teamgröße und -erfahrung:** Unser Team ist klein bis mittelgroß und die meisten Teammitglieder sind mit dieser Methode vertraut, was die Einarbeitungszeit verkürzt.

## Analyse des bestehenden Projekts

**Anforderungen für die Anwendung der GitHub Flow Strategie:**
- **Häufige kleine Releases:** Unser Projekt hat eine hohe Änderungsfrequenz, was gut zu GitHub Flow passt.
- **Code-Review-Prozess:** Wir benötigen ein effizientes System für Code-Reviews und das Zusammenführen von Branches.
- **Automatisierte Tests:** Um die Stabilität zu gewährleisten, müssen wir eine solide CI/CD-Pipeline implementieren.

## Umsetzung der GitHub Flow Strategie im bestehenden Projekt

1. **Branch-Erstellung:**
   - **main:** Der stabile Produktions-Branch.
   - **feature branches:** Für jedes neue Feature oder jede Änderung wird ein separater Branch erstellt, z.B. `feature/feature-name`.

2. **Workflow-Regeln:**
   - **Branch-Namen:** Einheitliche Namenskonventionen für feature branches.
   - **Pull Requests (PRs):** Jede Änderung wird über PRs in den main Branch gemerged.
   - **Code-Reviews:** Mindestens ein Kollege muss den Code prüfen und freigeben, bevor ein PR gemerged wird.
   - **Automatisierte Tests:** Jeder PR löst automatisierte Tests aus, die bestanden werden müssen, bevor der Merge erfolgt.

3. **Praktische Durchführung:**
   - Branches werden gemäß den festgelegten Regeln erstellt und bearbeitet.
   - Code-Reviews und Tests werden konsequent durchgeführt.
   - Erfolgreiche PRs werden in den main Branch gemerged und sofort deployed.

## Dokumentation der durchgeführten Arbeiten


### Erstellen eines Pull Requests
Die Pull Request erstellen wir über die GitHub-Oberfläche. Hier können wir die Änderungen noch einmal überprüfen und sicherstellen, dass die CI/CD-Pipelines erfolgreich durchlaufen wurden.

Hier sind wir mit folgenden Schritten vorgegangen:
1. **Branch auswählen:** Wir wählen den Branch aus, in den die Änderungen gemerged werden sollen.
![Branch auswählen](resources/PR_Step1.png)

2. **Pull Request erstellen:** Hierzu klicken wir auf den Button "New pull request".
![Pull Request erstellen](resources/PR_Step2.png)

3. **Überprüfung der Änderungen:** Wir überprüfen die Änderungen und stellen sicher, dass die CI/CD-Pipelines erfolgreich durchlaufen wurden.
![Änderungen überprüfen](resources/PR_Step3.png)
![CI/CD-Ergebnisse](resources/PR_Step4.png)

4. **Pull Request erstellen:** Wir erstellen den Pull Request und fügen eine Beschreibung hinzu, um die Änderungen zu erläutern.
![Pull Request erstellen](resources/PR_Step5.png)

5. ** Übersicht über den Pull Request:** Hier können wir den Status des Pull Requests überprüfen und ggf. weitere Änderungen vornehmen. Hier werden auch die Testergebnisse angezeigt.
![Pull Request Übersicht](resources/PR_Step6.png)

6. ** Warten auf Review:** Ein Kollege überprüft die Änderungen und gibt das OK, um den Pull Request zu mergen.
![Pull Request Review](resources/PR_Step7.png)

7. ** Pull Request mergen:** Nachdem der Pull Request freigegeben wurde, können wir ihn in den main Branch mergen. Hierzu verwenden wir den Button "Merge pull request". Wir wählen die Option "Squash and merge", um die Änderungen in einem Commit zusammenzufassen.
![Pull Request mergen](resources/PR_Step8.png)

8. ** Zusammenfassung:** Nach dem Mergen des Pull Requests erhalten wir eine Zusammenfassung der Änderungen und können den Erfolg des Merges überprüfen.
![Pull Request Zusammenfassung](resources/PR_Step9.png)

9. ** Löschen des Branches:** Nachdem der Pull Request gemerged wurde, können wir den Branch löschen, um die Übersichtlichkeit zu wahren.
![Branch löschen](resources/PR_Step10.png)

## Literatur
- [Git Branching Strategies](https://www.atlassian.com/git/tutorials/comparing-workflows)
- [GitHub Flow](https://guides.github.com/introduction/flow/)
- [GitLab Flow](https://docs.gitlab.com/ee/topics/gitlab_flow.html)
