<?php
    //connection definition file
    include 'connect.php';
  $table = $_REQUEST['boss'];
    
    //value posted from android
     //$dname = $_REQUEST['clinicname'];
	//$info ="Yaya Chemists Ltd";
	
            
    //query to insert into DB values posted from android    
      // $query=mysql_query("SELECT * FROM  pharmacy WHERE Name = '$pname'");
	   $query=mysql_query("SELECT * FROM  `".$table."` where status='paid'");
    //execute query
	//echo $query;
    
    //check if query was successful
	while($rows = mysql_fetch_array($query))
	{

	$result = $rows['name']."#";
		    
		echo $result;		
		
  }