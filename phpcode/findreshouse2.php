<?php
$host = "127.0.0.1"; // host of MySQL server
$user = "root"; // MySQL user
$pwd = "root"; // MySQL user's password
$db = "PropertyApp"; // database name

/*require("land.php");*/
// Create connection
$con = mysqli_connect($host, $user, $pwd, $db);

// Check connection
if(mysqli_connect_errno($con)) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
} 

// query the application data
/*AND ( property_price <='$value' AND  property_location= '$place')*/
/*user_username,user_email,user_phonenumber,property_name,property_size,property_location,property_price,residential_nor*/
$sql = "SELECT * FROM PROPERTY_TB INNER JOIN USER_TB ON PROPERTY_TB.user_id = USER_TB.user_id INNER JOIN RESIDENTIAL_TB ON PROPERTY_TB.property_id = RESIDENTIAL_TB.property_id  WHERE property_type='residential_house' AND residential_type =  'For Sale' ";
$result = mysqli_query($con, $sql);

// an array to save the application data
$rows = array();

// iterate to query result and add every rows into array
while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
    $rows[]= $row; 
}

// close the database connection
mysqli_close($con);

// echo the application data in json format
echo json_encode($rows);
?>