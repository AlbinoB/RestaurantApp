<?php


	class DbOperations {
		private $con;
		private $placedAt;

		function __construct(){
			
			require_once dirname(__FILE__).  '/DbConnect.php'; 

			$db=new DbConnect();

			$this->con=$db->connect();

		}

		/*Create function*/

		function registerUser( $fullName, $email, $mobileNumber, $address, $password){


			$stmt=$this->con->prepare("INSERT INTO `customer`( `fullName`, `email`, `mobileNumber`, `address`, `password`) VALUES (?,?,?,?,?)");
			
			$stmt->bind_param("ssiss",$fullName, $email, $mobileNumber, $address, $password);
			//si: s is string :i is integer

			$response=array();
			$responseData=array();
			$responseDataData=array();


			if($stmt->execute()){

				$responseData['success']=true;

				$result=$this->con->query("SELECT customerId FROM `customer` where mobileNumber=".$mobileNumber);
			
				$row = $result->fetch_assoc();


				$responseDataData['user_id']=$row["customerId"];
				$responseDataData['name']=$fullName;			
				$responseDataData['email']=$email;
				$responseDataData['mobile_number']=$mobileNumber;
				$responseDataData['address']=$address;

				$responseData['data']=$responseDataData;

				$response['data']=$responseData;

				return (json_encode($response));
			}else{
				
				$responseData['success']=false;

				$responseData['errorMessage']="Mobile Number OR Email Id already registered";

				$response['data']=$responseData;

				return (json_encode($response));
			}

		}


		function loginUser($mobileNumber,$password){

			$response=array();
			$responseData=array();
			$responseDataData=array();



				

				$result=$this->con->query("SELECT customerId,fullName,email,mobileNumber,address FROM `customer` where mobileNumber=".$mobileNumber." and password=".$password);
			
				if($result->num_rows > 0){
					$responseData['success']=true;
					$row = $result->fetch_assoc();

					$responseDataData['user_id']=$row["customerId"];
					$responseDataData['name']=$row["fullName"];			
					$responseDataData['email']=$row["email"];
					$responseDataData['mobile_number']=$row["mobileNumber"];
					$responseDataData['address']=$row["address"];

					$responseData['data']=$responseDataData;

					$response['data']=$responseData;

					return (json_encode($response));

				}
				else
				{
					$responseData['success']=false;
					$responseData['data']="Invalid Mobile Number or Password";
					$response['data']=$responseData;
					return (json_encode($response));

				}
				
			

			}


			function validateUser($jsonObj){
				$response=array();
				$responseData=array();


				$result=$this->con->query("SELECT fullName FROM `customer` where mobileNumber='".$jsonObj["mobile_number"]."' and email='".$jsonObj["email"]."'");
			
				if($result!=NULL & $result->num_rows > 0 ){
					

					$responseData["success"]=true;
					$responseData["first_try"]=true;

					$response["data"]=$responseData;


					$to = "albinobraganza21@gmail.com";
					$subject = "Food Runner change password OTP";
					$txt = "Your OTP:223344";
					$headers = "From: webmaster@example.com";

					//ini_set("SMTP","ssl://smtp.gmail.com");
					//ini_set("smtp_port","465");
					mail($to,$subject,$txt,$headers);


					return (json_encode($response));

				}
				else
				{
					$responseData["success"]=false;
					$responseData["errorMessage"]="No user found";

					$response["data"]=$responseData;

					return (json_encode($response));
				}


			}


			function resetPassword($jsonObj){
				$response=array();
				$responseData=array();

				$result=$this->con->query("SELECT otp FROM `customer` where mobileNumber='".$jsonObj["mobile_number"]."'");
			
				if($result!=NULL & $result->num_rows > 0 ){

					$otp=$result->fetch_assoc();

					if($jsonObj["otp"]==$otp["otp"]){

						$stmt=$this->con->prepare("UPDATE `customer` SET `password`='".$jsonObj["password"]."' WHERE mobileNumber='".$jsonObj["mobile_number"]."'");
			
						
						//si: s is string :i is integer
						if($stmt->execute()){
							$responseData["success"]=true;
							$responseData["data"]="Password changed successfully";

							$response["data"]=$responseData;

							return (json_encode($response));

						}
						else
						{
							$responseData["success"]=false;
							$responseData["errorMessage"]="Some error occured!";

							$response["data"]=$responseData;

							return (json_encode($response));
						}




						
						
					}
					else
					{
							$responseData["success"]=false;
							$responseData["errorMessage"]="Invalid OTP";

							$response["data"]=$responseData;

							return (json_encode($response));
					}
				}
					


			}

		

			function fetchAllRestaurants(){

				$response=array();
				$responseData=array();
				$responseDataData=array();
				$responseDataDataRestarant=array();



				

				$result=$this->con->query("SELECT restaurantId,restaurantName,restaurantRating,costForOne,imageUrl FROM `restaurant`");
			
				if($result->num_rows > 0){
					$responseData['success']=true;
					while($row = $result->fetch_assoc()){

						$responseDataDataRestarant['id']=$row["restaurantId"];
						$responseDataDataRestarant['name']=$row["restaurantName"];	
						$responseDataDataRestarant['rating']=$row["restaurantRating"];
						$responseDataDataRestarant['cost_for_one']=$row["costForOne"];
						$responseDataDataRestarant['image_url']=$row["imageUrl"];

						array_push($responseDataData, $responseDataDataRestarant);

					}

					$responseData['data']=$responseDataData;
					$response['data']=$responseData;

					return (json_encode($response));
				}
				
				else
				{
					$responseData['success']=true;

					array_push($responseDataData, $responseDataDataRestarant);
					$responseData['data']=$responseDataData;
					$response['data']=$responseData;
					return (json_encode($response));

				}

			}


			function fetchRestaurantDishes($restaurantId){

				$response=array();
				$responseData=array();
				$responseDataData=array();
				$responseDataDataRestarantDish=array();



				

				$result=$this->con->query("SELECT dishId,dishName,dishPrice,fkrestaurantId FROM `restaurantdishes` where fkrestaurantId=".$restaurantId);
			
				if($result->num_rows > 0){
					$responseData['success']=true;
					while($row = $result->fetch_assoc()){

						$responseDataDataRestarantDish['id']=$row["dishId"];
						$responseDataDataRestarantDish['name']=$row["dishName"];	
						$responseDataDataRestarantDish['cost_for_one']=$row["dishPrice"];
						$responseDataDataRestarantDish['restaurant_id']=$row["fkrestaurantId"];

						array_push($responseDataData, $responseDataDataRestarantDish);

					}

					$responseData['data']=$responseDataData;
					$response['data']=$responseData;

					return (json_encode($response));
				}
				
				else
				{
					$responseData['success']=true;

					array_push($responseDataData, $responseDataDataRestarant);
					$responseData['data']=$responseDataData;
					$response['data']=$responseData;
					return (json_encode($response));
				}

			}

			function placeOrder($jsonObj){

				$response=array();
				$responseData=array();

				$customerId=$jsonObj["user_id"];
				$restaurantId=$jsonObj["restaurant_id"];
				$totalCost=$jsonObj["total_cost"];
				$foodItemsArray=$jsonObj["food"];

				$t=time();
				$this->placedAt=date("d-m-y",$t)." ".date("h:i:s");

				$stmt=$this->con->prepare("INSERT INTO `orders` (`fkcustomerId`, `fkrestaurantId`, `placedAt`, `totalCost`) VALUES (?,?,?,?)");
			
				$stmt->bind_param("iiss",$customerId, $restaurantId, $this->placedAt, $totalCost);
			//si: s is string :i is integer

				if($stmt->execute()){

					$result=$this->con->query("SELECT orderId FROM `orders` where placedAt='".$this->placedAt."'");
			
					$row = $result->fetch_assoc();

					foreach ($foodItemsArray as $eachItem) 
					{

						$stmt1=$this->con->prepare("INSERT INTO `order_items`(`fkorderId`, `fkdishId`) VALUES (?,?)");
			
						$stmt1->bind_param("ii",$row["orderId"], $eachItem["food_item_id"]);

						if ($stmt1->execute()) {
							$responseData["success"]=true;
							$response["data"]=$responseData;
							
						}else
						{
							$responseData["success"]=false;
							$response["errorMessage"]="Something went wrong!";	
						}

					}

				}else
				{
					$responseData["success"]=false;
					$response["errorMessage"]="Something went wrong!";	
				}

				return (json_encode($response));

			}


			function fetchOrderHistory($userId){

				$response=array();
				$responseData=array();
				$responseDataData=array();
				$responseDataDataRestarant=array();
				$responseDataDataRestarantItemsArray=array();
				$responseDataDataRestarantItemsOrdered=array();



				$result=$this->con->query("SELECT orderId,fkrestaurantId,totalCost,placedAt FROM `orders` where fkcustomerId=".$userId);
			
				if($result->num_rows > 0){
					$responseData['success']=true;
					while($row = $result->fetch_assoc()){

						$result1=$this->con->query("SELECT restaurantName FROM `restaurant` where restaurantId=".$row["fkrestaurantId"]);
			
						$restaurant = $result1->fetch_assoc();


						$responseDataDataRestarant['order_id']=$row["orderId"];
						$responseDataDataRestarant['restaurant_name']=$restaurant["restaurantName"];
						$responseDataDataRestarant['total_cost']=$row["totalCost"];	
						$responseDataDataRestarant['order_placed_at']=$row["placedAt"];

						$result2=$this->con->query("SELECT fkdishId FROM `order_items` where fkorderId=".$row["orderId"]);

						while ($eachItem = $result2->fetch_assoc()) {
							$result3=$this->con->query("SELECT dishName,dishPrice FROM `restaurantdishes` where dishId=".$eachItem["fkdishId"]);
							$itemDetails=$result3->fetch_assoc();

							$responseDataDataRestarantItemsOrdered["food_item_id"]=$row["orderId"];
							$responseDataDataRestarantItemsOrdered["name"]=$itemDetails["dishName"];
							$responseDataDataRestarantItemsOrdered["cost"]=$itemDetails["dishPrice"];


							array_push($responseDataDataRestarantItemsArray, $responseDataDataRestarantItemsOrdered);


						}

						$responseDataDataRestarant["food"]=$responseDataDataRestarantItemsArray;

						$responseDataDataRestarantItemsArray=array();



						array_push($responseDataData, $responseDataDataRestarant);

					}

					$responseData['data']=$responseDataData;
					$response['data']=$responseData;

					return (json_encode($response));
				}
				
				else
				{
					$responseData['success']=true;
					array_push($responseDataData, $responseDataDataRestarant);
					$responseData['data']=$responseDataData;
					$response['data']=$responseData;
					return (json_encode($response));

				}


			}


}