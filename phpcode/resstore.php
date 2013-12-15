<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//if posted data is not empty
if (!empty($_POST)) {
    //If the username or password is empty when the user submits
    //the form, the page will die.
    //Using die isn't a very good practice, you may want to look into
    //displaying an error message within the form instead.  
    //We could also do front-end form validation from within our Android App,
    //but it is good to have a have the back-end code do a double check.
    if (empty($_POST['psize']) || empty($_POST['plocation'])|| empty($_POST['pname'])|| empty($_POST['price'])|| empty($_POST['username'])|| empty($_POST['nor'])) {
        
        
        // Create some data that will be the JSON response 
        $response["success"] = 0;
        $response["message"] = "Please Enter all fields.";
        
        //die will kill the page and not execute any code below, it will also
        //display the parameter... in this case the JSON data our Android
        //app will parse
        die(json_encode($response));
    }
    
    //if the page hasn't died, we will check with our database to see if there is
    //already a user with the username specificed in the form.  ":user" is just
    //a blank variable that we will change before we execute the query.  We
    //do it this way to increase security, and defend against sql injections

     $query  = " SELECT user_id FROM USER_TB WHERE user_username = :user";
    //now lets update what :user should be
    $query_params = array(
        ':user' => $_POST['username']
    );
    
    //Now let's make run the query:
    try {
        // These two statements run the query against your database table. 
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!";
        die(json_encode($response));
    }
    
    //fetch is an array of returned data.  If any data is returned,
    //we know that the username is already in use, so we murder our
    //page
    $row = $stmt->fetch();
    $field1 = $row['user_id'];
    /*if($field1 = $row['user_id'])
        {
            echo "$field1";

        }*/

    //now lets update what :user should be
   
    
    //fetch is an array of returned data.  If any data is returned,
    //we know that the username is already in use, so we murder our
    //page

    //If we have made it here without dying, then we are in the clear to 
    //create a new user.  Let's setup our new query to create a user.  
    //Again, to protect against sql injects, user tokens such as :user and :pass
  $query = "INSERT INTO PROPERTY_TB(property_name,property_size,property_type,property_location,property_price,user_id) VALUES (:name, :size,:type,:place, :value,$field1)";
    
    //Again, we need to update our tokens with the actual data:
   $query_params = array(
        ':size' => $_POST['psize'],
        ':place' => $_POST['plocation'],
        ':name' => $_POST['pname'],
        ':type' => 'residential_house',
        ':value' => $_POST['price']
    );
    
    //time to run our query, and create the user
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one:
        $response["success"] = 0;
        $response["message"] = "Database Error3. Please Try Again!";
        die(json_encode($response));
    }
    
    //If we have made it this far without dying, we have successfully added
    //a new user to our database.  We could do a few things here, such as 
    //redirect to the login page.  Instead we are going to echo out some
    //json data that will be read by the Android application, which will login
    //the user (or redirect to a different activity, I'm not sure yet..)
   /* $response["success"] = 1;
    $response["message"] = "Land Registered";
    echo json_encode($response);*/
    
    //for a php webservice you could do a simple redirect and die.
    //header("Location: login.php"); 
    //die("Redirecting to login.php");

    $query3  = " SELECT property_id FROM PROPERTY_TB WHERE property_type = 'residential_house' AND property_name=:nam";
    //now lets update what :user should be
    $query_params = array(
        ':nam' => $_POST['pname']
    );
    
    //Now let's make run the query:
    try {
        // These two statements run the query against your database table. 
        $stmt   = $db->prepare($query3);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error4. Please Try Again!";
        die(json_encode($response));
    }
    
    //fetch is an array of returned data.  If any data is returned,
    //we know that the username is already in use, so we murder our
    //page
    $row = $stmt->fetch();
    $field4 = $row['property_id'];
   /* if($field4 = $row['property_id'])
        {
            echo "$field4";

        }*/


   
   $query2 = "INSERT INTO RESIDENTIAL_TB(residential_nor,residential_type, property_id) VALUES (:rooms,:purp,'$field4')";
    
    //Again, we need to update our tokens with the actual data:
   $query_params = array(
        ':rooms' => $_POST['nor'],        
        ':purp' => $_POST['purpose']
    );
    
    //time to run our query, and create the user
    try {
        $stmt   = $db->prepare($query2);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one:
        $response["success"] = 0;
        $response["message"] = "Database Error5. Please Try Again!";
        die(json_encode($response));
    }
    
    //If we have made it this far without dying, we have successfully added
    //a new user to our database.  We could do a few things here, such as 
    //redirect to the login page.  Instead we are going to echo out some
    //json data that will be read by the Android application, which will login
    //the user (or redirect to a different activity, I'm not sure yet..)
    $response["success"] = 1;
    $response["message"] = "residential house Registered";
    echo json_encode($response);
    
    
} else {
?>
	<h1>Property</h1> 
	<form action="resstore.php" method="post"> 
	    Name:<br /> 
	    <input type="text" name="pname" value="" /> 
	    <br /><br /> 
	    Size:<br /> 
	    <input type="text" name="psize" value="" /> 
	    <br /><br /> 
        price:<br /> 
        <input type="type" name="price" value="" /> 
        <br /><br /> 
        place:<br /> 
        <input type="type" name="plocation" value="" /> 
        <br /><br /> 
        Username:<br /> 
        <input type="text" name="username" value="" /> 
        <br /><br /> 
        Number of rooms:<br /> 
        <input type="text" name="nor" value="" /> 
        <br /><br /> 
	    <input type="submit" value="Register New User" /> 
	</form>
	<?php
}

?>
