<?php
   
   

   header("Content-type:application/json");
      require_once("connecttwo.php");
$query = "SELECT * FROM notes";
$run = mysqli_query($con, $query);
$emptyArray = array();
while($row = mysqli_fetch_assoc($run)){
	$emptyArray[] = $row;
}
echo json_encode($emptyArray);
?>