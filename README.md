# tool-openapi-generator

## Usage

#### Generate rest adapters:
java -jar modules/tool-openapi-generator-cli/target/tool-openapi-generator-cli-1.0-SNAPSHOT.jar -i samples/swagger.json -o samples/feature/rest/ -g rest

#### Generate tests for rest adapters:
java -jar modules/tool-openapi-generator-cli/target/tool-openapi-generator-cli-1.0-SNAPSHOT.jar -i samples/swagger.json -o samples/feature/test/ -g tests
