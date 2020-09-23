# Build Guide #

To build the program, Maven and Docker must be installed on your machine.
First, clone the repository to your local machine and navigate to the folder
in the command line. Then, run the following commands:

1. Clean the project and install dependencies

    `mvn clean install`
   
2. Build the docker container 

    `docker-compose build`
    
3. Run the docker container

    `docker-compose up`
    
4. Open up localhost:8080 in your browser to see the exciting contacts application!

