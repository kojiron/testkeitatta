package dao
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Controller.schema, Controllertype.schema, Device.schema, Devicetype.schema, Operation.schema, Operationgroup.schema, Operationgroupmap.schema, Owner.schema, Permission.schema, PlayEvolutions.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Controller
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param controllertypeid Database column controllerTypeId SqlType(INT)
   *  @param ownerid Database column ownerId SqlType(BIGINT)
   *  @param status Database column status SqlType(SMALLINT), Default(None)
   *  @param tel Database column tel SqlType(VARCHAR), Length(16,true), Default(None) */
  case class ControllerRow(id: Long, controllertypeid: Int, ownerid: Long, status: Option[Short] = None, tel: Option[String] = None)
  /** GetResult implicit for fetching ControllerRow objects using plain SQL queries */
  implicit def GetResultControllerRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Option[Short]], e3: GR[Option[String]]): GR[ControllerRow] = GR{
    prs => import prs._
    ControllerRow.tupled((<<[Long], <<[Int], <<[Long], <<?[Short], <<?[String]))
  }
  /** Table description of table Controller. Objects of this class serve as prototypes for rows in queries. */
  class Controller(_tableTag: Tag) extends Table[ControllerRow](_tableTag, "Controller") {
    def * = (id, controllertypeid, ownerid, status, tel) <> (ControllerRow.tupled, ControllerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(controllertypeid), Rep.Some(ownerid), status, tel).shaped.<>({r=>import r._; _1.map(_=> ControllerRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column controllerTypeId SqlType(INT) */
    val controllertypeid: Rep[Int] = column[Int]("controllerTypeId")
    /** Database column ownerId SqlType(BIGINT) */
    val ownerid: Rep[Long] = column[Long]("ownerId")
    /** Database column status SqlType(SMALLINT), Default(None) */
    val status: Rep[Option[Short]] = column[Option[Short]]("status", O.Default(None))
    /** Database column tel SqlType(VARCHAR), Length(16,true), Default(None) */
    val tel: Rep[Option[String]] = column[Option[String]]("tel", O.Length(16,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Controller */
  lazy val Controller = new TableQuery(tag => new Controller(tag))

  /** Entity class storing rows of table Controllertype
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param name Database column name SqlType(TEXT)
   *  @param devicetypeid Database column deviceTypeId SqlType(INT)
   *  @param operationgroupid Database column operationGroupId SqlType(INT) */
  case class ControllertypeRow(id: Int, name: String, devicetypeid: Int, operationgroupid: Int)
  /** GetResult implicit for fetching ControllertypeRow objects using plain SQL queries */
  implicit def GetResultControllertypeRow(implicit e0: GR[Int], e1: GR[String]): GR[ControllertypeRow] = GR{
    prs => import prs._
    ControllertypeRow.tupled((<<[Int], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table ControllerType. Objects of this class serve as prototypes for rows in queries. */
  class Controllertype(_tableTag: Tag) extends Table[ControllertypeRow](_tableTag, "ControllerType") {
    def * = (id, name, devicetypeid, operationgroupid) <> (ControllertypeRow.tupled, ControllertypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(devicetypeid), Rep.Some(operationgroupid)).shaped.<>({r=>import r._; _1.map(_=> ControllertypeRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
    /** Database column deviceTypeId SqlType(INT) */
    val devicetypeid: Rep[Int] = column[Int]("deviceTypeId")
    /** Database column operationGroupId SqlType(INT) */
    val operationgroupid: Rep[Int] = column[Int]("operationGroupId")
  }
  /** Collection-like TableQuery object for table Controllertype */
  lazy val Controllertype = new TableQuery(tag => new Controllertype(tag))

  /** Entity class storing rows of table Device
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param `type` Database column type SqlType(INT)
   *  @param uniqueId Database column unique_id SqlType(VARCHAR), Length(128,true)
   *  @param subId Database column sub_id SqlType(VARCHAR), Length(128,true)
   *  @param ownerId Database column owner_id SqlType(BIGINT)
   *  @param createdAt Database column created_at SqlType(DATETIME) */
  case class DeviceRow(id: Long, `type`: Int, uniqueId: String, subId: String, ownerId: Long, createdAt: java.sql.Timestamp)
  /** GetResult implicit for fetching DeviceRow objects using plain SQL queries */
  implicit def GetResultDeviceRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[String], e3: GR[java.sql.Timestamp]): GR[DeviceRow] = GR{
    prs => import prs._
    DeviceRow.tupled((<<[Long], <<[Int], <<[String], <<[String], <<[Long], <<[java.sql.Timestamp]))
  }
  /** Table description of table Device. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Device(_tableTag: Tag) extends Table[DeviceRow](_tableTag, "Device") {
    def * = (id, `type`, uniqueId, subId, ownerId, createdAt) <> (DeviceRow.tupled, DeviceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(`type`), Rep.Some(uniqueId), Rep.Some(subId), Rep.Some(ownerId), Rep.Some(createdAt)).shaped.<>({r=>import r._; _1.map(_=> DeviceRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column type SqlType(INT)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Int] = column[Int]("type")
    /** Database column unique_id SqlType(VARCHAR), Length(128,true) */
    val uniqueId: Rep[String] = column[String]("unique_id", O.Length(128,varying=true))
    /** Database column sub_id SqlType(VARCHAR), Length(128,true) */
    val subId: Rep[String] = column[String]("sub_id", O.Length(128,varying=true))
    /** Database column owner_id SqlType(BIGINT) */
    val ownerId: Rep[Long] = column[Long]("owner_id")
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")

    /** Index over (`type`,uniqueId) (database name type) */
    val index1 = index("type", (`type`, uniqueId))
  }
  /** Collection-like TableQuery object for table Device */
  lazy val Device = new TableQuery(tag => new Device(tag))

  /** Entity class storing rows of table Devicetype
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param name Database column name SqlType(TEXT) */
  case class DevicetypeRow(id: Int, name: String)
  /** GetResult implicit for fetching DevicetypeRow objects using plain SQL queries */
  implicit def GetResultDevicetypeRow(implicit e0: GR[Int], e1: GR[String]): GR[DevicetypeRow] = GR{
    prs => import prs._
    DevicetypeRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table DeviceType. Objects of this class serve as prototypes for rows in queries. */
  class Devicetype(_tableTag: Tag) extends Table[DevicetypeRow](_tableTag, "DeviceType") {
    def * = (id, name) <> (DevicetypeRow.tupled, DevicetypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> DevicetypeRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
  }
  /** Collection-like TableQuery object for table Devicetype */
  lazy val Devicetype = new TableQuery(tag => new Devicetype(tag))

  /** Entity class storing rows of table Operation
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param code Database column code SqlType(SMALLINT)
   *  @param name Database column name SqlType(TEXT)
   *  @param devicetypeid Database column deviceTypeId SqlType(INT)
   *  @param adminoperation Database column adminOperation SqlType(BIT), Default(Some(false)) */
  case class OperationRow(id: Int, code: Short, name: String, devicetypeid: Int, adminoperation: Option[Boolean] = Some(false))
  /** GetResult implicit for fetching OperationRow objects using plain SQL queries */
  implicit def GetResultOperationRow(implicit e0: GR[Int], e1: GR[Short], e2: GR[String], e3: GR[Option[Boolean]]): GR[OperationRow] = GR{
    prs => import prs._
    OperationRow.tupled((<<[Int], <<[Short], <<[String], <<[Int], <<?[Boolean]))
  }
  /** Table description of table Operation. Objects of this class serve as prototypes for rows in queries. */
  class Operation(_tableTag: Tag) extends Table[OperationRow](_tableTag, "Operation") {
    def * = (id, code, name, devicetypeid, adminoperation) <> (OperationRow.tupled, OperationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(code), Rep.Some(name), Rep.Some(devicetypeid), adminoperation).shaped.<>({r=>import r._; _1.map(_=> OperationRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column code SqlType(SMALLINT) */
    val code: Rep[Short] = column[Short]("code")
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
    /** Database column deviceTypeId SqlType(INT) */
    val devicetypeid: Rep[Int] = column[Int]("deviceTypeId")
    /** Database column adminOperation SqlType(BIT), Default(Some(false)) */
    val adminoperation: Rep[Option[Boolean]] = column[Option[Boolean]]("adminOperation", O.Default(Some(false)))

    /** Index over (code,devicetypeid) (database name code) */
    val index1 = index("code", (code, devicetypeid))
  }
  /** Collection-like TableQuery object for table Operation */
  lazy val Operation = new TableQuery(tag => new Operation(tag))

  /** Entity class storing rows of table Operationgroup
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param name Database column name SqlType(TEXT) */
  case class OperationgroupRow(id: Int, name: String)
  /** GetResult implicit for fetching OperationgroupRow objects using plain SQL queries */
  implicit def GetResultOperationgroupRow(implicit e0: GR[Int], e1: GR[String]): GR[OperationgroupRow] = GR{
    prs => import prs._
    OperationgroupRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table OperationGroup. Objects of this class serve as prototypes for rows in queries. */
  class Operationgroup(_tableTag: Tag) extends Table[OperationgroupRow](_tableTag, "OperationGroup") {
    def * = (id, name) <> (OperationgroupRow.tupled, OperationgroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> OperationgroupRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
  }
  /** Collection-like TableQuery object for table Operationgroup */
  lazy val Operationgroup = new TableQuery(tag => new Operationgroup(tag))

  /** Entity class storing rows of table Operationgroupmap
   *  @param groupid Database column groupId SqlType(INT)
   *  @param operationid Database column operationId SqlType(INT) */
  case class OperationgroupmapRow(groupid: Int, operationid: Int)
  /** GetResult implicit for fetching OperationgroupmapRow objects using plain SQL queries */
  implicit def GetResultOperationgroupmapRow(implicit e0: GR[Int]): GR[OperationgroupmapRow] = GR{
    prs => import prs._
    OperationgroupmapRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table OperationGroupMap. Objects of this class serve as prototypes for rows in queries. */
  class Operationgroupmap(_tableTag: Tag) extends Table[OperationgroupmapRow](_tableTag, "OperationGroupMap") {
    def * = (groupid, operationid) <> (OperationgroupmapRow.tupled, OperationgroupmapRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(groupid), Rep.Some(operationid)).shaped.<>({r=>import r._; _1.map(_=> OperationgroupmapRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column groupId SqlType(INT) */
    val groupid: Rep[Int] = column[Int]("groupId")
    /** Database column operationId SqlType(INT) */
    val operationid: Rep[Int] = column[Int]("operationId")

    /** Primary key of Operationgroupmap (database name OperationGroupMap_PK) */
    val pk = primaryKey("OperationGroupMap_PK", (groupid, operationid))
  }
  /** Collection-like TableQuery object for table Operationgroupmap */
  lazy val Operationgroupmap = new TableQuery(tag => new Operationgroupmap(tag))

  /** Entity class storing rows of table Owner
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(TEXT)
   *  @param mobileNumber Database column mobile_number SqlType(VARCHAR), Length(11,true)
   *  @param pin Database column pin SqlType(CHAR), Length(5,false)
   *  @param createdAt Database column created_at SqlType(DATETIME)
   *  @param validatedAt Database column validated_at SqlType(DATETIME), Default(None) */
  case class OwnerRow(id: Long, name: String, mobileNumber: String, pin: String, createdAt: Option[java.sql.Timestamp], validatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching OwnerRow objects using plain SQL queries */
  implicit def GetResultOwnerRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[OwnerRow] = GR{
    prs => import prs._
    OwnerRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Owner. Objects of this class serve as prototypes for rows in queries. */
  class Owner(_tableTag: Tag) extends Table[OwnerRow](_tableTag, "Owner") {
    def * = (id, name, mobileNumber, pin, createdAt, validatedAt) <> (OwnerRow.tupled, OwnerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(mobileNumber), Rep.Some(pin), createdAt, validatedAt).shaped.<>({r=>import r._; _1.map(_=> OwnerRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
    /** Database column mobile_number SqlType(VARCHAR), Length(11,true) */
    val mobileNumber: Rep[String] = column[String]("mobile_number", O.Length(11,varying=true))
    /** Database column pin SqlType(CHAR), Length(5,false) */
    val pin: Rep[String] = column[String]("pin", O.Length(5,varying=false))
    /** Database column created_at SqlType(DATETIME) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at")
    /** Database column validated_at SqlType(DATETIME), Default(None) */
    val validatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("validated_at", O.Default(None))
  }
  /** Collection-like TableQuery object for table Owner */
  lazy val Owner = new TableQuery(tag => new Owner(tag))

  /** Entity class storing rows of table Permission
   *  @param controllerid Database column controllerId SqlType(BIGINT)
   *  @param deviceid Database column deviceId SqlType(BIGINT)
   *  @param allowadminoperation Database column allowAdminOperation SqlType(BIT), Default(Some(false)) */
  case class PermissionRow(controllerid: Long, deviceid: Long, allowadminoperation: Option[Boolean] = Some(false))
  /** GetResult implicit for fetching PermissionRow objects using plain SQL queries */
  implicit def GetResultPermissionRow(implicit e0: GR[Long], e1: GR[Option[Boolean]]): GR[PermissionRow] = GR{
    prs => import prs._
    PermissionRow.tupled((<<[Long], <<[Long], <<?[Boolean]))
  }
  /** Table description of table Permission. Objects of this class serve as prototypes for rows in queries. */
  class Permission(_tableTag: Tag) extends Table[PermissionRow](_tableTag, "Permission") {
    def * = (controllerid, deviceid, allowadminoperation) <> (PermissionRow.tupled, PermissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(controllerid), Rep.Some(deviceid), allowadminoperation).shaped.<>({r=>import r._; _1.map(_=> PermissionRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column controllerId SqlType(BIGINT) */
    val controllerid: Rep[Long] = column[Long]("controllerId")
    /** Database column deviceId SqlType(BIGINT) */
    val deviceid: Rep[Long] = column[Long]("deviceId")
    /** Database column allowAdminOperation SqlType(BIT), Default(Some(false)) */
    val allowadminoperation: Rep[Option[Boolean]] = column[Option[Boolean]]("allowAdminOperation", O.Default(Some(false)))

    /** Primary key of Permission (database name Permission_PK) */
    val pk = primaryKey("Permission_PK", (controllerid, deviceid))
  }
  /** Collection-like TableQuery object for table Permission */
  lazy val Permission = new TableQuery(tag => new Permission(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param hash Database column hash SqlType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(TIMESTAMP)
   *  @param applyScript Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param revertScript Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(VARCHAR), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(TIMESTAMP) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column state SqlType(VARCHAR), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Length(16777215,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))
}
