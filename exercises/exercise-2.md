# Cache dependencies between builds

We can further speed up the execution of our tests by caching the dependencies between builds. In most of our code changes the external dependencies remain stable. Yet, in the `javascript` job above we keep downloading all dependencies for each build. It is easy to cache files between builds using the `restore_cache` and `save_cache` steps. We only need a stable cache key: a file which changes if and only if our dependencies change. Luckily, the `yarn.lock` file does exactly this. 

```
jobs:
  javascript:
    docker:
      - image: node:8.6.0
    steps:
      - restore_cache:
          key: node-modules-{{ checksum "yarn.lock" }}
      - run: 
          name: Install dependencies
          command: yarn install
      - save_cache:
          key: node-modules-{{ checksum "yarn.lock" }}
          paths:
            - node_modules
      - run: 
          name: Run javascript tests
          command: yarn test      
```

One can apply the same trick to cache dependencies of other programming languages. Look for a suitable cache key. For example, if you use vendoring in your Golang application you can use the `vendor.json` file. For a Ruby application you can use the `Gemfile.lock` file.

**Exercise 2.1**: Improve the performance of your test by caching the dependencies using the `restore_cache` and `save_cache` steps. 

If you run the job for the first time. The cache key will not be present and the `yarn install` command will run as usual. The following `save_cache` step will cache all files present in the `node_modules` folder. Now, if you run the job again for the second time the cache key will be present and the `yarn install` command will finish much faster. 


Both the `restore_cache` / `save_cache` and `persist_to_workspace` / `attach_workspace` steps save and restore files for later use. Typically, the cache is used to store files between multiple workflow runs, while the workspace is used to store files between multiple jobs of a single workflow:

![Workflow list](images/cache-vs-workspace.png "Cache vs workspace") 
