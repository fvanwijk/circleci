# Run jobs in parallel

## Step 1: Add a job to run the Javascript tests
 

CircleCI allows us to choose a different Docker image for each job. For example, The `config.yml` file below specifies two jobs, named: `go` and `javascript`
 
```
jobs:
  go:
    docker:
      - image: golang:1.8.3
    steps:
      - run:
          name: Install dependencies
          command: go get ./...
      - run:
          name: Run Go tests
          command: go test ./...

  javascript:
    docker:
      - image: node:8.6.0
    steps:
      - run: 
          name: Install dependencies
          command: yarn install
      - run: 
          name: Run javascript tests
          command: yarn test      
```
 
The `go` job runs the steps in the `golang:1.8.3` Docker image, an image with Go development tools installed. While the `javascript` job runs the javascript unit tests in the `node:8.6.0` Docker image, an image with Node and Yarn installed. Because we can specify a Docker image for each job, we can choose an image with only the minimal tools installed, keeping each job light-weight and fast. 

**Exercise 1.1**: Add a new job to your `.circleci/config.yml` file which will run the `yarn install` and `yarn test` commands. Make sure you retrieve the sourcecode which was checked out in the `checkout` job. Take a look at the existing `scala` job to get an idea. Also, make sure you execute the commands in the right working directory.
 
## Step 2: Run jobs in parallel 

CircleCI makes it possible to run multiple jobs in parallel. For example, the following workflow will execute the `go` and `javascript` jobs in parallel: 

```
workflows:
  commit:
    jobs:
      - checkout
      - go:
          requires:
            - checkout
      - javascript:
          requires:
            - checkout
      - deploy:
          requires:
            - go
            - javascript
```

It is possible to fork, i.e. execute the `go` and the `javascript` job after the completion of the `checkout` job. And it is possible to join, i.e. execute the `deploy` job after the completion of both the `go` and the `javascript` jobs.  This makes it possible to significantly reduce the time needed to run our tests. 

**Exercise 1.2**: Change the workflow in your `.circleci/config.yml` file to run the `javascript` job in parallel with the `scala` job. Push your changes to GitHub and test your `javascript` job.  
