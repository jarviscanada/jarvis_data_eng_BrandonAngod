import org.scalatest.junit.AssertionsForJUnit
import scala.collection.mutable.ListBuffer
import org.junit.Assert._
import org.junit.Test
import org.junit.Before


class AppTest extends AssertionsForJUnit{


  @Before
  def setup(){
    var app:App = new App()
    //Change null to the table files
    val backupPath = null
    val srcPath = null
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
  }

  @Test
  def backupCheck(){
    assertTrue(true,app.backupCheck())
  }

  @Test
  def saveBackup(){
    assertEquals()
  }

  @Test
  def readTable(){
    //Replace endresult with output
    endResult:String = ""
    assertEquals()
  }
}