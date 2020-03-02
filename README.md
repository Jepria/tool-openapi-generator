# tool-openapi-generator

## Description
The reference project for generated files [jepria-showcase](https://github.com/Jepria/jepria-showcase/tree/master/module/JepRiaShowcase).

## Usage
### Binary files
Binary files placed in [bin-repo](https://github.com/Jepria/bin-repo).
Current versions: 
  [generator-cli-1.0.jar](https://github.com/Jepria/bin-repo/blob/master/build/org/jepria/tools/openapi/generator-cli/1.0/generator-cli-1.0.jar)
  [generator-1.0.jar](https://github.com/Jepria/bin-repo/blob/master/build/org/jepria/tools/openapi/generator/1.0/generator-1.0.jar)
### Command line options
```
-i         - input specification file
-o         - output directory
-g         - generation options
   rest    - generate rest adapters
   tests   - generate rest adapters 
   proj    - generate project
```

#### Example use of generator-cli.jar from bin repository.
```
java -jar %BIN_HOME%/build/org/jepria/tools/openapi/generator-cli/1.0/generator-cli-1.0.jar -i samples/swagger.json -o samples/feature/rest/ -g rest
```
### Build projects
To build from source, you need the following installed and available in your $PATH:
* [Java 8](https://www.oracle.com/technetwork/java/index.html)
* [Apache Maven ](https://maven.apache.org/)

After cloning the project, you can build it from source with this command:
```sh
mvn clean install
```
