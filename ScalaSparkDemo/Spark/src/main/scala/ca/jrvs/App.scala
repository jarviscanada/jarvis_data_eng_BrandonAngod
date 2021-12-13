package ca.jrvs

import scala.reflect.io.File


object App {
  import org.apache.spark
  import spark.sql
  import org.apache.spark.sql.{Row, SaveMode, SparkSession}
  import org.apache.spark.hive._
  //val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
  private val srcTable = null
  private val backupTable = null

  /*TODO

    Retrieves the following information from each table.
    HiveTable src & HiveTable backup
    PartionColumn part
    Column Foo,Bar,Baz
    Foo string
    Bar Non-Null Long
    Baz BigDecimal
  */
  def readTable() = {

  }

  /*TODO
    Check if src == backup
    If theres a change backup = src
  */
  def backupCheck(srcPath:String,backupPath:String) = {
    /*Method 1 WIP
      Perform a join on partition column and compare NULL's to see if the data is in both columns
      If something is null that means there is differences between the two

      Possible Logic Errors-
        If no nulls appear due to the structure of the partition table just causing the join
        to occur with all the data along with it. Can be resolved if I only take the Partition itself
        and none of the Other data.

    */
    //TODO fix statement
    if(srcTable.sql("SELECT Partition FROM srcTable LEFT JOIN Partition ON srcTable.Partition = backupTable.Partition" +
      " ORDER BY srcTable.Partition;") != null){
      saveBackup()
      return false
     }
    else{
      return true
    }


    /*Method 2
        Find a method to easily compare the values for absolute equality.

        Possible Errors-
          So far no solutions exist with limited research.
    */
    /*
    if(srcPartition != backupPartition){
      saveBackup()
      return false
    }
    */
  }

  /*TODO
    Method to save backup table to source table
  */
  def saveBackup(){
    /*Method 1
      Full join should bring all the information over. With consistent labels and info data should
      be transfered over without errors
    */
    //srcTable.sql("SELECT Partition FROM srcTable FULL JOIN * ON srcTable.Partition = backupTable.Partition" +
    //      " ORDER BY srcTable.Partition")


    /*Method 2
      Drop Backup table then Create new backup table.
      Notes: Potentially slower
    */
    backupTable.sql("DROP TABLE backupTable;")
    backupTable.sql("CREATE TABLE backupTable AS SELECT * FROM srcTable;")

  }
  def main(args: Array[String]) = {
    //Retrieve names of each HiveTable
    val srcPath = args(0);
    val backupPath = args(1);

    //Initialize our tables
    srcTable = spark
      .read
      .option("inferSchema", "true")
    .option("header", "true")
    .csv(srcPath)

    backupTable = spark
      .read
      .option("inferSchema", "true")
      .option("header", "true")
    .csv(backupPath)

    //Check to see if there are differences
    backupCheck(srcPath,backupPath)

  }
}