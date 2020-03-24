<?php
	require_once ('../../includes/DbOperations.php');  


		$response=array();
		$responseData=array();

	if($_SERVER['REQUEST_METHOD']=='POST'){

		$jsonObj = json_decode(file_get_contents('php://input'), true);//gets the json object passed

		if(
			isset($jsonObj['mobile_number'])&&
			isset($jsonObj['password'])
			)
		{
			$db=new DbOperations();

			$dbResultQuery=$db->loginUser(
			$jsonObj['mobile_number'],
			$jsonObj['password']);

			if($dbResultQuery!=null){

					echo $dbResultQuery;

				}
				else
				{
				

					$responseData['success']=false;
					$responseData['error']="Some error occured";
					$response['data']=$responseData;
					echo json_encode($response);// wiil encode and display the output in json 

				
				}


		}else
		{

			$responseData['success']=false;
			$responseData['error']="Invalid Request";
			$response['data']=$responseData;
			echo json_encode($response);// will encode and display the output in json 
		}
	}

?>
	