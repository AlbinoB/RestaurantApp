<?php
	require_once ('../../../includes/DbOperations.php');  


		$response=array();
		$responseData=array();

		if($_SERVER['REQUEST_METHOD']=='GET'){

	
			$db=new DbOperations();
			if(isset($_GET["restaurantId"])){
				$dbResultQuery=$db->fetchRestaurantDishes($_GET["restaurantId"]);

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
			}else{

					$responseData['success']=false;
					$responseData['error']="Restaurant Id missing";
					$response['data']=$responseData;
					echo json_encode($response);
			}

			
		}else
		{

			$responseData['success']=false;
			$responseData['error']="Invalid Request";
			$response['data']=$responseData;
			echo json_encode($response);// will encode and display the output in json 
		}		

		
	

?>
	