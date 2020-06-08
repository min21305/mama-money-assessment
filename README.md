# Money Transfer Assessment

## Steps to run application
* make git clone of the master repository
* open command prompt and point to this repository folder
* run: mvn clean install
* run: mvn spring-boot:run
* open http://localhost:8080/swagger-ui.html url
* run service

## Request and Response Details

* Request_1
```json
{
  "msisdn": "123456789",
  "sessionId": "#BB123456"
}
```
* Response_1
```json
{
  "sessionId": "#BB123456",
  "message": "Welcome to Mama Money! Where you would like to send money today? 1) Kenya 2) Malawi"
}
```

* Request_2
```json
{
  "msisdn": "123456789",
  "sessionId": "#BB123456",
  "userEntry": "1"
}
```
* Response_2
```json
{
  "sessionId": "#BB123456",
  "message": "How much money (in Rands) would you like to send to Kenya"
}
```

* Request_3
```json
{
  "msisdn": "123456789",
  "sessionId": "#BB123456",
  "userEntry": "100"
}
```
* Response_3
```json
{
  "sessionId": "#BB123456",
  "message": "Your person you are sending to will receive: 610.0 KES 1) Ok"
}
```

* Request_4
```json
{
  "msisdn": "123456789",
  "sessionId": "#BB123456",
  "userEntry": "1"
}
```
* Response_4
```json
{
  "sessionId": "#BB123456",
  "message": "Thank you for using Mama Money!"
}
```
