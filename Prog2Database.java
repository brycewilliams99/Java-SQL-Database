import java.util.Scanner;
import java.sql.*;



public class Prog2Database {
	
	
	public static void main(String[] args){
		
		try {
			getConnection();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	    Menu();

	  }

	static void Menu(){
		System.out.println(" ");
		System.out.println(" ");
	    System.out.println("Welcome to the Menu!");
	    System.out.println("Choose what you would like to do!");
	    System.out.println("A. Create new tables (People, Books, checkedoutBooks)");
	    System.out.println("B. Add new entry.");
	    System.out.println("C. Delete an entry.");
	    System.out.println("D. List a table.");
	    System.out.println("1. Add new book into the collection.");
	    System.out.println("2. Add new user.");
	    System.out.println("3. List users with books checked out.");
	    System.out.println("4. Check a new book out.");
	    System.out.println("5. Return a book that has been checked out.");
	    System.out.println("6. Exit the Database");
	    System.out.println(" ");
	    System.out.println("Enter what you would like to do: ");

		Scanner sc = new Scanner(System.in);

	    String choice = sc.nextLine();
	    
	   

	    switch(choice){
	      case "A":
	        choiceA();
	        break;

	      case "B":
	        choiceB();
	        break;

	      case "C":
	        choiceC();
	        break;

	      case "D":
	        choiceD();
	        break;

	      case "1":
	        addBook();
	        break;

	      case "2":
	        addUser();
	        break;

	      case "3":
	        listUsers();
	        break;

	      case "4":
	        checkoutBook();
	        break;

	      case "5":
	        returnBook();
	        break;

	      case "6":
	        exit();
	        break;
	    }
	    sc.close();
	  }
	  
	  static void choiceA() {
		 
		 String userTable =  "CREATE TABLE People " + "(SSN INTEGER not NULL, " + " name VARCHAR(255), " + " PRIMARY KEY ( SSN ))";
		 
		 String booksTable = "CREATE TABLE Books " + "(author VARCHAR(255), " + "title VARCHAR(255), " + "callNo INT)";
		 
		 String checkedoutbooksTable = "CREATE TABLE checkedOutBooks " + "(SSN INTEGER not NULL, " + " callNo INT)";
		 
		 try {
			 Connection conn = getConnection();
			 Statement stmt = conn.createStatement();
			 
			 stmt.executeUpdate(userTable);
			 stmt.executeUpdate(booksTable);
			 stmt.executeUpdate(checkedoutbooksTable);
		 }
		 catch (Exception e) {
			 System.out.println(e);
		 }
		 finally {
			 System.out.println("Tables created successfully!");
			 Menu();
		 }
	  }
	  
	  static void choiceB() {
		  
		  Scanner sc = new Scanner(System.in);
		  
		  System.out.println("Please choose what table you would like to add an entry for.");
		  System.out.println("1. People");
		  System.out.println("2. Books");
		  System.out.println("3. Checked out Books");
		  
		  String pick = null;
		  String sql = null;
		  String ssn = null;
		  String name = null;
		  String author = null;
		  int callNo = 0;
		  String title = null;
		  
		  pick = sc.nextLine();
		  
		  switch(pick) {
		  
		  case "1":
			  System.out.println("Please enter the person's name: ");
			  name = sc.nextLine();
			  System.out.println("Please enter the person's SSN: ");
			  ssn = sc.nextLine();
			  sql = "INSERT INTO People(SSN, name)" + "VALUES ('"+ssn+"', '"+name+"')";
			  break;
			  
		  case "2":
			  System.out.println("Please enter the author's name: ");
			  author = sc.nextLine();
			  System.out.println("Please enter the title of the book: ");
			  title = sc.nextLine();
			  System.out.println("Please enter the call number of the book: ");
			  callNo = sc.nextInt();
			  sql = "INSERT INTO Books(author, title, callNo)" + "VALUES('"+author+"', '"+title+"', '"+callNo+"')";
			  break;
			  
		  case "3":
			  System.out.println("Enter the person's SSN: ");
			  ssn = sc.nextLine();
			  System.out.println("Enter the call number of the book: ");
			  callNo = sc.nextInt();
			  sql = "INSERT INTO checkedoutBooks(ssn, name)" + "VALUES('"+ssn+"', '"+callNo+"')";
			  break;
		  }
		  
		  try {
			  Connection conn = getConnection();
			  Statement stmt = conn.createStatement();
			  
			  stmt.executeUpdate(sql);
			  System.out.println("Entry added successfully!");
		  }
		  
		  catch(Exception e) {
			  System.out.println(e);
			  System.out.println("Failed.");
		  }
		  
		  sc.close();
		  Menu();
	  }
	  
	  static void choiceC() {
		  
		  Scanner sc = new Scanner(System.in);
		  
		  System.out.println("Choose a table that you would like to delete from.");
		  System.out.println("1. People");
		  System.out.println("2. Books");
		  System.out.println("3. Checked Out Books");
		  
		  String pick = null;
		  String sql = null;
		  String ssn = null;
		  String callNo = null;
		  
		  pick = sc.nextLine();
		  
		  switch(pick) {
		  case "1":
			  System.out.println("Enter User's SSN: ");
			  ssn = sc.nextLine();
			  sql = "DELETE FROM People WHERE SSN='" +ssn + "'";
			  break;
			  
		  case "2":
			  System.out.println("Enter the book's call number: ");
			  callNo = sc.nextLine();
			  sql = "DELETE FROM Books WHERE callNo='" +callNo+ "'";
			  break;
			  
		  case "3":
			  System.out.println("Enter User's SSN: ");
			  ssn = sc.nextLine();
			  System.out.println("Enter the book's call number: ");
			  callNo = sc.nextLine();
			  sql = "DELETE FROM checkedoutBooks WHERE callNo='"+callNo+"'"+"and SSN='"+ssn+"'";
			  break;
		  }
		  
		  try {
			  Connection conn = getConnection();
			  Statement stmt = conn.createStatement();
			  
			  stmt.executeUpdate(sql);
			  System.out.println("Entry deletion successful!");
		  }
		  
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Failed.");
		  }
		  
		  sc.close();
		  Menu();
	  }
	  
	  static void choiceD() {
		  
		  Scanner sc = new Scanner(System.in);

		  
		  System.out.println("Choose a table to print out.");
		  System.out.println("1. People");
		  System.out.println("2. Books");
		  System.out.println("3. Checked out Books");
		  
		  String pick = sc.nextLine();
		  
		  String sql = null;
		  String header = null;
		  
		  switch(pick) {
		  
		  case "1":
			  sql = "SELECT * FROM People";
			  header = "SSN  NAME";
			  break;
			  
		  case "2":
			  sql = "SELECT * FROM Books";
			  header = "AUTHOR  TITLE  CALLNO";
			  break;
			  
		  case "3":
			  sql = "SELECT * FROM checkedoutBooks";
			  header = "SSN  CALLNO";
			  break;
		  }
		  
		 try {
			 Connection conn = getConnection();
			 Statement stmt = conn.createStatement();
			 System.out.println(header);
			 
			 if(pick.equals("2")){
				 ResultSet listValues = stmt.executeQuery(sql);
				 while(listValues.next()) {
					 System.out.println(listValues.getString(1)+" , "+listValues.getString(2)+" , "+listValues.getString(3));
				 }
			 }
			 else {
				 ResultSet listValues = stmt.executeQuery(sql);
				 while(listValues.next()) {
					 System.out.println(listValues.getString(1)+" , "+listValues.getString(2));
				 }
			 }
		 }
		 
		 catch (Exception e) {
			 System.out.println(e);
		 }
		 
		 Menu();
		 sc.close();
	  }
	  
	  static void addBook() {
		 
		 Scanner sc = new Scanner(System.in);
		 
		 String choice2 = "y";
		 
		 while(choice2.equals("y")) {
			 
		 
		  System.out.println("Enter the authors name: ");
		  String author = sc.nextLine();
		  
		  System.out.println("Enter the title of the book: ");
		  String title = sc.nextLine();
		  
		  System.out.println("Enter the call number of the book: ");
		  String callNo = sc.nextLine();
		  
		  try {
			  Connection conn = getConnection();
			  Statement stmt = conn.createStatement();
		  
		  stmt.executeUpdate("INSERT INTO Books (author, title, callNo)" + "VALUES ('"+author+"', '"+title+"', '"+callNo+"')");
		  
		  System.out.println("Would you like to add another book? (y/n): ");
		  choice2 = sc.nextLine();
		  }
		  catch (Exception e){
			  System.out.println(e);
		  }
		 }
		 
		 Menu();
		 sc.close();
		  
	  }
	  
	  static void addUser() { //Works
		  String choice2 = "y";
		  
		  Scanner sc = new Scanner(System.in);
		  
		  while(choice2.equals("y")) {
		  
			  System.out.println("Enter new User's SSN: ");
			  String ssn = sc.nextLine();
		  
			  System.out.println("Enter name of new user: ");
			  String name = sc.nextLine();
			  
			  try {
				  Connection conn = getConnection();
				  Statement stmt = conn.createStatement();
			  stmt.executeUpdate("INSERT INTO People (ssn, name)" + "VALUES ('"+ssn+"', '"+name+"')");
			  
			  }
			  
			  catch(Exception e) {
				  System.out.println(e);
			  }
			  System.out.println("Would you like to add another user? (Y/n): ");
			  choice2 = sc.nextLine();
		  }
		  
		  Menu();
		  sc.close();

	  }
	  
	  static void listUsers() { //Works
		  try {
			  Connection conn = getConnection();
			  Statement stmt = conn.createStatement();
			  String peoplesql = "SELECT * FROM People";
			  String bookssql = "SELECT * FROM Books";
			  String checkedoutsql = "SELECT * FROM checkedoutBooks";
			  
			  System.out.println(" ");
			  System.out.println("Table: People");
			  System.out.println("SSN  NAME");
			  ResultSet listValues = stmt.executeQuery(peoplesql);
				 while(listValues.next()) {
					 System.out.println(listValues.getString(1)+" , "+listValues.getString(2));
				 }
				 
			  System.out.println(" ");
			  System.out.println("Table: Books");
			  System.out.println("AUTHOR  TITLE  CALLNO");
			  listValues = stmt.executeQuery(bookssql);
				 while(listValues.next()) {
					 System.out.println(listValues.getString(1)+" , "+listValues.getString(2) + " , "+listValues.getString(3));
				 }
				 
			  System.out.println(" ");
			  System.out.println("Table: Checked Out Books");
			  System.out.println("SSN  CALLNO");
			  listValues = stmt.executeQuery(checkedoutsql);
				 while(listValues.next()) {
					 System.out.println(listValues.getString(1)+" , "+listValues.getString(2));
				 }
		  }
		  
		  catch(Exception e) {
			  System.out.println(e);
			  System.out.println("Failed");
		  }
		  
		  Menu();
	  }
	  
	  static void checkoutBook() { //Works
		  String choice2 = "y";
		  Scanner sc = new Scanner(System.in);
		  
		  while(choice2.equals("y")) {
			  System.out.println("Enter user's SSN: ");
			  String ssn = sc.nextLine();
			  
			  System.out.println("Enter the book's call number: ");
			  String callNo = sc.nextLine();
			  
			  try {
				  Connection conn = getConnection();
				  Statement stmt = conn.createStatement();
				  stmt.executeUpdate("INSERT INTO checkedoutBooks (ssn, callNo)" + "VALUES ('"+ssn+"', '"+callNo+"')");
				  stmt.executeUpdate("DELETE FROM Books WHERE callNo='" +callNo + "'");
				  System.out.println("Checkout Successful!");
			  
				  System.out.println("Would you like to checkout another book? (Y/n): ");
				  choice2 = sc.nextLine();
			  }
			  catch (Exception e) {
				  System.out.println(e);
			  }
		  }
		  
		  Menu();
		  sc.close();
	  }
	  
	  static void returnBook() { //Works
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Enter the authors name of the book you are returning: ");
		  String author = sc.nextLine();
		  
		  System.out.println("Enter the title of the book you are returning: ");
		  String title = sc.nextLine();
		  
		  System.out.println("Enter the call number of the book you are returning: ");
		  String callNo = sc.nextLine();
		  sc.close();
		  
		  try {
			  Connection conn = getConnection();
			  Statement stmt = conn.createStatement();
			  stmt.executeUpdate("DELETE FROM checkedoutBooks WHERE callNo='" +callNo + "'");
			  stmt.executeUpdate("INSERT INTO Books (author, title, callNo)" + "VALUES('"+author+"', '"+title+"', '"+callNo+"')");
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
		  
		  Menu();
		  
	  }
	  
	  static void exit() {
		  System.out.println("Exiting Database! Goodbye!");
		  System.exit(0);
	  }
	  
	  public static Connection getConnection() throws Exception{
		  try {
			  String url = "jdbc:ucanaccess://D:/javafiles/Prog2Database.accdb";
			  //This is where my access file was located on my computer.
			  
			  Connection conn = DriverManager.getConnection(url);
			  System.out.println("Connection Successful");
			  return conn;
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
		  
		  return null;
	  }
}