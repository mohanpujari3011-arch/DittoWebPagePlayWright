Ditto Playwright Automation Framework (Java)

This project is a Playwright (Java) automation framework designed to automate the Ditto Insurance Web Journey.
It follows the Page Object Model (POM) structure, includes a ThreadLocal browser factory, and uses Apache POI for Excel reporting.

📁 Project Structure
DittoPlayWrightProject
│
├── src/main/java
│   ├── Utils
│   │   ├── ObjectFactory.java
│   │   ├── DataReporting.java
│   │
│   ├── Pages
│       ├── HomePage.java
│       ├── MembershipPage.java
│       ├── PremiumPage.java
│
└── src/test/java
    └── MainTest
        └── TestExecution.java

🚀 Key Features
✔ Playwright Automation (Java)

Fast, reliable browser automation

Chromium engine

Custom timeouts, locators & waits

✔ ThreadLocal Browser Factory

ObjectFactory.java manages:

Playwright instance

Browser instance

BrowserContext

Page object

Benefits:

Thread-safe execution

Clean test initialization

Simple teardown

✔ Page Object Model (POM)
Page	Responsibilities
HomePage	Handles initial navigation, Next/Continue buttons
MembershipPage	Reads member options, writes to Excel, validates gender options, fills age/pincode
PremiumPage	Extracts premium details, coverage amounts, add-ons, writes to Excel
📊 Excel Reporting (Apache POI)

DataReporting.java provides:

writecell(row, col, value)

Automatic row/cell creation

Runtime Excel update saving

Used to store dynamic data such as:

Member count

Member list

Gender options

Coverage amounts

Recommended add-ons

🧪 Test Execution Workflow

TestExecution.java orchestrates the entire run:

ObjectFactory factory = new ObjectFactory();
Page page = factory.page();

page.setDefaultTimeout(120000);
page.navigate("https://stag-app.joinditto.in/fq");

DataReporting excel = new DataReporting("path/to/Excel.xlsx", "Sheet1");

HomePage hpage = new HomePage(page);
hpage.Activeone();

MembershipPage mpage = new MembershipPage(page, excel);
mpage.Membersvalidating("25", "515122");

PremiumPage pmp = new PremiumPage(page, excel);
pmp.Premiumvalidation();

factory.teardown();

🛠 Prerequisites
Requirement	Version
Java	11 or above
Maven	Latest
Playwright for Java	1.45+
Apache POI	5.2+
IDE	Eclipse / IntelliJ
📦 Maven Dependencies
<dependency>
  <groupId>com.microsoft.playwright</groupId>
  <artifactId>playwright</artifactId>
  <version>1.45.0</version>
</dependency>

<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>5.2.4</version>
</dependency>

▶️ How to Run
Run from IDE

Open project in Eclipse/IntelliJ

Go to:
src/test/java/MainTest/TestExecution.java

Run as Java Application

📘 Future Enhancements

Add TestNG/JUnit framework

Add HTML reports (Extent / Allure)

Introduce data-driven testing from Excel

Add CI/CD with Jenkins or GitHub Actions

Implement logging via Log4j2

📝 Author

Mohan Pujari
Automation Engineer | Playwright | Java | Framework Development
