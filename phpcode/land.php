<?php
$host = "127.0.0.1"; // host of MySQL server
$user = "root"; // MySQL user
$pwd = "root"; // MySQL user's password
$db = "PropertyApp"; // database name

require("findland.php");// Create connection
$con = mysqli_connect($host, $user, $pwd, $db);

// Check connection
if(mysqli_connect_errno($con)) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
} 

mysqli_close($con);
?>