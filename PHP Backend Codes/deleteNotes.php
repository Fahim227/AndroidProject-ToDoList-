<?php
 if($_SERVER['REQUEST_METHOD'] == 'POST'){
	 
		$id = $_POST['id'];
		require_once("connecttwo.php");
		//(`title`,`body`,`color`) VALUES('$title', '$body', '$color')
		
		$query = "DELETE FROM `notes` WHERE `id` = '$id'";
		
		$run = mysqli_query($con, $query);
		
		if($run){
			$response['success'] = true;
			$response['message'] = "Successfully Deleted";
			
		}
		else{
			$response['success'] = false;
			$response['message'] = "Error";
			
		}
		
	}
	else{
			$response['success'] = false;
			$response['message'] = "Error";
	}
	echo json_encode($response);

?>