/**
 * Count number of elements
 * Get the first element
 * Get the last element
 * Get the first 5 elements
 * Get the last 5 elements
 *
 * hint: use the following methods
 * head
 * last
 * size
 * take
 * tails
 */
val ls = List.range(0,10)
//write you solution here

// [MY CODE]
var numElements = ls.size
var firstElement = ls.head
var lastElement = ls.last
var firstFiveElements = ls.take(5)
var lastFiveElements = ls.drop(ls.size - 5)

/**
 * Double each number from the numList and return a flatten list
 * e.g.res4: List[Int] = List(2, 3, 4)
 *
 * Compare flatMap VS ls.map().flatten
 */
val numList = List(List(1,2), List(3));
//write you solution here

// [MY CODE]
numList.flatMap(i => i.map(_*2))

// The difference between flatMap and map().flatten is that flatMap creates intermediate data structures, whereas map().flatten does not
// Source: https://stackoverflow.com/questions/20215518/map-flatten-and-flatmap-not-equivalent

/**
 * Sum List.range(1,11) in three ways
 * hint: sum, reduce, foldLeft
 *
 * Compare reduce and foldLeft
 * https://stackoverflow.com/questions/7764197/difference-between-foldleft-and-reduceleft-in-scala
 */
//write you solution here

// [MY CODE]
List.range(1,11).sum
List.range(1,11).reduce((i1: Int,i2: Int) => i1 + i2)
List.range(1,11).foldLeft(0)((sum: Int, i: Int) => sum + i)

// reduceLeft is a specialized form of foldLeft
// When reduceLeft is called on a List[Int], it will reduce the list into a single Int value
// When foldLeft is called in a List[Int], it will fold the list into a single value, but doesn't have to be Int

/**
 * Practice Map and Optional
 *
 * Map question1:
 *
 * Compare get vs getOrElse (Scala Optional)
 * countryMap.get("Amy");
 * countryMap.getOrElse("Frank", "n/a");
 */
val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
countryMap.get("Amy")
countryMap.get("edward")
countryMap.getOrElse("edward", "n/a")

// get will return the value of the key as an optional
// getOrElse does the same thing, but allows for a second argument, so if the optional is empty, the second value is returned instead

/**
 * Map question2:
 *
 * create a list of (name, country) tuples using `countryMap` and `names`
 * e.g. res2: List[(String, String)] = List((Amy,Canada), (Sam,US), (Eric,n/a), (Amy,Canada))
 */
val names = List("Amy", "Sam", "Eric", "Amy")
//write you solution here

// [MY CODE]
names.map(name => (name, countryMap.getOrElse(name, "n/a")))

/**
 * Map question3:
 *
 * count number of people by country. Use `n/a` if the name is not in the countryMap  using `countryMap` and `names`
 * e.g. res0: scala.collection.immutable.Map[String,Int] = Map(Canada -> 2, n/a -> 1, US -> 1)
 * hint: map(get_value_from_map) ; groupBy country; map to (country,count)
 */
//write you solution here

// [MY CODE]
names.map(countryMap.getOrElse(_,"n/a")).groupBy(x=>x).map({case(c,n) => (c,n.length)})

/**
 * number each name in the list from 1 to n
 * e.g. res3: List[(Int, String)] = List((1,Amy), (2,Bob), (3,Chris))
 */
val names2 = List("Amy", "Bob", "Chris", "Dann")
//write you solution here

// [MY CODE]
List.range(1,names.length).zip(names2)

/**
 * SQL questions1:
 *
 * read file lines into a list
 * lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
 */
//write you solution here

// [MY CODE]
import scala.io.Source
val source = Source.fromFile("/home/centos/dev/jarvis_data_eng_Josh/spark/src/main/resources/employees.csv")
val lines = source.getLines().toList
source.close()

/**
 * SQL questions2:
 *
 * Convert lines to a list of employees
 * e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
 */
//write you solution here

// [MY CODE]
case class Employee(id: Int, name: String, city: String, age: Int)
val mapToEmp = (line:String) => {
  val token = line.split(",")
  Employee(token(0).trim.toInt,token(1).trim,token(2).trim,token(3).trim.toInt)
}
val noHeader = lines.drop(1)
val employees = noHeader.map(mapToEmp)

/**
 * SQL questions3:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
//write you solution here

// [MY CODE]
val upperCities = employees.map(emp => Employee(emp.id,emp.name,emp.city.toUpperCase,emp.age))

/**
 * SQL questions4:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
//write you solution here

// [MY CODE]
val upperCityToronto = employees.map(emp => if (emp.city.toLowerCase.equals("toronto")) Employee(emp.id,emp.name,emp.city.toUpperCase,emp.age))

/**
 * SQL questions5:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
//write you solution here

// [MY CODE]
val cityNum = upperCities.groupBy(_.city).map({case(city,count) => (city,count.length)})

/**
 * SQL questions6:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */
//write you solution here

// [MY CODE]
upperCities.groupBy(emp => (emp.city,emp.age)).map({case(city,count) => (city,count.length)})

// NOTE: some code has been adapted from the solution code provided on notion.