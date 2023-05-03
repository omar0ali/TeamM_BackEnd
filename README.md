# TeamM_BE
This is the back-end of an e-commerce website project created for EECS 4413, which focuses on building e-commerce systems.

Backend

## To import project into Eclipse

I used **github desktop** and cloned the project to my eclipse-workspace then opened Eclipse and created a new web dynamic project with the name TeamM_BE. Make sure the name should be exactly like the cloned directory. 
After making changes, you can go back to github desktop app and push the changes to origin.

### TEST CASES

Server home page
```
http://teamm4413.us-east-1.elasticbeanstalk.com/
```
Loggin url (Passwords are hashed)
```
http://teamm4413.us-east-1.elasticbeanstalk.com/login?email=someemail@gmail.com&password=password123
```
Logout is eiter one of these two
```
http://teamm4413.us-east-1.elasticbeanstalk.com/logout?email=someemail@gmail.com
http://teamm4413.us-east-1.elasticbeanstalk.com/logout
```
Sign up url, needs email, password fn and ln
```
http://teamm4413.us-east-1.elasticbeanstalk.com/register?email=newemail@gmail.com&password=111&fn=name&ln=lname 
```
Browse all items or products
```
http://teamm4413.us-east-1.elasticbeanstalk.com/browse
```
View speicific item with an ID
```
http://teamm4413.us-east-1.elasticbeanstalk.com/browse?id=2
```
View all comments by providing id of the item and review=true flag.
```
http://teamm4413.us-east-1.elasticbeanstalk.com/browse?id=2&review=true
```
This just returns a message if the user is logged in or not, and if logged in, it might say "there is no item selected"
```
http://teamm4413.us-east-1.elasticbeanstalk.com/review
```

Writing a review to a specific item
```
http://teamm4413.us-east-1.elasticbeanstalk.com/review?item_id=1&comment=This%20is%20a%20great%20product&stars=3
```
This should add an item to cart only if the user is logged in, must also add quantity of the items
It also checks the amount the user is adding and it checks that there are enough resources. Its is limited to 10.
```
http://teamm4413.us-east-1.elasticbeanstalk.com/browse?id=2&add=true&quantity=1
```
Can remove a specific Item from the cart only if it exists in the cart.
```
http://teamm4413.us-east-1.elasticbeanstalk.com/browse?id=2&add=false 
```
To view cart from a logged in user simply the following link:
```
http://teamm4413.us-east-1.elasticbeanstalk.com/login?cart=true 
```
Adding payment methods:
User must be logged in to view payment methods with the following link:
```
http://teamm4413.us-east-1.elasticbeanstalk.com/pay_methods
```
Adding payment methods would be:
```
http://teamm4413.us-east-1.elasticbeanstalk.com/pay_methods?card_number=847584093048&card_holder=someone&cvv=334&street=street1&city=city1&zip=or4hgf&phone=5554443333&exp_date=2025-10-10
```
User must be logged in to do that, otherwise, it won't add anything (After login in, user would be saved in the session).

Making a purchase is once the customer added items to their cart
Also there quantity of item decrement after purchase.
```
http://teamm4413.us-east-1.elasticbeanstalk.com/orders?purchase=true
```

Show all orders only if the admin is logged in (Customer account can won't be able to see anything)
```
http://teamm4413.us-east-1.elasticbeanstalk.com/orders
```
If a customer account logged in (they can see their orders)
```
http://teamm4413.us-east-1.elasticbeanstalk.com/orders?history=true
```

### Database url connection and port number
```
try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://id-db-teamm.ciity4oybruj.us-east-1.rds.amazonaws.com:3306/db_teamm","admin","adminOfTeamM-");
} catch(Exception e) {
		e.printStackTrace();
}
```
database.sql file has the create table, and insert queries.

### ER Diagram
You can access the er diagram through [https://app.diagrams.net/](https://app.diagrams.net/) and import file [TeamM_ER_Diagram.drawio](https://github.com/czyrnyc/TeamM_BE/blob/main/TeamM_ER_Diagram.drawio) located in the main directory of the project.


Front-End URL:
```
http://elasticbeanstalk-us-east-1-363292851522.s3-website-us-east-1.amazonaws.com/
```

Please feel free to edit, I might have made some mistakes or typos.
