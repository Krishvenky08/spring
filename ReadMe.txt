Step 1: Run SpringBoot Application
Step 2: Access login controller http://localhost:9091/student-api/students/login.do
It should be Post request and pass data in following format
{
	"username": "makarand",
	"password": "makarand"
}
Step 3: In return you will get JWT Token copy that

Step 4: http://localhost:9091/student-api/students/ method = Get OR Any controller method

Step 5: And set Authorization header Bearer JWT_TOKEN

Step 6: To add new student
http://localhost:9091/student-api/students/
Authorization header Bearer Token_Generated_From_Login
payload:
{
    "studentId":"100",
    "studentName":"Makarand Bhoir",
    "studentScore":"60",
    "address":{
        "city":"Mumbai",
        "state":"MH",
        "pin":"400001"
    }
}