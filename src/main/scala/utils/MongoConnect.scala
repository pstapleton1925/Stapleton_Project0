package utils

import scala.collection.immutable.IndexedSeq
import scala.io.Source

import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._

import utils.Helpers._

object MongoConnect {
  
  val mongoClient: MongoClient = MongoClient();

  val rpgItemsDb: MongoDatabase = mongoClient.getDatabase("rpgItems");

  val weaponsColl: MongoCollection[Document] = rpgItemsDb.getCollection("weapons");
  val accessoriesColl: MongoCollection[Document] = rpgItemsDb.getCollection("accessories");

  val testDb: MongoDatabase = mongoClient.getDatabase("test");
  val testColl: MongoCollection[Document] = testDb.getCollection("test");

  val accessoriesFilePath = "C:/Users/Patrick/Downloads/accessories_MOCK_DATA.json"
  val weaponsFilePath = "C:/Users/Patrick/Downloads/weapons_MOCK_DATA.json"

  val accessoriesDocList = scala.io.Source.fromFile(accessoriesFilePath).getLines().toList;
  val weaponsDocList = scala.io.Source.fromFile(weaponsFilePath).getLines().toList;
  val accessoriesStringToDB = accessoriesDocList.map(doc => Document(doc));
  val weaponsStringToDB = weaponsDocList.map(doc => Document(doc));
  

  // val testDoc: Document = Document("_id" -> "0", "name" -> "testDoc", "message" -> "ugh");

  // testColl.insertOne(testDoc).results();

  //print the names of all databases
  //mongoClient.listDatabaseNames().printResults();

  //drop a database with an observable at the end
  //mongoClient.getDatabase("databaseToBeDropped").drop().headResult();

  //list collections
  //database.listCollectionNames().printResults("Collection Names: ");

  //drop collections with observable
  //collection.drop().headResult();
  
  //mongoClient.close()
}