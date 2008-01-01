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
$sql = "SELECT * FROM PROPERTY_TB INNER JOIN USER_TB ON PROPERTY_TB.user_id = USER_TB.user_id INNER JOIN COMMERCIAL_TB ON PROPERTY_TB.property_id = COMMERCIAL_TB.property_id  WHERE property_type='commercial_house' AND commercial_type = 'For Lease'";
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