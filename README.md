# Financial Insights with ElasticSearch
Using Amazon Alexa advanced queries about your transactional history can be served via ElasticSearch.

# The Call Stack
Using `api.ai` a variety of UI can be used to call our backend. We choose Amazon Alexa. Our Alexa skill would interact with a simple Python backend served on an AWS Lambda. This fronted our core API as a SSL authenticated service (which is required for Amazon Alexa skills) and converted `utterances` to  `Intents`  and `Entities`. This Python script delegated these calls to our Spring boot application.

<p align="center">
    <img src="https://raw.githubusercontent.com/alexbrjo/SquirrelFinancial/master/stack.png" alt="API stack"/>
</p>

Our core application was a Spring Boot backend running on a 64GB AWS EC2 instance. A single cluster ElasticSearch was also run on the same EC2 instance. From `Intents`  the backend would extract and transform the correct information to query ElasticSearch for matching transactions (`hits`). The Google maps Geocoding API was used to convert locations to latitude/longitude coordinates for ElasticSearch. ElasticSearch was populated with transaction data using the Capital One Nessie API.

# The questions you could ask
* What was my spending between july 1 2017 and july 8 2017 in Arlington on food?
* What was the distribution of my spending from August 4 2017 to August 18, 2017?
* How much will i spend on uber during next week?

# Setting up the backend
Unfortunately the frontend python script to handle Alexa utterances were lost, so only the backend can be built.
```
nohup ./mvnw spring-boot:run > ~/spring.out &
ps -Af | grep spring
```
