# Add a manual approval step before deploying

Workflows may be configured to wait for manual approval:

< INSERT IMAGE >


A manual approval is convenient if your release process contains a manual sanity check before releasing to production. In the workflow given below, the `test_deploy` job is always executed. The `prod_deploy` job however is only executed after a manual approval: 

```
workflows:
  build_and_test:
    - test_deploy
    - hold:
        type: approval
        requires:
          - test_deploy
    - prod_deploy:
        requires:
          - hold 
```

**Exercise 3.1** Open the `.circleci/config.yml` file and add a manual approval step before executing the `deploy` job 