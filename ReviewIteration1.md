# 1. Repository Structure #

---


- Maven Repo .m2 eingecheckt für settings.xml
- src direkt eingecheckt - ohne Projektnamen?
> => was ist wenn man mehrere Module braucht?
- pom.xml direkt eingecheckt

**=> Solved ([r316](https://code.google.com/p/fhj-ws2014-sd12-pse/source/detail?r=316) - [r330](https://code.google.com/p/fhj-ws2014-sd12-pse/source/detail?r=330))**


# 2. Architecture #

---

- Package naming => Layers

login.xhtml --> LoginController --> UserService------> UserDAO
> @ManagedBean        @RequestScoped
> @Stateless          em.persist(user);

**=> Solved ([r316](https://code.google.com/p/fhj-ws2014-sd12-pse/source/detail?r=316) - [r330](https://code.google.com/p/fhj-ws2014-sd12-pse/source/detail?r=330))**
TODO: Check package for domain layer if it contains the right classes!


## Entities ##
- redundante id, hashCode, equals
- hashCode, equals nicht von allen implementiert
> zB:
> @Entity
> public class Document {
> > @Id
> > private String name;


> public String getName() {
> > return this.name;

> }

> public void setName(String name) {
> > this.name = name;

> }
> }
- Mapping Annotations vereinheiltlichen:
> @Id
> @TableGenerator(name = TABLE\_GEN\_NAME,
> > table = TableGeneratorHelper.TABLE\_NAME,
> > pkColumnName = TableGeneratorHelper.PK\_COL\_NAME,
> > pkColumnValue = PK\_COL\_VALUE,
> > valueColumnName = TableGeneratorHelper.VALUE\_COL\_NAME,
> > allocationSize = 1)

> @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE\_GEN\_NAME)

> @Id
> private String name;

> @Id
> @GeneratedValue(strategy = GenerationType.IDENTITY)
> private long id;



## DAO ##
- Methodennamen sehr an Hibernate angelehnt, besser wäre insert(), update(), delete(), find**()
- throw new RuntimeException()
- Logging!!!**

- genau!
> } catch(Exception e) {
> > // should be DAOException in future releases.
> > throw e;

> }

## Service ##
- EntityManager auch im Service verwendet

- throw new RuntimeException()

- zT kein Exception Handling

- Logging!!!

- CommunityServiceImpl enthält Presentation Layer Code:
> import javax.faces.context.ExternalContext;
> import javax.faces.context.FacesContext;
> > import at.fhj.swd.controller.Helpers.CookieHelper;

> DAO.create() Methode mit Parametern wäre besser
> Community community = new Community();
> community.setName(name);
> community.setVisible(visible);
> communityDao.createCommunity(community);


- DocumentServiceImpl enthält Presentation Layer Code:
> import javax.faces.bean.ApplicationScoped;
> import javax.faces.bean.ManagedBean;

> sollten das Konstanten sein?
> private static String globalContext = "global";
> private static String communityContext = "community";
> private static String userContext = "user";
> => final

- LazyCommunityImpl enthält Presentation Layer Code:
> import org.primefaces.model.LazyDataModel;
> import org.primefaces.model.SortOrder;

- NewsServiceImpl enthält Presentation Layer Code:
> import at.fhj.swd.controller.Helpers.CookieHelper;
> Constructor oder DAO.create() mit Parametern statt:
> > News newNews = new News();
> > newNews.setTitle(title);
> > newNews.setContent(content);
> > newNews.setStartdate(startdate);
> > newNews.setVisible(visible);

> => Einzelne Attribute werden leicht vergessen!!

- Was ist das???:
> @ManagedBean(name="topicService")
> @ViewScoped
> public class TopicServiceImpl implements TopicService{
> @Inject
> private TopicDAO topicDao;
> @Inject
> private CommunityDao communityDao;

- UserServiceImpl entält JPA Code
> import javax.persistence.EntityManager;
> > import javax.persistence.PersistenceContext;

## MVC ##
- ManahedBeans => Trennung von view und controller wird nicht halten
- Logging statt System.out.println("create");



## Misc ##
- Auskommentierter Code zB: UserServiceImpl
- quickstart Beispiel als eigenes Projekt einchecken
- Coding Style

> foo() {
> }
> vs.
> foo()
> {
> }

> private String _username;
> private String_password;1. Repository Structure