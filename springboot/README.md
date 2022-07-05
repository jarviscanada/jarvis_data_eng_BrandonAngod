# Introduction
- Explain business context briefly (see the kick-off ticket)
- Explain what does your project does? (e.g. manage clients and accounts, execute security orders, etc..)
- Discuss technologies you used

# Quick Start
- Prequiresites: Docker, CentOS 7
- Docker scripts with description
	- build images
  - create a docker network
  - start containers
- Try trading-app with SwaggerUI (screenshot)

# Implemenation
## Architecture
- Draw a component diagram that contains controllers, services, DAOs, SQL, IEX Cloud, WebServlet/Tomcat, HTTP client, and SpringBoot. (you must create your own diagram)
- briefly explain the following components and services (3-5 sentences for each)
  - Controller layer (e.g. handles user requests....)
  - Service layer
  - DAO layer
  - SpringBoot: webservlet/TomCat and IoC
  - PSQL and IEX

## REST API Usage
### Swagger
Swagger is an open sourced API development tool. We will utilize Swagger to create our API as it will make the proccess simply and effectively.
### Quote Controller
- High-level description for this controller. Where is market data coming from (IEX) and how did you cache the quote data (PSQL). Briefly talk about data from within your app
- briefly explain each endpoint
  e.g.
  - GET `/quote/dailyList`: list all securities that are available to trading in this trading system blah..blah..
### Trader Controller
- Creates and manages trader information
- Delete Traders
- Find Traders according to ID
### Order Controller
- High-level description for this controller.
- briefly explain each endpoint
### App controller
- briefly explain each endpoint

# Test 
Tested using postman and junit. Junit handled all the unit tests and Postman handled tetsting the main functions of the program.

# Deployment
- docker diagram including images, containers, network, and docker hub
e.g. https://www.notion.so/jarviscanada/Dockerize-Trading-App-fc8c8f4167ad46089099fd0d31e3855d#6f8912f9438e4e61b91fe57f8ef896e0
- describe each image in details (e.g. how psql initialize tables)

# Improvements
If you have more time, what would you improve?
- at least 3 improvements