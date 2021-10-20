import utils.Helpers._
import utils.MongoConnect._

import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._
import javax.print.Doc

object Main extends App {

  println("Hello from Main");
  println(" ");

  println("Dropping outdated collections...")
  println(" ");
  println(s"Dropping weapons collection... ${weaponsColl.drop().headResult()}");
  println(" ");
  println(s"Dropping accessories collection... ${accessoriesColl.drop().headResult()}");
  println(" ");
  println(" ");
  println(s"total # of documents in weapons collection: ${weaponsColl.countDocuments().headResult()}");
  println(s"total # of documents in accessories collection: ${accessoriesColl.countDocuments().headResult()}");
  
  val accInsertObservable = accessoriesColl.insertMany(accessoriesStringToDB);

  val accInsertAndCount = for {
  insertResult <- accInsertObservable
  countResult <- accessoriesColl.countDocuments()
  } yield countResult
  
  println(" ");
  println("Inserting updated accessory documents from file...")
  println(s"total # of documents in accessories collection:  ${accInsertAndCount.headResult()}")

  val weapInsertObservable = weaponsColl.insertMany(weaponsStringToDB);

  val weapInsertAndCount = for {
  insertResult <- weapInsertObservable
  countResult <- weaponsColl.countDocuments()
  } yield countResult

  println(" ");
  println("Inserting updated weapons documents from file...")
  println(s"total # of documents in weapons collection:  ${weapInsertAndCount.headResult()}")

  println(" ");
  println("Accessing the first document in the accessories collection...")
  accessoriesColl.find().projection(excludeId()).first().printHeadResult();

  println(" ");
  println("Accessing the first document in the weapons collection...")
  weaponsColl.find().projection(excludeId()).first().printHeadResult();

  println(" ");
  println("Finding expert level weapons and sorting by element damage...");
  println(" ");
  weaponsColl.find(and(gte("baseDamage", 100))).sort(descending("elementDamage")).projection(excludeId()).printResults();

  println(" ");
  println("Finding the best accessories for defending against Lightning...")
  println(" ");
  accessoriesColl.aggregate(List(
    filter(equal("element", "Lightning")), 
    filter(gte("elementDefMod", 15)), 
    project(fields(excludeId())), 
    sort(orderBy(ascending("elementDamageMod"))))).printResults();
  
  mongoClient.close();
}