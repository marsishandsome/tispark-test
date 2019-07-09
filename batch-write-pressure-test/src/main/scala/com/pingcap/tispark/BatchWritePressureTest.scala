package com.pingcap.tispark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object BatchWritePressureTest {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setIfMissing("spark.master", "local[*]")
      .setIfMissing("spark.app.name", getClass.getName)
      .setIfMissing("spark.sql.extensions", "org.apache.spark.sql.TiExtensions")
      .setIfMissing("tidb.addr", "172.16.5.91")
      .setIfMissing("tidb.port", "4000")
      .setIfMissing("tidb.user", "root")
      .setIfMissing("spark.tispark.pd.addresses", "172.16.5.91:2379")

    val spark = SparkSession.builder.config(sparkConf).getOrCreate()

    spark.sql("use test")
    spark.sql("select count(*) from t1").show
  }
}
