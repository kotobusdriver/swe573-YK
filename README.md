This repository is opened for the SWE573 Software Development Practice course (2024 spring semester), at Bogazici University. 
Repo owner: Yana Krasovska, student number 2022719111.



RUN THE APPLICATION IN DOCKER

1. Install Docker Desktop   
   Firstly, if you are on Windows install/update WSL 2 by typing the following command in a terminal

C:\Users\YOURMACHINE> wsl --update
	
   Then, download the appropriate installation binary from https://docs.docker.com/desktop/
   After the download is completed go on to install Docker Desktop. At the end of the installation, you will need to restart your system.

   Make sure to check that Docker Engine is running by opening the Docker Desktop application.

2. Open a terminal in the source folder root, where docker-compose.yaml file resides.

3. Run the following command in the terminal to start the database and the application

C:\Users\YOURMACHINE> docker-compose up 

4. Wait for container images to be pulled from internet and the application to be built and started. This may take some time.

5. Open http://localhost:5173 in your browser to view the application

6. To stop the application use Ctrl+C to stop containers in the terminal.
