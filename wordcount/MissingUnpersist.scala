// scalastyle:off println
package org.apache.spark.examples.wordcount

import org.apache.spark.{SparkConf, SparkContext}

object MissingUnpersist{
    def main(args: Array[String]) {
        val sparkConf = new SparkConf().setAppName("Missing unpersist")
        val sc = new SparkContext(sparkConf)
        val data = sc.textFile("article.txt")
        val words = data.flatMap(x=>x.split(" "))
        words.persist()
        words.count()
        val pairs = words.map((_,1))
        val result = pairs.reduceByKey(_+_)
        result.persist()
        result.count()
        result.take(10)
        result.unpersist()
        sc.stop()
    }
}