# December 8, 2017


- ##### Solving for mongodb start problems
        
        > sudo apt-get remove mongodb-org
        > sudo apt-get install mongodb
        
        Create the service config file as shown below:
        
        > cd /lib/systemd/system
        > sudo touch mongodb.service
        > sudo nano mongodb.service
        [Unit]
        Description=An object/document-oriented database
        Documentation=man:mongod(1)
        After=network.target
        
        [Service]
        User=mongodb
        Group=mongodb
        ExecStart=/usr/bin/mongod --quiet --config /etc/mongodb.conf
        
        [Install]
        WantedBy=multi-user.target
        
        Verify in the list if the service is enabled or disabled using the command below:
        
        > systemctl list-unit-files --type=service
        ...
        mongodb.service             disabled
        ...
        
        If it is disabled or not in the list, enable it:
        
        > sudo systemctl enable mongodb.service
        
        Check again:
        
        > systemctl list-unit-files --type=service 
        ...
        mongodb.service             enabled
        ...
        
        Now you can managing the service on SystemD init process:
        
        > systemctl status mongodb
        > sudo systemctl stop mongodb
        > sudo systemctl start mongodb
        > sudo systemctl restart mongodb
    

- ##### Checking if mongodb is working

        systemctl status mongodb


- ##### Checking the port number of mongo db

        netstat -plntu


- ##### Using a database

        use database_name


- ##### Creating collection in a database 

        db.createCollection("collection_name);


- ##### Creating collection and inserting into it

        db.collection_name.insert({name:"Swain"});
 
        if the collection does not exist then it will create a collection and insert into it.


- ##### Deleting a collection 

        db.collection_name.drop();


- ##### Inserting documents

        db.collection_name.insert({
            fisrt_name:"Swain", last_name;"Shrestha"},
            fisrt_name:"Eshika", last_name;"Shrestha"}
        );

- ##### Finding all documents and displaying them beautifully

         db.collection_name.find().pretty();


- ##### Finding documents

        db.collection_name.find();

        db.collection_name.find({
            "first_name": "Swain",
            "last_name": "Shrestha"
        });
  
        db.collection_name.find({  //finding using less than (lt),greater than (gt), greater than or equal to(gte) and not equal to(ne) 
            "age": {$lt:"30"}
        });

        db.collection_name.find({ //using OR
            $or:[{"name":"Swain"},{"salary":"200000"}]
        });

        db.collection_name.find({
            "name":"Swain", $or:[{"skill":"MongoDB"},{"age":"30"}]
        });


- ##### Using Update

        MongoDB by default updates only one matching document

        db.collection_name.update({
          "name":"Swain"}
        { name:"Pratik",last;"Shrestha"});
        
        db.collection_name.update({
          "name":"Swain"},
          {$set{ name:"Pratik",last;"Shrestha"}});
          
        db.collection_name.update({   //multi documet update
          "name":"Swain"},
          {$set{ name:"Pratik",last;"Shrestha"}},
          {multi:true});


- ##### Remove document

        db.collection_name.remove({
            "name":"Swain"});

- ##### Selecting Fields

        Displaying only the firstname and lastname without the _id
        
        db.collection_name.find({},
            {"first_name":1,"last_name":1,"_id":0});


- ##### Using limit, skip and sort

        db.collection_name.find({
        "name":"Swain"}).pretty().limit(5);
  
        db.collection_name.find({
        "name":"Swain"}).pretty().skip(3).limit(5);
  
        db.collection_name.find().sort({"first_name":1});

        1 indicates ascending order and -1 indicates descending order.



##### Using indexing


        db.collection_name.ensureIndex({"Email":1});

        db.collection_name.getIndexes();

        db.collection_name.dropIndexes({
            "Email":1});



