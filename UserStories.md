**Allgemeine Anmerkungen:
  * Bitte die Userstories durchnummerieren nach der Uerbarbeitung. Einfach machen durch (USx) usw. am Beginn der Userstory
  * Bitte doppelte User Stories vereinheitlichen
  * Sonst siehe Detailkommentare**

# Stories Unzugeordnet #

---


**Login**

**(US1.1) Als User will ich mit echten verifizierten Personen auf der Plattform interagieren.**

Acceptance:
  * Bei der Registrierung muss die angegebene E-Mail Adresse verifiziert werden
  * Die Anmeldung ist nur nach der Bestätigung der E-Mail erfolgreich
  * Bei der Anmeldung muss Text von einem Bild eingegeben werden, um sicherzustellen, das sich eine Person Registriert.

**Startseite:**

**(US2) Als User möchte ich auf der Startseite des Web-Portals alle globalen und lokalen Nachrichten (deren Community man angehört) des Activity Streams sehen können, um die aktuellen Nachrichten lesen zu können.**

Acceptance:
  * Der User sieht alle Activity Streams der globalen Communities.

**(US3) Als User möchte ich im Startseiten-Stream Zugriff auf den den Community-Stream haben.**

Acceptance:
  * Sicherstellen, dass die Inhalte des Community-Streams im Startseiten-Stream sichtbar sind

**Anmerkungen CKrenn:**
  * Acceptance - Was fehlt: User sieht nur Inhalte die er sehen darf (keine privaten Communities ausser er ist Member) zusammenführen mit vorheriger Community.

**Newsfeed:**

**Anmerkungen CKrenn zum Feed generell:**
  * Hier geht meist nicht ganz hervor, wo ich was darf (im zentralen Feed, oder in der Community. bitte noch detaillieren und ggf verschieben.

**(US8) Wenn ich als User angemeldet bin, möchte ich den Newsfeed mit den aktuellen Unternehmensinformationen sehen.**

Acceptance:
  * Der Newsfeed beinhaltet folgende Informationen:
  * Gruppenrelevante Aktivitäten
  * eigene Beiträge
  * Beiträge von Freunden
  * öffentliche Beiträge
  * Beiträge, die mir gefallen, möchte ich mit einem Like versehen können
  * bei für mich interessanten Beiträgen möchte ich kommentieren können
  * ich möchte entscheiden können, wer meinen Beitrag sehen kann (Freunde, Gruppe, öffentlich)

**Anmerkungen CKrenn:**
  * Gehört das zur Startseite? dann bitte dorthin schieben
  * Was ist mit entscheiden gemeint @tagging fuer User, Community beim Erstellen?

**(US9) Als User möchte ich eine Nachricht auf den Activity Stream schreiben können.**

Acceptance:
  * Sicherstellen das die Nachricht auch editiert/ausgebessert werden kann.
  * Sicherstellen das die Nachricht auch wieder gelöscht werden kann.
  * Sicherstellen das die Nachricht von einem Admin / Portal Admin gelöscht werden kann.

**(US10) Als User möchte ich Nachrichten/Kommentare auf dem Activity Stream liken können.**

Acceptance:
  * Sicherstellen das die Nachricht/der Kommentar auch wieder entliked werden kann.

**(US11) Als User möchte ich zu Nachrichten auf dem Activity Stream Kommentare schreiben können.**

Acceptance:
  * Sicherstellen das der Kommentar auch editiert/ausgebessert werden kann.
  * Sicherstellen das der Kommentar auch wieder gelöscht werden kann.
  * Sicherstellen das der Kommentar von einem Admin / Portal Admin gelöscht werden kann.

**(US15) Als User möchte ich im Activity-Stream private Nachrichten an andere User schicken können, um nur mit bestimmten Nutzern kommunizieren zu können.**

Acceptance:
  * Durch Hinzufügen der Email-Adresse als @Tag wird der Empfänger der Nachricht bestimmt.

**Anmerkungen CKrenn:**
  * Acceptance: als @Tag ergänzt

**Userinformation (Profilseite, Userseite)**

**Anmerkungen CKrenn:**
  * Sollte eigentlich Profilseite sein, hab ich ergänzt.

**Communities:**

**Anmerkungen CKrenn:**
  * Mir fehlt generell eine Userstory zum Beantragen einer Community. Bitte ergänzen
  * Mir fehlt eine User Story, die beschreibt wie eine public / private Community aufgebaut ist. Bitte ergänzen
  * Mir fehlt eine User Story, die beschreibt wie man Mitglied einer öffentlichen / privaten Community wird. Bitte ergänzen

**(US28) Als User möchte ich Zugriff auf eine Übersichtsseite haben**

Acceptance:
  * Sicherstellen, dass auf der Übersichtsseite alle öffentlichen und privaten Communities angezeigt werden.
  * Sicherstellen, dass auf der Übersichtsseite die Communities eines bestimmten Users angezeigt werden.
  * Sicherstellen, dass man mit einem "Zurück zur Übersicht"-Button auf die Übersichtsseite zurückkehren kann.

# Stories Iteration 1 #


---

## Gruppe 1 ##

### Dokumenten Bibliothek ###

**(US4) Als User möchte ich die aktuellen Dokumente angezeigt bekommen**

Acceptance:
  * Nach Erstellung / Änderung sortiert
  * Über direktlink abrufbar

**(US6) Als Administrator möchte ich auf der Startseite Dateien zur Dokumentenbibliothek hinzufügen oder löschen können, damit die anderen diese lesen bzw. herunterladen können.**

Acceptance:
  * Ein Bereich auf der Startseite ist sichtbar, in dem ich die Liste der bereits verfügbaren Dokumente sehe
  * Bei diesem Bereich befindet sich eine Schaltfläche, der einen Dialog öffnet, um eine Datei zu wählen, die hinaufgeladen werden soll.
  * Jeder Listeneintrag besitzt eine Schaltfläche, mit der dieser bei Klick gelöscht wird

**(US7) Als Benutzer möchte ich auf der Startseite Dateien aus der Dokumentenbibliothek herunterladen können, damit ich diese auf meinem Rechner lesen kann.**

Acceptance:
  * Ein Bereich auf der Startseite ist sichtbar, in dem ich die Liste der bereits verfügbaren Dokumente sehe
  * Bei Klick auf ein Element in der Liste wird dieses heruntergeladen

### Newsfeed ###

**(US5) Als User möchte ich die aktuellen News des Unternehmens angezeigt bekommen.**

Acceptance:
  * Nach aktualität sortiert

Abhängigkeit: US5.1 Gruppe 3


---

## Gruppe 2 ##

**Userseite und Userinformation**

**(US1) Als ein beim Portal bereits registrierter User möchte ich mich mit Benutzernamen und Passwort auf meine personalisierte Startseite einloggen können.**

**Storypoints: 8**

Acceptance:
  * erfolgreicher Login
  * personalisierter Datenstream wird angezeigt
  * mein Name wird angezeigt
  * ein Logout-Button ist verfügbar
  * mein Profilbild wird angezeigt
  * über einen Link werde ich zu meinen Account-Informationen weitergeleitet
  * mit falschen Login Daten ist ein Login nicht möglich

**(US16) Als User möchte ich meine eigene Userseite bearbeiten können, um die eigenen persönlichen Daten aktuell zu halten.**


**Storypoints: 13**

Acceptance:
  * Die Profildaten müssen aus den Feldern E-Mail-Adresse und Telefonnummer bestehen.
  * Speichern der Daten in der Datenbank bei Bestätigung
  * Validierung der eingegebenen Daten

**(US20) Als User möchte ich Statusinformationen (online/offline, intern/extern) meiner Freunde sehen.**

**Storypoints: 5**

Acceptance:
  * Der Online-Status wird mit einem grünen Punkt neben dem Namen markiert.
  * Der Offline-Status wird mit einem roten Punkt neben dem Namen markiert.
  * Der externe Mitarbeiter wird durch den Buchstaben "E" neben dem Namen gekennzeichnet.



---

## Gruppe 3 ##

### Verwaltungsbereich ###

**(US25) Als Admin möchte ich im Adminbereich Communities anlegen und freischalten, um User mit gleichen Interessen eine Gruppe erstellen zu können.**

**Storypoints: 5**

Acceptance:
  * Der Adminbereich soll nur für Admins erreichbar sein.
  * Neue Communities sollen nicht freigeschaltet sein
  * Die Freischaltung soll durch den Admin wiederrufbar sein.

**(US26) Als Portal-Admin möchte ich im Adminbereich einen News-Eintrages editieren können.**

**Storypoints: 8**

Acceptance:
  * News Einträge sollen durch den Administrator bearbeitbar sein.

### Verwaltungsbereich (Administratoren) ###

**(US27) Als Portal-Administrator möchte ich neue hochgeladene Fotos am User Profil vor der Veröffentlichung freischalten.**

->> auf Iteration 2 verschoben

**Storypoints: 5**

Acceptance:
  * Ein neu hochgeladenes Foto wird auf der User Seite nicht angezeigt.
  * Der Admin Bereich bietet die Möglichkeit neu hochgeladene Fotos freizuschalten.
  * Fotos sind nach der Freischaltung für alle User sichtbar.

Abhängigkeit: US16, Gruppe 2

**(US5.1) Als Admin möchte ich neue News erstellen / editieren können**

**Storypoints: 6**

Acceptance:
  * Schnelles / einfaches erstellen neuer Neuigkeiten
  * Einfacher Upload für Dateien (Bilder) --> Iteration 2


---

## Gruppe 4 ##

https://code.google.com/p/fhj-ws2014-sd12-pse/w/edit/UserStories_G4


---




---

# Stories Iteration 2 #


---


## Gruppe 2 ##

**(US18) Als User möchte ich mein Profilbild zur meiner Userseite hochladen können.**

**Storypoints: 13**

Acceptance:
  * Der Upload erfolgt über einen Auswahldialog, der auf das Filesystemzugreift
  * Erfolg bzw. Misserfolg wird dem User über einen Feedback-Dialog angezeigt

**(US19) Als User möchte ich mein Profilbild aus dem Portal löschen können.**

**Storypoints: 3**

Acceptance:
  * Ein ausgewähltes Foto kann durch Klicken auf den Löschen-Button entfernt werden.
  * Das gelöschte Profilfoto wird nicht mehr angezeigt.

**(US21) Als User möchte ich einen neuen Kontakt zu meinen persönlichen/privaten Kontakten hinzufügen können.**

**Storypoints: 5**

Acceptance:
  * Auswahl aus der Liste aller verfügbaren User
  * Keine Bestätigung beim hinzufügen, sondern direktes hinzufügen
  * Jedes Profil enthält ein „Hinzufügen“-Button um zu einer Kontaktliste aufgenommen zu werden. (Konflikt)

**(US22) Als User möchte ich einen oder mehrere bestehende Kontakte entfernen können**

**Storypoints: 5**

Acceptance:
  * Möglichkeit zur Auswahl eines oder mehrerer Kontakte
  * Vor dem endgültigen Löschen wird eine positive Bestätigung des Users verlangt.
  * Ohne Bestätigung wird der Kontakt nicht gelöscht.



## Ende Gruppe 2 ##


---



**(US29) Als User möchte ich in der Lage sein, die Übersichtsseite zu personalisieren.**

Acceptance:
  * Sicherstellen, dass nur die eigenen sowie alle öffentlichen Communities angezeigt werden
  * Sicherstellen, dass die Inhalte der Übersichtsseite nach relevante Kriterien sortiert werden können
  * Sicherstellen, dass die Inhalte des Community-Streams nach Datum sortiert werden können

## Services ##

**(US34) Als Drittanbieter möchte ich eine Schnittstelle zur Zeiterfassung über SOAP.**

Acceptance:
  * Wochenarbeitszeit pro User wird übertragen
  * Monatsarbeitszeit pro User wird übertragen
  * Error Information bei falscher UserID wird übertragen