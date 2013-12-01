<?php
include 'connect.php';

$idnumber = $_REQUEST['id'];
$bos = $_REQUEST['boss'];


//checks for empty fields
if (!$idnumber)
  { 
 echo "1";
 exit();
  }


$sql = "UPDATE `".$bos."` SET status='paid' WHERE phonenumber='$idnumber'"; 
 $result = mysql_query($sql);
        if($result)
		{ 
  
 echo "2";
 }
 else
		{
			echo "3";
		}

?>