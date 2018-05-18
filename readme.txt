This Java program allows user to access movie related data files, and run searches on relation between users and movies in the database.

Team member: Ao Sun, Yi Wang

—————————————————
Classes
—————————————————
Movie.java
User.java
Database.java
DataController.java
ReaderFactory.java
FileReader.java
MovieReader.java
RatingReader.java
Observer.java
InputObserver.java
Logger.java
Observable.java
UserInterface.java
Main.java

Please run Main.java to start the program 
—————————————————
Class Design
—————————————————
We used the following three design patterns: Three Tier Architecture (Presentation, Controller, Data), Observer Pattern, and Factory Pattern.            

For the Data section of the Three Tier Architecture, Class Database stores the data read in from files for rating and movie by using three hash maps: users<Integer, User>, movies<String, Movie>, userToMovie<User, HashMap<Movie, Double>>. users<Integer, User> maps user IDs of type Integer to User objects of type User; movies<String, Movie> maps movie titles of type String to Movie objects if type Movie; userToMovie<User, HashMap<Movie, Double>> maps Users of type User to another hash map where keys are movies that the user has watched and values are his or her ratings to these movies. The purpose of this creating this class is to purely store data into appropriate data structures so that the data can be easily accessed and manipulated later in this project. This also complies with the single responsibility principle that each class or module should only be responsible for one single part of the functionality of the program.

For the Controller part of the Three Tier Architecture, there is a Class called DataController, where the data stored in Class Database is processed so that predictions can be calculated and top recommendations can be generated. This is one of the changes that was made in Milestone 2 compared to Milestone 1. This reinforces the idea of single responsibility principle. When generating predictions and recommendations, data from Class Database will be accessed and used to get proper results. The Classes that are remained from the previous Milestone are User and Movie, which stores information from files for ratings and movies. After parsing data from these two files and storing them, they are populated into pre-designed data structures in Class Database for future usage.         

For the Presentation part of the Three Tier Architecture, it will be the responsibility of the UserInterface class. The UserInterface class uses Class FileReader when the set up function is called, and Classes Movie and User will then parse the data read in from FileReader to and store them into appropriate data structures in Database in order to set up the process. When the get input function is called in the UserInterface class, the input will be passed into Class DataController who will then use Class Database to generate predictions and recommendations for the client. All the data stored in Class Database are encapsulated into Class DataController; therefore, UserInterface only interacts with DataController to get predictions and recommendations. This also ensures that this program abides by the idea of abstraction and encapsulation.

For Factory Pattern, there is a ReaderFactory class that encapsulates the entire reader function. Under ReaderFactory, there are two concrete reader classes, MovieReader and RatingReader. Both of them implement an interface called FileReader. Each of these two reader classes has its own implementation on FileReader depending on the format of the files being read. To show the connection between the ReaderFactory with the entire program, one of the concrete readers will be called in UserInterface when the program is being set up. However, the user does not need to know how the file reader is implemented behind the scene. All the user needs to do is to call the type of file reader needed, either MovieReader or RatingReader, to start the program. This demonstrates that the program has high abstraction and encapsulation.            

For Observer Pattern, we have three specific classes that are dedicated to this design pattern: Observable, Observer, and InputObserver. This entire observation is set up to update the log in the Logger class. Class Observable is the class that objects use to register as observers and remove themselves as observers. Methods register(Observer ob) and unregister(Observer ob) fulfill these two functions. There is also a method called notifyObservers() that is used to update all the current observers whenever state changes. In addition to these three methods, there is another method called getUpdate(Observer ob) that will allow the observer to get updates of type generic object from the subject. The return type is intentionally set to Object so that it is generic and will be easily used for any type of data. In the interface class Observer, there are two methods: update() and setSubject(Observable sub). The update() method will get called when the subject’s state changes. setSubject(Observable sub) sets the object to be observed. Class InputObserver is the concrete class that implements the interface Observer. For this specific assignment, the activities in UserInterface need to be updated in Logger, so writeLog(String input) is called in the implementation of update().

To sum up, Three Tier Architecture, Observer Pattern, and Factory Pattern are all demonstrated thoroughly and clearly across the entire program. Also, single responsibility principle, abstraction and encapsulation are incorporated throughout the project along with the design patterns.                            
