<?php
require_once('connect.php');
$user = $_REQUEST['use'];
$pass = $_REQUEST['pas'];


$dupe1 = mysql_num_rows(mysql_query("select * from USER_TB where user_idno = '$user'")); 
 
 if ($dupe1 == 0) {   
 echo "2";
 exit();
}

//retrieve data from the database
$sql = mysql_query("SELECT user_password FROM USER_TB WHERE user_idno = '$user'");


while($row=mysql_fetch_array($sql))

{
      if($field1= $row['user_password'])
      	{
      		echo "$field1";
      	}
}	


?>