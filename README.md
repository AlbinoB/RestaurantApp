# RestaurantApp
<br/>Android App for ordering food online
<br/>
https://raw.githubusercontent.com/AlbinoB/RestaurantApp/master/Screenshots/6-All%20Restaurants%20Screenshot.png

<br/>
<br/>The app is built in kotlin and uses volley to send get and post request from the internshala serve through rest api calls.
<br/>The app also uses the room library to send data to the SQLite database.
<br/>
<br/>Main features considered while developing
<br/>1.Recycler Views
<br/>2.Fragments
<br/>3.Room Library
<br/>4.Volley
<br/>
<br/>Branches
<br/>1.master->New improvements and stable(connected to aws owned API-customised).
<br/>2.internshalTraining->Training course from internshal.com(internshala Api)
<br/>link->https://trainings.internshala.com/?referral=ISRP5558822&utm_source=ISRP_share_link&utm_medium=ISRP5558822&utm_campaign=TRAINING 
<br/>3.TestEC2Server->To test new features and bugs on the AWS server.
<br/>4.FoodRunnerSellerApp->App for the restaurant owner to sign up their restaurants with FoodRunner(alpha stage).

<br/>How to contribute.
<br/>1.Click the fork button to get the repo to your account.
<br/>2.Copy the git clone link from your account.
<br/>eg ->https://github.com/YourUserName/RestaurantApp.git
<br/>3.Open android studio, and select File->New->import from version control(github).
<br/>4.Paste the link you copied in step 2.
<br/>5.Wait for all the files to get synced.
<br/>6.Create a new branch from master
<br/>a.In the bottom right you'll find git-master(click)
<br/>b.Select new branch
<br/>c.Name it as work_YourName.
<br/>d.Check the box to checkout.
<br/>7.Open file manager and move to the project folder where it is saved.
<br/>eg->C:\Users\YourPCName\AndroidStudioProjects\RestaurantApp
<br/>8.Move inside the project folder and right click->git-bash.
<br/>9.Type the command ->git remote add upstream https://github.com/AlbinoB/RestaurantApp.git
<br/>Note:Creating an upstream to be in sync with master and your branch->work_YourName.
<br/>10.Type the command->git fetch upstream
<br/>Note:fetches any updates on master and stores the changes in a separate branch ->upstream/master
<br/>11.Move to android studio.
<br/>a.In the bottom right you'll find git-work_YourName(click)
<br/>b.In remote branched->upstreams/master(click)->merge into current
<br/>c.On the pop up select->smart merge
<br/>c.You might get merge conflicts here.
<br/>d.See the files which are causing the conflict(Accept-Theirs or Accept-Yours or Merge(to see which lines are causing the conflict)).
<br/>e.Exclude .iml and files inside .idea. 
<br/>Tip: select all the files mentioned above and click accept yours.
<br/>e.Push these changes to your fork, so that your remote->fork and work_YourName are in sync.
<br/>12.Add new features/bug fixes.
<br/>13.Commit only those files which you have changed.
<br/>14.Push the files to your fork.
<br/>15.Go to your fork repo on github.(Refresh)
<br/>16.Under branches select your branch->work_YourName
<br/>17.Find the orange button to create a pull request of the changes you have done.
<br/>18.Commment in brief of what all was done in which file.
<br/>19.If the feature is good the maintainer will merge it to the master.
<br/>20.Caution->If there are conflicts you need to get your fork in sync with master and resend the pull request(Don't click on new pull request).
<br/>click ->compare and pull request to merge the old pull request with the newly updated code.


