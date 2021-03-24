# gomoku
Simple game in which two players alternate turns placing a stone on an empty intersection. The winner is the first player who forms an unbroken line of exactly 5 stones horizontally, vertically, or diagonally.

![Gomoku screenshot](https://pmasior.github.io/images/gomoku.png)

## How to run?
1. Install Java 
    * Linux (Linux Mint, Ubuntu): 
        ```bash
        sudo apt install openjdk-11-jdk
        ```
    * Windows: Download SE Development Kit 11 or SE Development Kit 15.0.2 from [Oracle.com](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) and install it
2. Clone repository
    ```bash
    git clone https://github.com/pmasior/gomoku.git
    ```
    or download repository from [GitHub.com](https://github.com/pmasior/gomoku/archive/refs/heads/main.zip) and extract it
3. Run console and go to folder where you cloned the repository or extracted archive (folder with file `gomoku.py`)
    ```bash
    cd gomoku
    ```
4. Run app using Gradle:
    * Linux:
        ```bash
        ./gradlew run
        ```
    * Windows:
        ```batch
        .\gradlew.bat run
        ```
