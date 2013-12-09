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
    if (empty($_POST['username']) || empty($_POST['password'])) {
		
	
	    // Create some data that will be the JSON response 
	    $response["success"] = 0;
	    $response["message"] = "Please Enter Both a Username and Password.";

		//die will kill the page and not execute any code below, it will also
		//display the parameter... in this case the JSON data our Android
		//app will parse
        die(json_encode($response));
    }
    
    //If we have made it here without dying, then we are in the clear to 
    //create a new user.  Let's setup our new query to create a user.  
    //Again, to protect against sql injects, user tokens such as :user and :pass
    $query = "UPDATE USER_TB SET user_password=:pass WHERE  user_username = :user";
    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':user' => $_POST['username'],
        ':pass' => $_POST['password']
    );
    
    //time to run our query, and create the user
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // Again, don't display $ex->getMessage() when you go live. 
        die("Failed to run query: " . $ex->getMessage());
		//You could comment out the above die and use this one:
		$response["success"] = 0;
		$response["message"] = "Database Error. Please Try Again!";
		die(json_encode($response));
    }

	//If we have made it this far without dying, we have successfully added
	//a new user to our database.  We could do a few things here, such as 
	//redirect to the login page.  Instead we are going to echo out some
	//json data that will be read by the Android application, which will login
	//the user (or redirect to a different activity, I'm not sure yet..)
	$response["success"] = 1;
	$response["message"] = "Password Successfully updated!";
	echo json_encode($response);
	
	//for a php webservice you could do a simple redirect and die.
	//header("Location: login.php"); 
	//die("Redirecting to login.php");
    
    
} else {
?>
	<h1>Passupdate</h1> 
	<form action="updatepass.php" method="post"> 
	    Username:<br /> 
	    <input type="text" name="username" value="" /> 
	    <br /><br /> 
	    Password:<br /> 
	    <input type="password" name="password" value="" /> 
	    <br /><br /> 
	    <input type="submit" value="Register New User" /> 
	</form>
	<?php
}

?>
