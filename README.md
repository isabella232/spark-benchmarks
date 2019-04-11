Spark Benchmarks
================

> Thanks to original work at https://github.com/BBVA/spark-benchmarks this is a fork to support
> a generalized filesystem connector to support HDFS, S3A and all other connectors. This fork
> also supports Spark 2.4.x, Hadoop 3.1.x and will be is more actively maintained.

[![Build Status](https://travis-ci.org/minio/spark-benchmarks.svg?branch=master)](https://travis-ci.org/minio/spark-benchmarks)

Overview
--------

Spark Benchmarks is a benchmarking suite specific for Apache Spark that helps to evaluate a Spark deployment 
in terms of speed, throughput and system resource utilization.

Motivation
----------

There already exists other benchmarks suites in the community that helps to evaluate different big data 
frameworks. The more representative one is [HiBench](https://github.com/intel-hadoop/HiBench) which contains a set of 
Hadoop, Spark and streaming workloads suited for benchmarking different use cases: sorting, machine learning 
algorithms, web searches, graphs and so on. 

However, not all workloads are implemented using only Spark jobs and rely on Hadoop MapReduce framework assuming Spark
is running on top of a YARN cluster. Concretely, DFSIO benchmark, that tests the throughput of a HDFS cluster by 
generating a large number of tasks performing writes and reads simultaneously, does not have a Spark corresponding 
implementation.

The purpose of this suite is to help users to stress different scenarios of Spark combined with a distributed 
file system (MinIO, HDFS, Alluxio, etc), regardless of whether it runs on Mesos, YARN or Spark Standalone. Moreover, it enables
an exhaustive study and comparision for different platform and hardware setups, sizing tuning and system optimizations, 
making easier the evaluation of their performance implications and the identification of bottlenecks.

Workloads
---------

Currently, there is only one workload available:

1. [TestDFSIO](./docs/TestDFSIO.md)

Getting started
---------------

Please visit the documentation associated to the corresponding workload.

Building Spark Benchmarks
-------------------------

### Pre-Requisites

The followings are needed for building Spark Benchmarks

* JDK 8
* [SBT 0.13.17](http://www.scala-sbt.org/0.13.17/docs/Getting-Started/Setup.html)

### Supported Spark/Hadoop releases:

* Spark 2.4.x
* Hadoop 3.1.x

### Building

To build all modules in Spark Benchmarks, use the below command.

```bash
sbt clean assembly
```

If you are only interested in a single workload you can build a single module. For example, the below command only
builds the dfsio workload.

```bash
sbt dfsio/clean dfsio/assembly
```

## Contributions

Contributions are very welcome, see [CONTRIBUTING.md](https://github.com/minio/spark-benchmarks/blob/master/CONTRIBUTING.md) 
or skim [existing tickets](https://github.com/minio/spark-benchmarks/issues) to see where you could help out.

## License

Spark Benchmarks is Open Source and available under the Apache 2 License.
