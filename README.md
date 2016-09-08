#Lucene example
Example Java project for using Lucene (https://lucene.apache.org/).
This sample create some indices with two fields: id, tags.
Then it runs some queries and prints the result.

##How to build
It is a simple gradle project. Use gradle to build it.

##How to run
Run the See the [com.oriaxx77.lucenePlay.LucenePlay](./src/main/java/com/oriaxx77/lucenePlay/LucenePlay.java) class.

##API
[com.oriaxx77.lucenePlay.LucenePlay](./src/main/java/com/oriaxx77/lucenePlay/LucenePlay.java) class
- Static entry point of the example.
- It's main creates some documents, runs some queries then prints the results.
- It uses an in-memory store.

[com.oriaxx77.lucenePlay.IndexCreator](./src/main/java/com/oriaxx77/lucenePlay/IndexCreator.java) class
- Create 3 documents with two fields: id, tags.

Documents created
| id            | tags             |
| ------------- | ---------------- |
| 0 		    | apples oranges   |
| 1             | oranges          |
| 2             | apples           |

[com.oriaxx77.lucenePlay.IndexDocumentFinder](./src/main/java/com/oriaxx77/lucenePlay/IndexDocumentFinder.java) class
  - It can run queries against index stores.
  - The example uses the following queries:
    - tags:oranges NOT tags:apples
    - tags:oranges AND tags:apples
    - tags:oranges OR tags:apples
  

[com.oriaxx77.lucenePlay.QueryResultPrinter](./src/main/java/com/oriaxx77/lucenePlay/QueryResultPrinter.java) class
- It can print query results on to the default output.

Example output:

```sh
QueryString: tags:oranges NOT tags:apples
doc=1 score=0.52354836 shardIndex=0

QueryString: tags:oranges AND tags:apples
doc=0 score=0.6829643 shardIndex=0

QueryString: tags:oranges OR tags:apples
doc=0 score=0.6829643 shardIndex=0
doc=1 score=0.52354836 shardIndex=0
doc=2 score=0.52354836 shardIndex=0
```

[com.oriaxx77.lucenePlay.Entity](./src/main/java/com/oriaxx77/lucenePlay/Entity.java) class
- Something to index.

