<?php

require_once('connect.php');

$namee = $_REQUEST['name'];
$phon = $_REQUEST['phone'];
$col = $_REQUEST['collaterall'];
$amt = $_REQUEST['loanamt'];
//$int = $_REQUEST['interest'];
//$week= $_REQUEST['duration'];
$bos=$_REQUEST['boss'];
//$pay=$_REQUEST['paymeny'];
if ($namee==null||$phon==null||$col==null||$amt==null||$bos==null)
 { 
 echo "1";
 exit();
  }


//inserts data into the database
$sql = "INSERT INTO `".$bos."` (name,phonenumber,collateral,amountloaned,status)
		VALUES ('$namee','$phon','$col','$amt','unpaid')";


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

