/*
 * Copyright 2019 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.minio.spark.benchmarks.dfsio

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

class IOReader(hadoopConf: Configuration, dataDir: String) extends IOTestBase(hadoopConf, dataDir) {

  def doIO(fileName: String, fileSize: BytesSize)(implicit conf: Configuration, fs: FileSystem): BytesSize = {

    val bufferSize = conf.getInt("test.io.file.buffer.size", DefaultBufferSize) // TODO GET RID OF DEFAULT
    val buffer: Array[Byte] = new Array[Byte](bufferSize)
    val filePath = new Path(dataDir, fileName.toString)

    logger.info("Reading file {} with size {}", filePath.toString, fileSize.toString)

    val in = fs.open(filePath)

    var actualSize: Long = 0 // TODO improve this
    try {
      Stream.continually(in.read(buffer, 0, bufferSize))
        .takeWhile(_ > 0 && actualSize < fileSize)
        .foreach { currentSize =>
          actualSize += currentSize
          logger.debug(s"Reading chunk of size $currentSize. Currently: $actualSize / $fileSize")
        }
    } finally {
      in.close()
    }

    logger.info("File {} with size {} read successfully", fileName, actualSize.toString)

    actualSize
  }

}
