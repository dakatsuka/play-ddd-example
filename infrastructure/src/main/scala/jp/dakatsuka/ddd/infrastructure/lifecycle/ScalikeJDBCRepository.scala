package jp.dakatsuka.ddd.infrastructure.lifecycle

import jp.dakatsuka.ddd.domain.persistence.{Entity, IOContext, IOContextTransparently, Identifier}
import scalikejdbc._, async._, FutureImplicits._
import scala.concurrent.Future

trait ScalikeJDBCRepository[S <: AsyncDBSession] extends IOContextTransparently[S] {
  type ID <: Identifier[_]
  type E  <: Entity[ID]

  /**
   * Table object (Companion object extended scalaikejdbc.SQLSyntaxSupport)
   */
  val Table: SQLSyntaxSupport[E]

  /**
   * Primary key column
   * Override this value if needed
   */
  lazy val primaryKey = Table.column.id

  /**
   * Table name for select
   */
  lazy val tableName  = Table as syntax

  /**
   * Table syntax
   */
  lazy val syntax = Table.syntax(Table.tableName.split("_").map(_.head).mkString(""))

  /**
   * Convert to column name and value pairs from entity
   * You must implement this method in inherited class.
   *
   */
  protected def convertToNamedValues(entity: E): Seq[(Symbol, Any)]

  /**
   * Convert to entity from scalikejdbc's WrappedResultSet
   * You must implement this method in inherited class.
   *
   */
  protected def convertToEntity(n: ResultName[E])(rs: WrappedResultSet): E

  /**
   * Convert to entity
   *
   */
  protected def toEntity(rs: WrappedResultSet): E = convertToEntity(syntax.resultName)(rs)

  /**
   * Find an entity by ID
   *
   */
  def find(id: ID)(implicit ctx: IOContext[S]): Future[Option[E]] = withSQL {
    selectFrom(tableName).where.eq(primaryKey, id.value)
  }.map(toEntity)
}
