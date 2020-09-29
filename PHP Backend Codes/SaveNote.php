<?php
   

 if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$title = $_POST['title'];
		$body = $_POST['body'];
		$color = $_POST['color'];
		
		require_once("connecttwo.php");
		
		$query = "INSERT INTO `notes`(`title`,`body`,`color`) VALUES('$title', '$body', '$color')";
		$run = mysqli_query($con, $query);
		
		if($run){
			$response['success'] = true;
			$response['message'] = "Successfully";
			
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