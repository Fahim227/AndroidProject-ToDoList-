<?php
 if($_SERVER['REQUEST_METHOD'] == 'POST'){
	 
		$id = $_POST['id'];
		$title = $_POST['title'];
		$body = $_POST['body'];
		$color = $_POST['color'];
		
		require_once("connecttwo.php");
		//(`title`,`body`,`color`) VALUES('$title', '$body', '$color')
		
		$query = "UPDATE `notes` SET `title` = '$title', `body` = '$body', `color` = '$color' WHERE `id` = '$id'";
		
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