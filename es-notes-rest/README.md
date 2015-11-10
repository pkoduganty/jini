#Prerequisites
JDK 8

#Quick Start
git clone https://github.com/pkoduganty/jini.git
cd jini/es-notes-rest
java -jar jeni-notes-rest-1.jar
*should start tomcat and elasticsearch automatically, REST endpoint on http://localhost:8090/notes

#REST Endpoints

###GET ALL
http://localhost:8090/notes

###GET BY ID
http://localhost:8090/notes/YmwH7Zp9RR-KFUgOyEptjw

###GET BY AUTHOR
http://localhost:8090/notes?author=praveen

###CREATE/UPDATE
POST http://localhost:8090/notes
{
"title":"first note",
"description":"first note",
"author":"praveen",
"subject":["general","misc"]
}

###DELETE
DELETE http://localhost:8090/notes/YmwH7Zp9RR-KFUgOyEptjw