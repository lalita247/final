data ?= data.env
include $(data)
export $(shell sed 's/=.*//' $(data))

build:	build-version
	
build-version:
	docker build -t ${DOCKER_NAME}/${REPO_NAME}:${VERSION}  .
	
login:
	docker login -u ${DOCKER_NAME} -p ${DOCKER_PASSWORD}

push:	login	
	docker push ${DOCKER_NAME}/${REPO_NAME}:latest
