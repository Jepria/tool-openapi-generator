# tool-openapi-generator

## Description
The reference project for generated files [jepria-showcase](https://github.com/Jepria/jepria-showcase/tree/master/module/JepRiaShowcase).

## Usage
#### Command line options
```
-i         - input specification file
-o         - output directory
-g         - generation options
   rest    - generate rest adapters
   tests   - generate rest adapters   
```
#### Generate rest adapters:
```
java -jar modules/tool-openapi-generator-cli/target/tool-openapi-generator-cli-1.0-SNAPSHOT.jar -i samples/swagger.json -o samples/feature/rest/ -g rest
```

#### Generate tests for rest adapters:
```
java -jar modules/tool-openapi-generator-cli/target/tool-openapi-generator-cli-1.0-SNAPSHOT.jar -i samples/swagger.json -o samples/feature/test/ -g tests
```
#### Binary files
Binary files placed in [bin-repo](https://github.com/Jepria/bin-repo).

Current versions: 
  [generator-cli-1.0.jar](https://github.com/Jepria/bin-repo/blob/master/build/org/jepria/tools/openapi/generator-cli/1.0/generator-cli-1.0.jar)
  [generator-1.0.jar](https://github.com/Jepria/bin-repo/blob/master/build/org/jepria/tools/openapi/generator/1.0/generator-1.0.jar)