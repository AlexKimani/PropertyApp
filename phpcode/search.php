<?php

require_once('connect.php');

$idnumber = $_REQUEST['id'];
$bosss = $_REQUEST['boss'];


//checks for empty fields
if (!$idnumber)
  { 
 echo "1";
 exit();
  }



else {

$dupe1 = mysql_num_rows(mysql_query("select * from `".$bosss." where phonenumber='$idnumber'")); 
 
        if ($dupe1 > 0) { 
  
 echo "2";
 exit();
 exit; 
}
}


	


?>

