dev:
	@go run ./cmd/*.go

build: 
	@mkdir -p ./bin
	@go build -o ./bin/main ./cmd/*.go 

run: 
	@./bin/main

tests:
	@go test
	@go test ./types

todo:
	@grep -rn --exclude "Makefile" TODO | grep -oP '//\K.*'
