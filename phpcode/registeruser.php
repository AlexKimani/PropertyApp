<?php

require_once('connect.php');

$jina1 = $_REQUEST['firstname'];
$jina2= $_REQUEST['secondname'];
$idno = $_REQUEST['idnumber'];
$passcode = $_REQUEST['password'];
$job = $_REQUEST['occupation'];
$gen= $_REQUEST['gender'];
$box = $_REQUEST['address'];
$email = $_REQUEST['mail'];
$phonumber = $_REQUEST['telephone'];

//*/checks for empty fields
/* if ($name==null||$id==null||$pass==null)
 { 
 echo "1";
 exit();
  }*/
//checks double user name registration

$dupe1 = mysql_num_rows(mysql_query("select * from USER_TB where user_idno='$idno'")); 
 
        if ($dupe1 > 0) { 
  
 echo "2";
 exit();
}


//inserts data into the database
$sql = "INSERT INTO USER_TB (user_fname,user_sname,user_idno,user_password,_user_occupation,user_gender,user_address,user_email,user_phonenumber)
		VALUES ('$jina1','$jina2','$idno','$passcode','$job','$gen','$box','$email','$phonumber')";


$result = mysql_query($sql);

	if($result)
		{
			echo "4";
		}
	else
		{
			echo "5";
		}


?>






