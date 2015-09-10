## Group 1: Ascher,Lamb,Kamper,Haberl,Kranabetter,Reitner,Seidl ##
### General issues ###
| **To Do** | **Status** | **Responsible person** | **Story points** |
|:----------|:-----------|:-----------------------|:-----------------|
|Fix document upload|done        |Ascher                  |3                 |
|Fix document download|done        |Ascher                  |3                 |
|Maven environment|done        |Kranabetter             |2                 |

### Main-Page ###
| **To Do** | **Status** | **Responsible person** | **Story points** |
|:----------|:-----------|:-----------------------|:-----------------|
|Add document library to main page|done        |Haberl                  |2                 |
|Add news to main page|done        |Haberl                  |2                 |

### Refactoring ###
| **To Do** | **Status** | **Responsible person** | **Story points** |
|:----------|:-----------|:-----------------------|:-----------------|
|Logging    |done        |Kamper                  |2                 |
|Exception handling|done        |Kamper                  |2                 |
|Document service including unit test|to do       |Lamb, Reitner           |4                 |
|Implement hashcodes and Equals|to do       |Lamb, Reitner           |1                 |
|Unify naming of variables and classes etc.|to do       |Lamb, Reitner           |1                 |
|Remove representation layer code from service|to do (clarify)|Lamb,Kamper,Reitner     |3                 |

### Selenium tests ###
| **To Do** | **Status** | **Responsible person** | **Story points** |
|:----------|:-----------|:-----------------------|:-----------------|
|Document upload|to do       |Ascher, Haberl          |4                 |
|Document download|to do       |Ascher, Haberl          |3                 |
|News feed for user|to do       |Ascher, Haberl          |3                 |

## Group 2: Fritz, Hagelmüller, Huber, Marcher, Palade, Schuberth und Spörk ##
### User-Page ###

|Selenium tests for User-Pages|To Do|Sandra Marcher|2|
|:----------------------------|:----|:-------------|:|
|Email-address are not changeable on User-Pages|To Do|Michael Spörk |1|
|Information of validation directly on input field|To Do|Michael Spörk |1|

### Login ###
|Login old/new merging|To Do|Ionut Palade|1|
|:--------------------|:----|:-----------|:|
|Selenium tests for Login-Page|To Do|Ionut Palade|2|

### Refactoring ###
|UserService RemoteFaced tests|To Do|Michael Spörk|4|
|:----------------------------|:----|:------------|:|
|UserDAO tests                |To Do|Jörg Huber   |5|
|UserService tests            |To Do|Schuberth Jürgen|3|
|Delete controller logic from Service-Layer|To Do|Schuberth Jürgen|1|
|Logging                      |To Do|Steven Hagelmüller|2|
|Delete comment out code      |To Do|Sandra Marcher|2|
|Check Enitity                |To Do|Eva Fritz    |2|
|Delete DAO-Logic from Service-Layer|To Do|Steven Hagelmüller|2|
|Update DAO method names      |To Do|Eva Fritz    |2|
|Exception Handling           |To Do|Ionut Palade |3|

## Group 3: Frech, Hösele, Kotremba, Mayer, Tot, Viehberger ##

### REFACTORING ###

|Task/Story|Status|Assigned To|SP|
|:---------|:-----|:----------|:-|
|Refactoring CommunityService (Architecture)|Done  |Michael Mayer|3 |
|Admin/PortalAdmin validation in presentation layer|Done  |Michael Mayer|2 |
|Refactoring NewsService (Architecture)|Done  |Michael Mayer|2 |

### IMPLEMENTATIONS ###

|Task/Story|Status|Assigned To|SP|
|:---------|:-----|:----------|:-|
|US5.1 Einfacher Upload für Dateien / Fertigstellung 1)|done  |Achim Kotremba|5 |
|Remote Facade CommunityService/NewsService|Done  |Michael Mayer|1 |
|Logging   |done  |Bojan Tot  |2 |
|Überarbeitung UI|done  |Daniel Frech|2 |

### TESTS ###
|Task/Story|Status|Assigned To|SP|
|:---------|:-----|:----------|:-|
|Unit Tests CommunityService|done  |Julia Viehberger|3 |
|Unit Tests NewsService|done  |Bojan Tot  |3 |
|Blackbox Test CommunityService (In-Container)|Done  |Michael Mayer|2 |
|Blackbox Test NewsService (In-Container)|Done  |Michael Mayer|2 |
|1 Selenium Test for US5.1|done  |Julia Viehberger|2 |
|1 Selenium Test for US26|done  |Daniel Hösele|2 |
|1 Selenium Test for US25|TODO  |Daniel Frech|2 |

## Group 4: Dulzky, Lindlbauer, Mitteregger, Temmel, Siharath, Wendl ##

### Refactoring ###
abgeschlossen

### Abstimmung mit anderen Teams ###
**Service-Layer in den Domain-Layer hochziehen: ja/nein** utils-package (HashUtil.java und Ressources.java) aus dem Domain-Layer in eine eigene Hierarchie: ja/nein
**Wie wird mit den gemeinsamen noch offenen issue bzgl. gemeinsame Exception-Fang-Ebene auf oberster Business-Ebene an damit unser System resilient ist?**

### Offene Punkte ###
#### 2. Functional Tests/In-Container Tests ####
Vorbereitungen erfolgt, Umsetzung der Happy-Path-Tests: in progress
Abschätzung: 8 Stunden

#### 3. Component Tests/DB-Tests: ####
work in progress
Probleme mit Connection
Abschätzung: 5 Stunden

#### 4. Unit-Tests ####
ost-Entity abgeschlossen
Abschätzung: 10 Stunden

### Zeitaufwand bisher ###

Di, 16.12., 20:30 - 22:15, Beginn Functional Tests,
Mi, 17.12., 20:30 - 22:30, Functional Tests
Do, 18.12., 20:30 - 22:30, Functional Tests
So, 21.12., 20:00 - 22:00, Functional Tests
Mo, 29.12., 20:00 - 21:30, Functional Tests
Di, 30.12., 19:00 - 22:00, Functional Tests
Sa, 03.01., 18:00 - 21:00, Functional Test => schauen, wie die Tests auszuführen sind
So, 04.01., 20:00 - 22:15, Kompoenten-Test
Mo, 05.01., 20:00 - 22:00, Administration + Reparieren nach Refactoring von Gruppe 1 (1 Stunde), Komponenten-Test
Di, 06.01., 20:00 - 22:45 Unit-Test für Post-Entity
So, 04.01., 20:00 - 23:00, Unit-Test Post-Entity abgeschlossen

Summe - bisheriger Aufwand: 17,5 Stunden

Summe - geschätzter offener Aufwand: 23 Stunden