/**
  * *****************************************************************************
  * copyright 2018 ASTROIDE
  *
  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
  * use this file except in compliance with the License.  You may obtain a copy
  * of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
  * License for the specific language governing permissions and limitations under
  * the License.
  * ****************************************************************************
  */
package fr.uvsq.adam.astroide.sampling

import org.apache.spark.sql.SparkSession

object LoadSample {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._

    val inputDataframe = spark.read.option("header", "true").csv(args(0).toString)
    val sampleData = inputDataframe.sample(false, args(1).toDouble)

    sampleData.write.options(Map("header" -> "true")).options(Map("codec" -> "org.apache.hadoop.io.compress.GzipCodec")).csv((args(2).toString))

    println(args(1) + " | " + sampleData.count())

  }
}




