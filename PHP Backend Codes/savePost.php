<?php
   

 if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$body = $_POST['body'];
		$userid = $_POST['user_id'];
		require_once("connect.php");
		
		$query = "INSERT INTO `posts`(`body`,`user_id`) VALUES('$body', '$userid')";
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