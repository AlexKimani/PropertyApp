<?php
   
    require_once('connect.php');
    
 
    
 $selectedloan = $_REQUEST['info'];
 $bos = $_REQUEST['boss'];
             
 
   
	$query="SELECT * FROM  `".$bos."` WHERE name = '$selectedloan'";   
	  $r = mysql_query($query);
  
		while($rows = mysql_fetch_array($r))
		{
$result=$rows['name']."#".$rows['amountloaned']."#".$rows['collateral']."#".$rows['phonenumber'];
		    
		echo $result;		
		
  }
  
?>