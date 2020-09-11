# Build Guide #

To build the program, Maven and Docker must be installed on your machine.
First, clone the repository to your local machine and navigate to the folder
in the command line. Then, run the following commands:

1. Clean the project and install dependencies

    `mvn clean install`
    
2. Create the docker container to be run and give it a name (note the period)

    `docker build -t <container-name> .`
    
3. Run the container and map it to port 8080 (doesn't have to be 8080 but needs to be mapped regardless)

    `docker run -p 8080:8080 -t <container-name>`
