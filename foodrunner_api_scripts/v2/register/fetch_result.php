<?php
	require_once ('../../includes/DbOperations.php');  


		$response=array();
		$responseData=array();


	if($_SERVER['REQUEST_METHOD']=='POST'){

		$jsonObj = json_decode(file_get_contents('php://input'), true);//gets the json object passed

		if( isset($jsonObj['name'])&&
			isset($jsonObj['email'])&&
			isset($jsonObj['mobile_number'])&&
			isset($jsonObj['address'])&&
			isset($jsonObj['password'])

			)
		{

		if(
			strlen($jsonObj['name'])>2&&
			strlen($jsonObj['password'])>5
		){
				$db=new DbOperations();

			$dbResultQuery=$db->registerUser(
				
					$jsonObj['name'],
					$jsonObj['email'],
					$jsonObj['mobile_number'],
					$jsonObj['address'],
					$jsonObj['password']

				);

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
			}
			else
			{
				$responseData['success']=false;
				$responseData['error']="Invalid fields";
				$response['data']=$responseData;
				echo json_encode($response);// wiil encode and display the output in json 		
			}


		}else
		{

			$responseData['success']=false;
			$responseData['error']="Required Fields are missing";
			$response['data']=$responseData;
			echo json_encode($response);// will encode and display the output in json 

		}


	}else{

		$responseData['success']=false;
		$responseData['error']="Invalid Request";
		$response['data']=$responseData;
		echo json_encode($response);// will encode and display the output in json 
	}

	