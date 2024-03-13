default: help

# Substitution because CFN Mapping Keys do not support the "_" character
ARCH := $(shell uname -m)
ifeq ($(ARCH),x86_64)
    CPU_ARCH := $(shell echo $(ARCH) | sed 's/_/-/g')
else
    CPU_ARCH := $(ARCH)
endif

help: ## Display this help.
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

build: ## Build the application
	@./mvnw package -Dpackaging=jar

build-native-image: ## Build the native image - The native image generated is highly dependent on the OS on which the command is run (GraalVM is installed on this OS)
	@./mvnw package -Dpackaging=native-image

build-native-image-static: ## Build the native image - In Static Link Mode
	@./mvnw package -Dpackaging=native-image -Pnative-static

build-native-image-in-graal-container: ## Build the native image on graal container
	@docker run --net host \
	  --rm -i \
	  -v ./:/app -w /app \
	  -v $(HOME)/.m2:/root/.m2 \
	  --entrypoint "" \
	  container-registry.oracle.com/graalvm/native-image:21.0.1-ol9 \
	  ./mvnw package -Dpackaging=native-image

# "-Dmicronaut.native-image.args" is only used in case of packaging equal to docker-image
# https://micronaut-projects.github.io/micronaut-maven-plugin/latest/examples/package.html
build-container-image-native: ## Build container image based on native image
	@./mvnw  package -Dpackaging=docker-native -Dmicronaut.native-image.args="--enable-sbom=cyclonedx"

build-MicronautNative: build-native-image-in-graal-container ## Task executed by SAM CLI to generate the artifact to be deployed on AWS Lambda
	@cp src/main/resources/bootstrap $(ARTIFACTS_DIR)
	@cp target/hello $(ARTIFACTS_DIR)/hello
	@chmod 777 $(ARTIFACTS_DIR)/hello
	@chmod 777 $(ARTIFACTS_DIR)/bootstrap

sam-build: ## Build application for SAM (Serverless Application Model)
	@sam build

sam-deploy: ## Deploy application on AWS lambda
	@sam deploy --parameter-overrides ArchCPU=$(CPU_ARCH)

sam-logs: ## SAM Logs AWS Lambda Function (Tail)
	@sam logs -t -n MicronautNative --stack-name micronaut-native

sam-delete: ## Delete Stack AWS
	@sam delete

put-events-event-bridge: ## Put Events in Event Bus
	@aws events put-events --entries file://events.json

