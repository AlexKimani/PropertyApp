<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");


if (!empty($_POST)) {

    if (empty($_POST['location']) || empty($_POST['price'])){

         // Create some data that will be the JSON response 
        $response["success"] = 0;
        $response["message"] = "Please Enter all fields.";
        
        //die will kill the page and not execute any code below, it will also
        //display the parameter... in this case the JSON data our Android
        //app will parse OR property_location = :place"
        die(json_encode($response));
    }

    //initial query
   /* property_name,property_size,property_location,property_price,user_username,user_email,user_phonenumber */
  /* AND ( property_price=:value AND property_location= :place)*/
$query = "SELECT * FROM PROPERTY_TB INNER JOIN USER_TB ON PROPERTY_TB.user_id = USER_TB.user_id WHERE property_type='land' ;
";
 $query_params = array(
        ':value' => $_POST['price'],
        ':place' => $_POST['location']
    );

//execute query
try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Database Error!";
    die(json_encode($response));
}
  
// Finally, we can retrieve all of the found rows into an array using fetchAll 
$rows = $stmt->fetchAll();


if ($rows) {
    $response["success"] = 1;
    $response["message"] = "Post Available!";
    $response2["posts"]   = array();
    
   foreach ($rows as $row) {
        $post             = array();
        $post["property_id"]  = $row["property_id"];
        $post["property_name"]  = $row["property_name"];
        $post["property_size"] = $row["property_size"];
        $post["property_location"]    = $row["property_location"];
        $post["property_price"]  = $row["property_price"];
        $post["user_username"]  = $row["user_username"];
        $post["user_email"]  = $row["user_email"];
        $post["user_phonenumber"]  = $row["user_phonenumber"];        
        
        //update our repsonse JSON data
        array_push($response2["posts"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response2);
    
    
} else {
    $response2["success"] = 0;
    $response2["message"] = "No Post Available!";
    die(json_encode($response2));
}
}
else {
?>
    <h1>Fecth Search</h1> 
    <form action="comments.php" method="post"> 
        Location:<br /> 
        <input type="text" name="location" value="" /> 
        <br /><br /> 
        Price:<br /> 
        <input type="text" name="price" value="" /> 
        <br /><br /> 
        <input type="submit" value="Search" /> 
    </form>
    <?php
}


?>
